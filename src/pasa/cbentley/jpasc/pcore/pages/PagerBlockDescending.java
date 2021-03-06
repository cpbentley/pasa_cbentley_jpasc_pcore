/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;

/**
 * Page will automatically compute the next value of max
 * @author Charles Bentley
 *
 */
public class PagerBlockDescending extends PagerAbstract<Block> {

   public PagerBlockDescending(PCoreCtx pc) {
      super(pc);
   }


   /**
    * block processed from down to bottom?
    */
   public Integer getNextStart(List<Block> listProcessed, List<Block> listPublished) {
      int indexLastBlockProcessed = listProcessed.size() - 1;
      Block block = listProcessed.get(indexLastBlockProcessed);
      if (block != null) {
         return block.getBlock() - 1;
      }
      return getNextStartDefautAscending();
   }
}
