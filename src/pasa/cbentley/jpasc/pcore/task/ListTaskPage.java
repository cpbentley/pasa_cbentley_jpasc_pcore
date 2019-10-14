/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task;

import java.util.List;

import com.github.davidbolet.jpascalcoin.exception.RPCApiException;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.thread.AbstractBRunnable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;

/**
 * List objects from an {@link IPascalCoinClient}
 * TODO move a generic version of this to core5
 * @author Charles Bentley
 *
 * @param <T>
 */
public abstract class ListTaskPage<T> extends ListTask<T> {

   /**
    * The last will list the items filling the pages of the book.
    */
   protected PagerAbstract<T> pager;

   public ListTaskPage(PCoreCtx pc, IListListener<T> listener) {
      super(pc, listener);
   }

   /**
    * Create the {@link PagerAbstract} controlling this task.
    * @return
    */
   protected abstract PagerAbstract<T> createPagerDefault();

   /**
    * Build a list of items for the given page
    * @param client
    * @param start
    * @param pageSize
    * @return
    */
   protected abstract List<T> findItems(IPascalCoinClient client, Integer start, Integer pageSize);

   /**
    * Filters the list. Returns same list if no filtering required.
    * @param list
    * @return
    */
   protected abstract List<T> getFiltered(List<T> list);

   /**
    * Returns the {@link PagerAbstract} of thhis {@link ListTaskPage}
    * 
    * Could be null if overriding class does not initialize it
    * @return
    */
   public PagerAbstract<T> getPager() {
      return pager;
   }

   /**
    * Implementation of {@link AbstractBRunnable#runAbstract()}
    */
   public void runAbstract() {
      if (pager == null) {
         pager = createPagerDefault();
      }
      runAbstract(pager);
   }

   /**
    * 
    * @param pager
    */
   public void runAbstract(PagerAbstract<T> pager) {
      //#debug
      toDLog().pFlow("Start of Method", this, ListTaskPage.class, "runAbstract", LVL_03_FINEST, true);

      IPascalCoinClient pclient = pc.getPClient();
      List<T> listProcessed = null;
      List<T> listFiltered = null;
      boolean hasMoreDataPages = false;
      do {
         try {
            Integer start = pager.getStart();
            Integer max = pager.getMax();
            //TODO simulate a problem in the RPC call that generates a RPCApiException
            listProcessed = findItems(pclient, start, max);
            listFiltered = getFiltered(listProcessed);
            //publishing constraints : let the pager decide if the full list must be published (e.g. fixed size page of results)
            List<T> listToPublish = pager.getListToPublish(listProcessed, listFiltered);
            publishList(listToPublish);
            //automatically false when list is null or empty.we want to publish exact same page or we don't care
            //hasMoreDataPages = pageAccount.pageTurnInLoop(listProcessed, listFiltered);
            hasMoreDataPages = pager.isContinuePaging();
         } catch (RPCApiException e) {
            //we want to print stack even in production.. this should not occurr.
            e.printStackTrace();
            //#debug
            toDLog().pEx(pager.toString(), this, ListTaskPage.class, "runAbstract", e);

            //tell the pager there was a failure.. pager will decide if he wants may try again
            hasMoreDataPages = pager.isContinuePagingAfterException();
         }
      } while (hasMoreDataPages && isContinue() && !isTaskShouldStop());

      //#debug
      toDLog().pFlow("End of Method", this, ListTaskPage.class, "runAbstract", LVL_03_FINEST, true);
   }

   /**
    * Override this when you need to check for extra reasons to continue.
    * For example a new block has been mined which invalidates current task
    */
   protected boolean isTaskShouldStop() {
      return false;
   }
   
   /**
    * Sets the {@link PagerAbstract} that will govern how results are fed to the {@link IListListener}
    * @param pager
    */
   public void setPager(PagerAbstract<T> pager) {
      this.pager = pager;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskPage");
      toStringPrivate(dc);
      super.toString(dc.sup());

      dc.nlLvl(pager, "Pager");
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskPage");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
      dc.nlLvlOneLine(pager, "Pager");
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
