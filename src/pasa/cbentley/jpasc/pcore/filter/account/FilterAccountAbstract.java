/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.FilterAbstract;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccount;

public abstract class FilterAccountAbstract extends FilterAbstract implements IFilterAccount {

   public FilterAccountAbstract(PCoreCtx pc) {
      super(pc);
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterAccountAbstract");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterAccountAbstract");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
