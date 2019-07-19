/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for listing accounts on chain with names.
 * 
 * Version 4.03 is buggy.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainName extends ListTaskAccountChainFindAccounts8 {

   
   public ListTaskAccountChainName(PCoreCtx pc, IListListener<Account> listener, String name, boolean isExactMatch) {
      super(pc, listener);
      this.name = name;
      this.isExactMatch = isExactMatch;
   }

}
