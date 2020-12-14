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
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.ListTask;

/**
 * List operations where a PASA was sent by one of the private keys
 * @author Charles Bentley
 *
 */
public class ListTaskOperationPASAReceived extends ListTaskOperationAbstract {

   private PublicKey key;

   public ListTaskOperationPASAReceived(PCoreCtx pc, IListListener<Operation> listener, PublicKey key) {
      super(pc, listener);
      this.key = key;
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
