/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching wallet account with a specific name {@link IStrAcceptor}
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainBalanceMinMax extends ListTaskAccountChainFindAccounts8 {

   public ListTaskAccountChainBalanceMinMax(PCoreCtx pc, IListListener<Account> listener, Double balanceMin, Double balanceMax) {
      super(pc, listener);
      this.balanceMin = balanceMin;
      this.balanceMax = balanceMax;
   }
   
   /**
    * so as soon as we have a result, we publish it
    */
   protected PagerAbstract<Account> createPagerDefault() {
      Integer numAccounts = pc.getPClient().getBlockCount() * 5;
      PagerAccount pageAccount = new PagerAccount(pc);
      pageAccount.setLookUpRangeStart(0); //start at beginning of chain
      pageAccount.setLookUpRangeEnd(numAccounts);
      pageAccount.setTimingEnabled(true);
      pageAccount.setManualExactPageSize(false);
      pageAccount.setPageSize(1);
      pageAccount.build();
      return pageAccount;
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainBalanceMinMax");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainBalanceMinMax");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
