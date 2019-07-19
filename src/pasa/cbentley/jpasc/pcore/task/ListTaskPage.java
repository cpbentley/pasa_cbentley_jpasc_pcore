/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task;

import java.util.List;

import com.github.davidbolet.jpascalcoin.exception.RPCApiException;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;

public abstract class ListTaskPage<T> extends ListTask<T> {

   /**
    * The last will list the items filling the pages of the book.
    */
   protected PagerAbstract<T> pager;

   public ListTaskPage(PCoreCtx pc, IListListener<T> listener) {
      super(pc, listener);
   }

   protected abstract PagerAbstract<T> createPagerDefault();

   protected abstract List<T> findItems(IPascalCoinClient client, Integer start, Integer pageSize);

   protected abstract List<T> getFiltered(List<T> list);

   public PagerAbstract<T> getPager() {
      return pager;
   }

   public void runAbstract() {
      if (pager == null) {
         pager = createPagerDefault();
      }
      runAbstract(pager);
   }

   public void runAbstract(PagerAbstract<T> pager) {
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
      } while (hasMoreDataPages && isContinue());
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
