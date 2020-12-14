/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation;

import java.util.List;

import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterOperation;
import pasa.cbentley.jpasc.pcore.interfaces.IListenerBlock;
import pasa.cbentley.jpasc.pcore.interfaces.IListenerOperation;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerBlocks;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerOperations;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.task.ListTask;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.block.ListTaskBlockAbstract;

/**
 * List operations looking at a block range 
 * 
 * for the number givens type
 * @author Charles Bentley
 *
 */
public class ListTaskOperationByFilter extends ListTaskOperationAbstract implements IListenerBlock, IListenerOperation {

   private ListTaskBlockAbstract blocks;

   private ListenerBlocks         listenerBlocks;

   private ListenerOperations     listenerOperations;

   private IFilterOperation       filterOperation;

   public ListTaskOperationByFilter(PCoreCtx pc, IListListener<Operation> listener, ListTaskBlockAbstract blocks, IFilterOperation filter) {
      super(pc, listener);
      this.blocks = blocks;
      listenerBlocks = new ListenerBlocks(pc, this);
      listenerOperations = new ListenerOperations(pc, this);
      blocks.setListener(listenerBlocks);
      
      this.filterOperation = filter;
   }

   /**
    * we listen to found blocks which are sent to {@link ListTaskOperationByFilter#listenTo(Block)} 
    * 
    */
   public void runAbstract() {
      blocks.runAbstract();
   }

   public void listenTo(Operation operation) {
      if (filterOperation != null) {
         //add to list waiting to be send out.. maybe immediately
         boolean isAccepted = filterOperation.filterOperation(operation);
         if (isAccepted) {
            publish(operation);
         }
      } else {
         publish(operation);
      }
   }

   /**
    * Operations are sent to {@link ListTaskOperationByFilter#listenTo(Operation)}
    */
   public void listenTo(Block block) {
      ListTaskOperationBlock task = new ListTaskOperationBlock(pc, listenerOperations, block.getBlock());
      task.runAbstract();

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
