/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.access;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessAccountDBolet;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessBlockDBolet;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessPascal;

public class AccessPascalPrivate extends PascalAccessAbstract implements IAccessPascal {

   private AccessAccountDBoletRPCChain accessAccountRPC;

   private AccessBlockRPC   accessBlockRPC;

   public AccessPascalPrivate(PCoreCtx pc) {
      super(pc);

   }

   public IAccessAccountDBolet getAccessAccountDBolet() {
      if (accessAccountRPC == null) {
         accessAccountRPC = new AccessAccountDBoletRPCChain(pc);
      }
      return accessAccountRPC;
   }

   public IAccessBlockDBolet getAccessBlockDBolet() {
      if (accessBlockRPC == null) {
         accessBlockRPC = new AccessBlockRPC(pc);
      }
      return accessBlockRPC;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "AccessPascalPrivate");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AccessPascalPrivate");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
