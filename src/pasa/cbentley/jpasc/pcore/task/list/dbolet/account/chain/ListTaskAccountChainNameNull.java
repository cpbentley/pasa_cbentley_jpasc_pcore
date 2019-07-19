/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountName;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for searching chain accounts without a name.
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainNameNull  extends ListTaskAccountChainFindAccounts4 {


   public ListTaskAccountChainNameNull(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
      FilterAccountName filter = new FilterAccountName(pc, null);
      this.addFilterAccount(filter);
   }
   
}
