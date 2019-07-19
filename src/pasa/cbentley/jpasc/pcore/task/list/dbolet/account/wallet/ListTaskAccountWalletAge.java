/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountAge;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for listing accounts on chain with an age filter
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletAge extends ListTaskAccountWallet {


   public ListTaskAccountWalletAge(PCoreCtx pc, IListListener<Account> listener, Integer ageMinimum, Integer ageMaximum) {
      super(pc, listener);
      
      Integer blockReference = pc.getPClient().getBlockCount();
      FilterAccountAge filter = new FilterAccountAge(pc, blockReference , ageMinimum, ageMaximum);
      this.addFilterAccount(filter);
   }
}
