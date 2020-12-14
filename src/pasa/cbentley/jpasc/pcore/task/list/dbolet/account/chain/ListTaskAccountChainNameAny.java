/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.ITechPascRPC;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountName;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountNameExist;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching chain account with a match for name. RPC check
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainNameAny extends ListTaskAccountChainFindAccounts4  {


   public ListTaskAccountChainNameAny(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
      FilterAccountNameExist filter = new FilterAccountNameExist(pc);
      this.addFilterAccount(filter);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainNameAny");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainNameAny");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
