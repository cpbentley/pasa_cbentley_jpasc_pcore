/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for searching chain for 1 account with a exactly the name
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainNameExact extends ListTaskAccountChainName  {


   public ListTaskAccountChainNameExact(PCoreCtx pc, IListListener<Account> listener, String name) {
      super(pc, listener, name, true);
   }

}
