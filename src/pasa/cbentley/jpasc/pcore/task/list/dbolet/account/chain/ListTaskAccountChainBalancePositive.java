/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching wallet account with a specific name {@link IStrAcceptor}
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainBalancePositive extends ListTaskAccountChainBalanceMinMax {


   public ListTaskAccountChainBalancePositive(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener,0.0001, null);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainBalancePositive");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainBalancePositive");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
