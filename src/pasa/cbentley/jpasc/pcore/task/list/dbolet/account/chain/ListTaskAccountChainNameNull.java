/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountName;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching chain accounts without a name.
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainNameNull  extends ListTaskAccountChainFindAccounts4 {


   public ListTaskAccountChainNameNull(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
      FilterAccountName filter = new FilterAccountName(pc, null);
      this.addFilterAccount(filter);
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainNameNull");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainNameNull");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
