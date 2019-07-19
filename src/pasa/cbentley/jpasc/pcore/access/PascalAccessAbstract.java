/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.access;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessPascal;

public abstract class PascalAccessAbstract implements IStringable {

   protected final PCoreCtx pc;

   public PascalAccessAbstract(PCoreCtx pc) {
      this.pc = pc;
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PascalAccessAbstract");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public IDLog toDLog() {
      return pc.toDLog();
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PascalAccessAbstract");
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }
   //#enddebug

}
