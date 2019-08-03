/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountEncPubKey;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for listing all accounts of a given key
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainKey extends ListTaskAccountChainFindAccounts4 {


   public ListTaskAccountChainKey(PCoreCtx pc, IListListener<Account> listener, PublicKeyJava key) {
      super(pc, listener);
      this.addFilterAccount(new FilterAccountEncPubKey(pc, key.getEncPubKey()));
   }

   public ListTaskAccountChainKey(PCoreCtx pc, IListListener<Account> listener, PublicKey key) {
      super(pc, listener);
      this.addFilterAccount(new FilterAccountEncPubKey(pc, key.getEncPubKey()));
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainKey");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainKey");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

   
}
