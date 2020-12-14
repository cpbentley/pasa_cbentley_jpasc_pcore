/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountPositiveBalance;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching wallet account with a specific name {@link IStrAcceptor}
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletBalancePositive extends ListTaskAccountWallet {


   public ListTaskAccountWalletBalancePositive(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
      this.addFilterAccount(new FilterAccountPositiveBalance(pc));
   }

}
