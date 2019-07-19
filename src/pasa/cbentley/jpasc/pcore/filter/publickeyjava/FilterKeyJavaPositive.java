/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.publickeyjava;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKeyJava;

/**
 * True if key is in the list
 * @author Charles Bentley
 *
 */
public class FilterKeyJavaPositive extends FilterKeyJavaListAbstract implements IFilterPublicKeyJava {

   public FilterKeyJavaPositive(PCoreCtx pc) {
      super(pc);
   }

   public boolean filterPublicKey(PublicKeyJava publicKey) {
      boolean isContained = isContainingKeyEncKey(publicKey);
      if (isContained) {
         return true;
      }
      return false;
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterKeyJavaPositive");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterKeyJavaPositive");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}
