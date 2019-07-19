/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.operation;

import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.OperationSubType;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class FilterOperationBySubtype1OrSubtype2 extends FilterOperationAbstract {

   private OperationSubType subtype1;

   private OperationSubType subtype2;

   public FilterOperationBySubtype1OrSubtype2(PCoreCtx pc, OperationSubType subtype1, OperationSubType subtype2) {
      super(pc);
      this.subtype1 = subtype1;
      this.subtype2 = subtype2;
   }

   public boolean filterOperation(Operation operation) {
      if (operation.getSubType() == subtype1 || operation.getSubType() == subtype2) {
         return true;
      }
      return false;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterOperationBySubtype1OrSubtype2");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.nlLvlO(subtype1, "OperationSubType 1");
      dc.nlLvlO(subtype2, "OperationSubType 2");
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterOperationBySubtype1OrSubtype2");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
