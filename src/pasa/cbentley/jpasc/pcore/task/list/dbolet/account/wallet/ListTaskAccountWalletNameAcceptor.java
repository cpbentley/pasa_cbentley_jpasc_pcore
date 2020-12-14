/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccount;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching wallet account with a specific name {@link IStrAcceptor}
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletNameAcceptor extends ListTaskAccountWallet implements IFilterAccount {

   private IStrAcceptor strAcceptor;

   public ListTaskAccountWalletNameAcceptor(PCoreCtx pc, IListListener<Account> listener, IStrAcceptor strAcceptor) {
      super(pc, listener);
      if (strAcceptor == null) {
         throw new NullPointerException();
      }
      this.strAcceptor = strAcceptor;
      this.addFilterAccount(this);
   }

   public boolean filterAccount(Account account) {
      String name = account.getName();
      if (name == null || name.equals("")) {
         return false;
      }
      return strAcceptor.isStringAccepted(name);
   }

}
