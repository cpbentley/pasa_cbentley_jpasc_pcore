/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.java;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src5.interfaces.INameable;

public class PublicKeyJavaNamer implements INameable<PublicKeyJava> {

   private PublicKeyJava key;

   public PublicKeyJavaNamer(PublicKeyJava key) {
      if (key == null) {
         throw new NullPointerException();
      }
      this.key = key;
   }

   public PublicKeyJava getKey() {
      return key;
   }

   public String getNameableString() {
      return key.name;
   }

   public PublicKeyJava getNamedObject() {
      return key;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PublicKeyJavaNamer");
      toStringPrivate(dc);
      dc.nlLvl(key);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PublicKeyJavaNamer");
      toStringPrivate(dc);
      dc.nlLvl1Line(key);
   }

   public UCtx toStringGetUCtx() {
      return key.toStringGetUCtx();
   }

   //#enddebug

}
