/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

import java.math.BigDecimal;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Encapsulate to make easier to change implementation/molina precision.
 * 
 * @author Charles Bentley
 *
 */
public class PascalCoinValue implements IStringable {

   private PCoreCtx   pc;

   private BigDecimal val;

   public PascalCoinValue(PCoreCtx pc, Double value) {
      this(pc, value.doubleValue());
   }

   public PascalCoinValue(PCoreCtx pc, double value) {
      this.pc = pc;
      if (value == 0.0d) {
         val = BigDecimal.ZERO;
      } else {
         val = BigDecimal.valueOf(value); //do NOT use the double construct
      }
   }

   public PascalCoinValue(PCoreCtx pc, BigDecimal val) {
      this.pc = pc;
      this.val = val; //do NOT use the double construct
   }

   public double getDouble() {
      return val.doubleValue();
   }

   public String getString() {
      return val.toPlainString();
   }

   public PascalCoinValue multiply(int i) {
      if (val == BigDecimal.ZERO) {
         //special case optimization
         return this;
      } else {
         BigDecimal newVal = this.val.multiply(new BigDecimal(i));
         return new PascalCoinValue(pc, newVal);
      }
   }

   /**
    * Mutates the value. Not thread safe
    * @param balance
    */
   public void addMutable(double balance) {
      BigDecimal bal = BigDecimal.valueOf(balance);
      val = val.add(bal);
   }

   public void addMutable(Double balance) {
      this.addMutable(balance.doubleValue());
   }

   public PascalCoinValue add(PascalCoinValue pcv) {
      if (pcv.val == BigDecimal.ZERO) {
         //special case optimization
         return this;
      } else {
         BigDecimal newVal = this.val.add(pcv.val);
         return new PascalCoinValue(pc, newVal);
      }
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PascalCoinValue");
      dc.appendVarWithSpace("doubleValue", val.doubleValue());
      dc.appendVarWithSpace("intValue", val.intValue());
      dc.appendVarWithSpace("intValueExact", val.intValueExact());

   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PascalCoinValue");
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }
   //#enddebug

}
