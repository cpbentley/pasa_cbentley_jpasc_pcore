/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountBalanceMinMax;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for searching wallet account with a specific name {@link IStrAcceptor}
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletBalance extends ListTaskAccountWallet {

   public ListTaskAccountWalletBalance(PCoreCtx pc, IListListener<Account> listener, Double minBalance, Double maxBalance) {
      super(pc, listener);
      this.addFilterAccount(new FilterAccountBalanceMinMax(pc, minBalance, maxBalance));
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountWalletBalance");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountWalletBalance");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}
