/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountPriceMinMax;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;

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
      if(priceMin != null || priceMax != null) {
         this.addFilterAccount(new FilterAccountPriceMinMax(pc, priceMin, priceMax));
      }
   }
   
   protected PagerAbstract<Account> createPagerDefault() {
      Integer numAccounts = pc.getPClient().getBlockCount() * 5;
      PagerAccount pageAccount = new PagerAccount(pc);
      pageAccount.setLookUpRangeStart(0); //start at beginning of chain
      pageAccount.setLookUpRangeEnd(numAccounts);
      pageAccount.setTimingEnabled(true);
      pageAccount.setPageTimingMin(250);
      pageAccount.setManualExactPageSize(false);
      pageAccount.setPageSize(1);
      pageAccount.build();
      return pageAccount;
   }
}
