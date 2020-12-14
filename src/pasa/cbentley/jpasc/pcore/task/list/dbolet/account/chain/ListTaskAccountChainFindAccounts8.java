/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Task for listing all accounts on chain using the findAccounts with 8 parameters
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainFindAccounts8 extends ListTaskAccountAbstract {

   protected String  name;

   protected String  nameSearchType;

   protected Integer type = null;

   protected String  statusType;

   protected Double  balanceMin;

   protected Double  balanceMax;

   /**
    * all posi
    * @param pc
    * @param listener
    */
   public ListTaskAccountChainFindAccounts8(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.findAccounts(name, nameSearchType, type, statusType, balanceMin, balanceMax, start, max);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainFindAccounts8");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainFindAccounts8");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
