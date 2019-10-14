/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Dummy {@link ListTaskPage} that does nothing.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskPageCustomRunEmpty<T> extends ListTaskPageCustomRun<PublicKeyJava>  {


   public ListTaskPageCustomRunEmpty(PCoreCtx pc, IListListener<PublicKeyJava> listener) {
      super(pc, listener);
   }

   /**
    * Nothing to list in this dummy task
    */
   public void runAbstractCustom() {
      
   }

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskPageCustomRunEmpty");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskPageCustomRunEmpty");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}
