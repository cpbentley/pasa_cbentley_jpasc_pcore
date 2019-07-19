/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Task for listing all accounts on chain using the findAccounts with 8 parameters
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainFindAccounts8 extends ListTaskAccountAbstract {

   protected String  name;

   protected Boolean isExactMatch;

   protected Integer type = null;

   protected Boolean isListedForSale;

   protected Double  balanceMin;

   protected Double  balanceMax;

   /**
    * all posi
    * @param pc
    * @param listener
    */
   public ListTaskAccountChainFindAccounts8(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.findAccounts(name, isExactMatch, type, isListedForSale, balanceMin, balanceMax, start, max);
   }

}
