/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * True if price is included in the price range inclusive.
 * When null. no minimum or maximum
 * @author Charles Bentley
 *
 */
public class FilterAccountPriceMinMax extends FilterAccountAbstract {

   private Double priceMin;

   private Double priceMax;

   public FilterAccountPriceMinMax(PCoreCtx pc, Double priceMin, Double priceMax) {
      super(pc);
      this.priceMin = priceMin;
      this.priceMax = priceMax;
   }

   public boolean filterAccount(Account account) {
      Double price = account.getPrice();
      if (price != null) {
         double priceValue = price.doubleValue();
         if (priceMin != null && priceValue < priceMin.doubleValue()) {
            return false;
         }
         if (priceMax != null && priceValue > priceMax.doubleValue()) {
            return false;
         }
         return true;
      }
      return false;
   }
   

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterAccountPriceMinMax");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("priceMin", priceMin);
      dc.appendVarWithSpace("priceMax", priceMax);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterAccountPriceMinMax");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   



}
