/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;

/**
 * Task for listing all accounts on chain
 * 
 * {@link IListListener} will get some accounts when the {@link PagerAccount} max has been reached.
 * 
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChain extends ListTaskAccountChainFindAccounts4 {

   public ListTaskAccountChain(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
   }

}
