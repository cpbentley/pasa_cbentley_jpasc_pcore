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
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;

/**
 * Task
 * @author Charles Bentley
 *
 */
public class ListTaskOperationPending extends ListTaskOperationAbstract {

   public ListTaskOperationPending(PCoreCtx pc, IListListener<Operation> listener) {
      super(pc, listener);
   }

   protected List<Operation> findItems(IPascalCoinClient client, Integer start, Integer max) {
      //the risk here is that the pager wants a given number of operations but
      //it has changed because a block as been mined. so when a 
      return client.getPendings(start,max);
   }

   protected PagerAbstract<Operation> createPagerDefault() {
      IPascalCoinClient pclient = pc.getPClient();
      int numOperationsPending = pclient.getPendingsCount();
      PagerOperation pageOperation = new PagerOperation(pc);
      pageOperation.setLookUpRangeEnd(numOperationsPending);
      pageOperation.setPagerToDefaultAdaptive();
      pageOperation.build();
      return pageOperation;
   }
   
   protected boolean isTaskShouldStop() {
      int numOperationsPending =  pc.getPClient().getPendingsCount();
      if(numOperationsPending == 0) {
         return true;
      }
      if(numOperationsPending != pager.getLookUpRangeEnd()) {
         return true;
      }
      return false;
   }

}
