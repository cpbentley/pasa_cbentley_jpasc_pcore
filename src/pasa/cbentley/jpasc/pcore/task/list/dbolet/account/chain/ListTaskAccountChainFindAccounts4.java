/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Task for listing all accounts on chain
 * 
 * {@link IListListener} will get some accounts when the {@link PagerAccount} max has been reached.
 * 
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainFindAccounts4 extends ListTaskAccountAbstract {

   /**
    * If has value, will return the account that match name
    */
   protected String name;
   
   /**
    * If has value, will return accounts with same type
    */
   protected Integer type;
   /**
    * all posi
    * @param pc
    * @param listener
    */
   public ListTaskAccountChainFindAccounts4(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.findAccounts(name, type, start, max);
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainFindAccounts4");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainFindAccounts4");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}
