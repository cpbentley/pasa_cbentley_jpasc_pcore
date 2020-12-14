/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for listing all accounts on chain inside a range
 * 
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainRange extends ListTaskAccountChainFindAccounts4 {

   private int rangeEnd;

   private int rangeStart;

   public ListTaskAccountChainRange(PCoreCtx pc, IListListener<Account> listener, int rangeStart, int rangeEnd) {
      super(pc, listener);
      //#mdebug
      if (rangeStart < 0 || rangeEnd < 0 || rangeStart > rangeEnd) {
         throw new IllegalArgumentException("rangeStart=" + rangeStart + " rangeEnd=" + rangeEnd);
      }
      //#enddebug
      this.rangeEnd = rangeEnd;
      this.rangeStart = rangeStart;
   }

   protected PagerAbstract<Account> createPagerDefault() {
      PagerAccount pageAccount = new PagerAccount(pc);
      pageAccount.setLookUpRangeStart(rangeStart); //start at beginning of range
      pageAccount.setLookUpRangeEnd(rangeEnd + 1); //TODO why +1 for 99999?
      pageAccount.setTimingEnabled(true);
      pageAccount.setPageTimingMin(250);
      pageAccount.setManualExactPageSize(false);
      pageAccount.setPageSize(1); //starts at 1 and will increase
      pageAccount.build();
      return pageAccount;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChain");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChain");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
