/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.publickeyjava;

import java.util.ArrayList;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.FilterAbstract;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKeyJava;

public abstract class FilterKeyJavaListAbstract extends FilterAbstract implements IFilterPublicKeyJava {

   private ArrayList<PublicKeyJava> keyList;

   public FilterKeyJavaListAbstract(PCoreCtx pc) {
      super(pc);
      keyList = new ArrayList<PublicKeyJava>(2);
   }

   public void addKey(PublicKeyJava publicKeyJava) {
      keyList.add(publicKeyJava);
   }

   public boolean isContainingKeyEncKey(PublicKeyJava key) {
      for (PublicKeyJava keyInside : keyList) {
         if (key.getEncPubKey().equals(keyInside.getEncPubKey())) {
            return true;
         }
      }
      return false;
   }

   public boolean isContainingKeyByName(PublicKeyJava key) {
      for (PublicKeyJava keyInside : keyList) {
         if (key.getName().equals(keyInside.getName())) {
            return true;
         }
      }
      return false;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterKeyJavaListAbstract");
      toStringPrivate(dc);
      super.toString(dc.sup());
      C5Ctx c5 = pc.getC5();
      c5.toStringListStringable(dc, keyList, "List of Keys");
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterKeyJavaListAbstract");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
