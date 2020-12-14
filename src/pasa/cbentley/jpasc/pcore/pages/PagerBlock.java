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
public class PagerBlock extends PagerAbstract<Block> {

   public PagerBlock(PCoreCtx pc) {
      super(pc);
   }

   public PagerBlock(PCoreCtx pc, Integer startBlock, Integer endBlock, Integer pageSize) {
      super(pc, startBlock, pageSize);
      int numTotalBlocks = endBlock - startBlock + 1;
      setLookUpRangeEnd(numTotalBlocks);
   }

   public Integer getID(Block block) {
      return block.getBlock();
   }
   /**
    * block processed from down to bottom?
    */
   public Integer getNextStart(List<Block> listProcessed, List<Block> listPublished) {
      if (isAscending()) {
         //when ascending, first is lowest, last is highest
         int indexLastBlockProcessed = listProcessed.size()-1; 
         Block block = listProcessed.get(indexLastBlockProcessed);
         if (block != null) {
            return block.getBlock() + 1;
         }
         return getCountProcessed() + 1;
      } else {
         //when descending.. first is highest, last is the lowest
         int indexLastBlockProcessed = listProcessed.size()-1; 
         Block block = listProcessed.get(indexLastBlockProcessed);
         if (block != null) {
            return block.getBlock() - pageSizeActive;
         }
         return getCountProcessed() - 1;
      }
   }

}
