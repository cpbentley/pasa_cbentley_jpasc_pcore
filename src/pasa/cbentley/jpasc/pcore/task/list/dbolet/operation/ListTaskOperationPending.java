/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerOperation;

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
      return client.getPendings();
   }

   protected PagerAbstract<Operation> createPagerDefault() {
      IPascalCoinClient pclient = pc.getPClient();
      int numOperationsPending = pclient.getPendingsCount();
      PagerOperation pageOperation = new PagerOperation(pc);
      pageOperation.setLookUpRangeEnd(numOperationsPending);
      pageOperation.build();
      return pageOperation;
   }

}
