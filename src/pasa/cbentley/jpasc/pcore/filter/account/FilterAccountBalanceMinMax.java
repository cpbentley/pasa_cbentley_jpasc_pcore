/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * True if balance is included in the balance range inclusive.
 * <br>
 * When null. no minimum or maximum
 * @author Charles Bentley
 *
 */
public class FilterAccountBalanceMinMax extends FilterAccountAbstract {

   private Double balanceMin;

   private Double balanceMax;

   public FilterAccountBalanceMinMax(PCoreCtx pc, Double priceMin, Double priceMax) {
      super(pc);
      this.balanceMin = priceMin;
      this.balanceMax = priceMax;
   }

   public boolean filterAccount(Account account) {
      Double balance = account.getBalance();
      if (balance != null) {
         double balanceValue = balance.doubleValue();
         if (balanceMin != null && balanceValue < balanceMin.doubleValue()) {
            return false;
         }
         if (balanceMax != null && balanceValue > balanceMax.doubleValue()) {
            return false;
         }
         return true;
      }
      return false;
   }

}
