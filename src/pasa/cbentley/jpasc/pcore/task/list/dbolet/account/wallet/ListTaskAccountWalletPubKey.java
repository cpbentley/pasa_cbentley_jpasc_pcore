/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClient;
import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;
import com.github.davidbolet.jpascalcoin.exception.RPCApiException;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccount;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Returns all accounts from public keys registered in the daemon. no filtering
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletPubKey extends ListTaskAccountAbstract {

   private PublicKey pubKey;


   public ListTaskAccountWalletPubKey(PCoreCtx pc, IListListener<Account> listener, PublicKey key) {
      super(pc, listener);
      if (key == null) {
         throw new NullPointerException();
      }
      this.pubKey = key;
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.getWalletAccounts(pubKey.getEncPubKey(), null, start, max);
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountWalletPubKey");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountWalletPubKey");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
