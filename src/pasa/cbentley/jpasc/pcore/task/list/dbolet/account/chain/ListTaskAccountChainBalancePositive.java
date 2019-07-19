/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for searching wallet account with a specific name {@link IStrAcceptor}
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainBalancePositive extends ListTaskAccountChainBalanceMinMax {


   public ListTaskAccountChainBalancePositive(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener,0.0001, null);
   }

}
