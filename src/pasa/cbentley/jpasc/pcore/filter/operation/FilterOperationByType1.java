/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.operation;

import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.OperationSubType;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class FilterOperationByType1 extends FilterOperationAbstract {

   private OperationSubType subtype;

   public FilterOperationByType1(PCoreCtx pc, OperationSubType subtype) {
      super(pc);
      this.subtype = subtype;
   }

   public boolean filterOperation(Operation operation) {
      if (operation.getSubType() == subtype) {
         return true;
      }
      return false;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterOperationByType1");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.nlLvlO(subtype, "OperationSubType");
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterOperationByType1");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
