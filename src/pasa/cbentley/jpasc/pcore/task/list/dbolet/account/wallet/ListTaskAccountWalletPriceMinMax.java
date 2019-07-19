/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountPriceMinMax;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for searching wallet accounts being sold in a price range.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletPriceMinMax extends ListTaskAccountWallet {


   public ListTaskAccountWalletPriceMinMax(PCoreCtx pc, IListListener<Account> listener,Double minPrice, Double maxPrice) {
      super(pc, listener);
      this.addFilterAccount(new FilterAccountPriceMinMax(pc, minPrice, maxPrice));
   }

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountWalletForSale");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountWalletForSale");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
