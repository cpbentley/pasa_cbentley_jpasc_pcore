/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation;

import java.util.List;

import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.predicates.BlockPredicate;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerOperation;
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;

/**
 * Find blocks that match this {@link BlockPredicate}. List operations all those blocks.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskOperationByBlockPredicate extends ListTaskOperationAbstract {

   private BlockPredicate predicate;

   public ListTaskOperationByBlockPredicate(PCoreCtx pc, IListListener<Operation> listener, BlockPredicate predicate) {
      super(pc, listener);
      this.predicate = predicate;
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
