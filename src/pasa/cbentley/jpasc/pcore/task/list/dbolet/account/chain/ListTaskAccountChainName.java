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
 * Task for listing accounts on chain with names.
 * 
 * Update for V5 and 
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_EXACT}
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_CONTAINS}
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_STARTSWITH}
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_ENDS_WITH}
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_NOT_CONTAINS}
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_NOT_ENDS_WITH}
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_NOT_STARTSWITH}
 * 
 * V5 does not support
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_NONE}
 * <li> {@link ITechPascRPC#NAMESEARCHTYPE_ANY}
 * 
 * Version 4.03 is buggy.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainName extends ListTaskAccountChainFindAccounts8 {

   
   public ListTaskAccountChainName(PCoreCtx pc, IListListener<Account> listener, String name, String nameSearchType) {
      super(pc, listener);
      this.name = name;
      this.nameSearchType = nameSearchType;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainName");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainName");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
