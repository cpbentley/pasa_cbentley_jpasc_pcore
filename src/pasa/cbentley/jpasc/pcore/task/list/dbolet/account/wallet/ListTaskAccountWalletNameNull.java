/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccount;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching wallet accounts without a name
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletNameNull extends ListTaskAccountWallet implements IFilterAccount {


   public ListTaskAccountWalletNameNull(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
      this.addFilterAccount(this);
   }

   public boolean filterAccount(Account account) {
      String name = account.getName();
      if (name == null || name.equals("")) {
         return true;
      }
      return false;
   }

}
