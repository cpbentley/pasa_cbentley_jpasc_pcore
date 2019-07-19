/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.publickeyjava;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKeyJava;

public class FilterKeyJavaNegative extends FilterKeyJavaListAbstract implements IFilterPublicKeyJava {

   public FilterKeyJavaNegative(PCoreCtx pc) {
      super(pc);
   }

   public boolean filterPublicKey(PublicKeyJava publicKey) {
      boolean isContained = isContainingKeyEncKey(publicKey);
      if (isContained) {
         return false;
      }
      return true;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterKeyJavaNegative");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterKeyJavaNegative");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
