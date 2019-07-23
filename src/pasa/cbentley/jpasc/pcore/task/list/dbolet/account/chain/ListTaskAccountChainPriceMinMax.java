/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountPriceMinMax;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for searching wallet accounts being sold within a price range.
 * 
 * @author Charles Bentley
 * 
 * @see FilterAccountPriceMinMax
 */
public class ListTaskAccountChainPriceMinMax extends ListTaskAccountChainFindAccounts8 {

   public ListTaskAccountChainPriceMinMax(PCoreCtx pc, IListListener<Account> listener, Double priceMin, Double priceMax) {
      super(pc, listener);
      this.isListedForSale = true;
      this.addFilterAccount(new FilterAccountPriceMinMax(pc, priceMin, priceMax));
   }
}
