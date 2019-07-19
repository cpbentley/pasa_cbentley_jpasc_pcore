/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.operation;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class FilterOperationAmountAbs extends FilterOperationAbstract {

   private double amount;

   public FilterOperationAmountAbs(PCoreCtx pc, double amount) {
      super(pc);
      this.amount = amount;
   }

   public boolean filterOperation(Operation operation) {
      Double opAm = operation.getAmount();
      double doubleAbsValue = Math.abs(opAm.doubleValue());
      if (doubleAbsValue >= amount) {
         return true;
      }
      return false;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterOperationAmountAbs");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("amount", amount);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterOperationAmountAbs");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
