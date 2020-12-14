/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for listing all accounts of a given key
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountPublicKeyJava extends ListTaskAccountChainArrayValue {

   private PublicKeyJava key;
   
   public ListTaskAccountPublicKeyJava(PCoreCtx pc, IListListener<Account> listener, PublicKeyJava key) {
      super(pc, listener, key.getAccounts());
      this.key = key;
   }


   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountPublicKeyJava");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountPublicKeyJava");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
