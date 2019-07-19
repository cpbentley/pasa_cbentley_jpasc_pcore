/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.publickey;

import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.FilterAbstract;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKey;

public class FilterKeyCannotUse extends FilterAbstract implements IFilterPublicKey {

   public FilterKeyCannotUse(PCoreCtx pc) {
      super(pc);
   }

   public boolean filterPublicKey(PublicKey publicKey) {
      return !publicKey.getCanUse();
   }

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterKeyCannotUse");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterKeyCannotUse");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
