/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccountArray;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Returns all accounts from public keys registered in the daemon. no filtering.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletPubKey extends ListTaskAccountAbstract {

   private String encPubKey;

   public ListTaskAccountWalletPubKey(PCoreCtx pc, IListListener<Account> listener, PublicKey key) {
      this(pc, listener, key.getEncPubKey());
   }

   public ListTaskAccountWalletPubKey(PCoreCtx pc, IListListener<Account> listener, PublicKeyJava key) {
      this(pc, listener, key.getEncPubKey());
   }

   public ListTaskAccountWalletPubKey(PCoreCtx pc, IListListener<Account> listener, String encPubKey) {
      super(pc, listener);
      if (encPubKey == null) {
         throw new NullPointerException();
      }
      this.encPubKey = encPubKey;
   }

   protected PagerAbstract<Account> createPagerDefault() {
      Integer numAccounts = pc.getPClient().getWalletAccountsCount(encPubKey,null);
      PagerAccountArray pageAccount = new PagerAccountArray(pc);
      pageAccount.setLookUpRangeStart(0); //start at beginning of chain
      pageAccount.setLookUpRangeEnd(numAccounts);
      pageAccount.setPagerToDefaultAdaptive();
      pageAccount.build();
      return pageAccount;
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.getWalletAccounts(encPubKey, null, start, max);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, ListTaskAccountWalletPubKey.class, "@line64");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ListTaskAccountWalletPubKey.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   


}
