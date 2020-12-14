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
import pasa.cbentley.jpasc.pcore.pages.PagerOperationAccount;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;

/**
 * List all the operations of account.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskOperationByAccount extends ListTaskOperationAbstract {

   private Integer account;

   Integer         depth      = 5000;

   Integer         startBlock = 0;   // 0 means current block here

   public ListTaskOperationByAccount(PCoreCtx pc, IListListener<Operation> listener, Integer account) {
      super(pc, listener);
      this.account = account;
   }

   protected PagerAbstract<Operation> createPagerDefault() {
      PagerOperationAccount pageOperation = new PagerOperationAccount(pc);
      pageOperation.setLookUpRangeStart(0);
      pageOperation.setPageSize(pc.getDefaultPageSizeRootAccountOperations());
      pageOperation.build();
      return pageOperation;
   }

   protected List<Operation> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.getAccountOperations(account, startBlock, depth, start, max);
   }

}
