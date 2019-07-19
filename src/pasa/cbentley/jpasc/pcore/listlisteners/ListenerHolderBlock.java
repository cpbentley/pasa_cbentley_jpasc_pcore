/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import com.github.davidbolet.jpascalcoin.api.model.Block;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class ListenerHolderBlock extends ListenerHolder<Block> {

   public ListenerHolderBlock(PCoreCtx pc) {
      super(pc);
   }

}
