/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.ITechPascRPC;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching chain for 1 account with a exactly the name
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainNameExact extends ListTaskAccountChainName  {


   public ListTaskAccountChainNameExact(PCoreCtx pc, IListListener<Account> listener, String name) {
      super(pc, listener, name, ITechPascRPC.NAMESEARCHTYPE_EXACT);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainNameExact");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainNameExact");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
