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
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.task.ListTask;

/**
 * From a given block, find up/down the first block with non zero operations
 * @author Charles Bentley
 *
 */
public class ListTaskOperationByNonEmptyBlock extends ListTaskOperationAbstract {

   private Integer blockInteger;

   private boolean isAscending;

   private int     minimumOperationCount;

   public ListTaskOperationByNonEmptyBlock(PCoreCtx pc, IListListener<Operation> listener, Integer block) {
      super(pc, listener);
      this.blockInteger = block;
      minimumOperationCount = 1;
      isAscending = true;
   }

   /**
    * By default it will be 1
    * @return
    */
   public int getMinimumOperationCount() {
      return minimumOperationCount;
   }

   /**
    * If true, search up from given block
    * @return
    */
   public boolean isAscending() {
      return isAscending;
   }


   public void setAscending(boolean isAscending) {
      this.isAscending = isAscending;
   }

   public void setMinimumOperationCount(int minimumOperationCount) {
      this.minimumOperationCount = minimumOperationCount;
   }

   protected PagerAbstract<Operation> createPagerDefault() {
      // TODO Auto-generated method stub
      return null;
   }

   protected List<Operation> findItems(IPascalCoinClient client, Integer start, Integer max) {
      // TODO Auto-generated method stub
      return null;
   }

}
