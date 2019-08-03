/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for listing accounts on chain with names.
 * 
 * Version 4.03 is buggy.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainType extends ListTaskAccountChainFindAccounts8 {

   
   public ListTaskAccountChainType(PCoreCtx pc, IListListener<Account> listener, Integer type) {
      super(pc, listener);
      this.type = type;
   }

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainType");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainType");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
