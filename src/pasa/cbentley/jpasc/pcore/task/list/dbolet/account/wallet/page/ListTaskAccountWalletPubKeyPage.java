/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.page;

import java.util.List;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Returns all accounts from public keys registered in the daemon. no filtering
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletPubKeyPage extends ListTaskAccountAbstract {

   private PublicKey pubKey;

   public ListTaskAccountWalletPubKeyPage(PCoreCtx pc, IListListener<Account> listener, PublicKey key) {
      super(pc, listener);
      if (key == null) {
         throw new NullPointerException();
      }
      this.pubKey = key;
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.getWalletAccounts(pubKey.getEncPubKey(), null, start, max);
   }

   protected PagerAbstract<Account> createPagerDefault() {
      Integer numAccounts = pc.getPClient().getWalletAccountsCount(pubKey.getEncPubKey(), null);
      PagerAccount pageAccount = new PagerAccount(pc);
      pageAccount.setLookUpRangeStart(0); //start at beginning of chain
      pageAccount.setLookUpRangeEnd(numAccounts);
      pageAccount.setTimingEnabled(true);
      pageAccount.setManualExactPageSize(false);
      pageAccount.setPageSize(pc.getDefaultPageSizeRootAccount());
      pageAccount.build();
      return pageAccount;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountWalletPubKey");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountWalletPubKey");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
   }
   //#enddebug

}
