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
public abstract class ListTaskPageCustomRun<T> extends ListTaskPage<T> {

   public ListTaskPageCustomRun(PCoreCtx pc, IListListener<T> listener) {
      super(pc, listener);
   }

   /**
    * Create the {@link PagerAbstract} controlling this task.
    * @return
    */
   protected PagerAbstract<T> createPagerDefault() {
      return null;
   }

   /**
    * Build a list of items for the given page
    * @param client
    * @param start
    * @param pageSize
    * @return
    */
   protected List<T> findItems(IPascalCoinClient client, Integer start, Integer pageSize) {
      return null;
   }

   /**
    * Filters the list. Returns same list if no filtering required.
    * @param list
    * @return
    */
   protected List<T> getFiltered(List<T> list) {
      return null;
   }

   /**
    * Implementation of {@link AbstractBRunnable#runAbstract()}
    */
   public void runAbstract() {
      runAbstractCustom();
   }

   public abstract void runAbstractCustom();

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
      dc.nlLvl1Line(pager, "Pager");
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
