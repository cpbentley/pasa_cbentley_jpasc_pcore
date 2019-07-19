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
public class PagerOperation extends PagerAbstract<Operation> {

   public PagerOperation(PCoreCtx pc, Integer numOpererations) {
      super(pc, 0, Math.min(numOpererations, 10)); //small s
      setLookUpRangeEnd(numOpererations);
   }

   public PagerOperation(PCoreCtx pc) {
      super(pc); 
   }
   
   public Integer getNextStart(List<Operation> listProcessed, List<Operation> listFiltered) {
      int size = listProcessed.size();
      Operation last = listProcessed.get(size - 1); //operations are listed in ascending order
      return last.getOperationBlock() + 1;
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
