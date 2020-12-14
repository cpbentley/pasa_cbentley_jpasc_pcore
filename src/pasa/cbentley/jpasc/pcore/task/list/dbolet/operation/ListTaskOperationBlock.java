/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation;

import java.util.List;

import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerOperation;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;

/**
 * Task that gets operations on a block period.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskOperationBlock extends ListTaskOperationAbstract {

   private Integer blockInteger;

   public ListTaskOperationBlock(PCoreCtx pc, IListListener<Operation> listener, Integer block) {
      super(pc, listener);
      this.blockInteger = block;
   }

   protected List<Operation> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return  client.getBlockOperations(blockInteger, start, max);
   }

   protected PagerAbstract<Operation> createPagerDefault() {
      IPascalCoinClient pclient = pc.getPClient();
      Block block = pclient.getBlock(blockInteger);
      PagerOperation pageOperation = new PagerOperation(pc);
      pageOperation.setLookUpRangeEnd(block.getOperationCount());
      pageOperation.build();
      return pageOperation;
   }


}
