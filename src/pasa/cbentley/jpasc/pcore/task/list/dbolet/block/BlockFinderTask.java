/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.block;

import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.predicates.BlockPredicate;
import pasa.cbentley.jpasc.pcore.interfaces.IObjectListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.task.PCoreTask;

/**
 * Task
 * @author Charles Bentley
 *
 */
public class BlockFinderTask extends PCoreTask {

   private BlockPredicate         blockPredicate;

   private Integer                current;

   private IObjectListener<Block> listener;

   /**
    * Callback for posting results
    * @param pc
    * @param listener
    * @param block
    */
   public BlockFinderTask(PCoreCtx pc, IObjectListener<Block> listener, BlockPredicate blockPredicate) {
      super(pc);
      this.listener = listener;
      this.blockPredicate = blockPredicate;
   }

   /**
    * null if none is possible
    * @return
    */
   private Integer getNextBlock() {
      if (current == null) {
         current = blockPredicate.getStartingBlock();
      }
      if (blockPredicate.isUp()) {
         current++;
      } else {
         current--;
      }
      return current;
   }

   public void runAbstract() {
      IPascalCoinClient pclient = pc.getPClient();
      Integer blockInteger = null;
      while (isContinue() && (blockInteger = getNextBlock()) != null) {

         Block block = pclient.getBlock(blockInteger);
         boolean isValid = blockPredicate.isValidNumOperations(block.getOperationCount());
         if (isValid) {
            listener.newObjectAvailable(block, blockPredicate);
            //how many founds?
         } else {
            //we want to notify failure
            listener.newObjectfail(block, blockPredicate);
         }
      }
   }

}
