/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Pager when listing operations from blocks
 * @author Charles Bentley
 *
 */
public class PagerOperationAccount extends PagerAbstract<Operation> {


   public PagerOperationAccount(PCoreCtx pc) {
      super(pc); 
   }
   
   public Integer getNextStart(List<Operation> listProcessed, List<Operation> listFiltered) {
      return getCountProcessed();
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "PageOperation");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PageOperation");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }
   //#enddebug
   

}
