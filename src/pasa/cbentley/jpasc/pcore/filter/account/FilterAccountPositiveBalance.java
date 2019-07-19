/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class FilterAccountPositiveBalance extends FilterAccountAbstract {

   public FilterAccountPositiveBalance(PCoreCtx pc) {
      super(pc);
   }

   public boolean filterAccount(Account account) {
      Double accountBalance = account.getBalance();
      if(accountBalance != null && account.getBalance() > 0) {
         return true;
      }
      return false;
   }

}
