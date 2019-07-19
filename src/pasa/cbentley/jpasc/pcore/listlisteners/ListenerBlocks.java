/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Block;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IListenerBlock;

public class ListenerBlocks extends ListListenerAbstract implements IListListener<Block> {

   private IListenerBlock listener;

   public ListenerBlocks(PCoreCtx pc, IListenerBlock listener) {
      super(pc);
      this.listener = listener;
   }

   public void newDataAvailable(List<Block> list) {
      for (Block block : list) {
         listener.listenTo(block);
      }
   }

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListenerBlocks");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListenerBlocks");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
