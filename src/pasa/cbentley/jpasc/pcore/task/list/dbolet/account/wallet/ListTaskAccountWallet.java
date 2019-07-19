/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;
import com.github.davidbolet.jpascalcoin.exception.RPCApiException;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Base Task for listing accounts of a wallet for all keys or a given set of keys
 * 
 * The order of the accounts will depend on the key paging.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWallet extends ListTaskAccountAbstract {

   public ListTaskAccountWallet(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
   }

  
   public void runAbstract() {
      IPascalCoinClient pclient = pc.getPClient();
      PagerPublicKey pageAccount = new PagerPublicKey(pc);
      List<PublicKey> list = null;
      List<PublicKey> listFiltered = null;
//      boolean hasMoreDataPages = false;
//      do {
//         try {
//            Integer start = pageAccount.getStart();
//            Integer max = pageAccount.getMax();
//            list = pclient.getWalletPubKeys(start, max);
//            if (list == null) {
//               //warn and silently returns
//               //#debug
//               toDLog().pNull("list of wallet pub key is null", pclient, ListTaskAccountWallet.class, "runAbstract", LVL_09_WARNING, false);
//               return;
//            }
//            for (PublicKey pubKey : list) {
//               runAbstractForKey(pclient, pubKey);
//            }
//         } catch (RPCApiException e) {
//            list = null; //make sure the list is null to get out of the loop
//            e.printStackTrace();
//            //#debug
//            toDLog().pEx(pageAccount.toString(), this, ListTaskAccountWallet.class, "runAbstract", e);
//         }
//
//         //automatically false when list is null or empty
//         hasMoreDataPages = pageAccount.pageTurnInLoop(list, listFiltered);
//
//      } while (hasMoreDataPages && isContinue());
   }
   
   private void runAbstractForKey(IPascalCoinClient pclient, PublicKey pubKey) {
      //only list from can use
      if (pubKey == null) {
         //warn and continue
         //warn and silently returns
         //#debug
         toDLog().pNull("pubKey is null", pclient, ListTaskAccountWallet.class, "runAbstractForKey", LVL_09_WARNING, false);
         return;
      }
      //even with a filter, this class only accept
      //hardcoded security. even if someone puts a public key in the filter, it won't go through
      Boolean canUse = pubKey.getCanUse();
      if (canUse == null) {
         //warn and continue
         //#debug
         toDLog().pNull("pubKey canUse null", pclient, ListTaskAccountWallet.class, "runAbstractForKey", LVL_09_WARNING, false);
         return;
      }
      if (canUse) {
         if (filterSetKey != null) {
            PublicKeyJava keyJava = pc.getDomainMapper().mapPublicKey(pubKey);
            boolean isKeyRejected = !filterSetKey.filterPublicKey(keyJava);
            if (isKeyRejected) {
               return;
            }
         }
         //list accounts of those keys. use a sub task
         ListTaskAccountWalletPubKey accountPubKeyListTask = new ListTaskAccountWalletPubKey(pc, getListener(), pubKey);
         accountPubKeyListTask.setFilterSetAccount(this.filterSetAccount);
         accountPubKeyListTask.runAsSubTaskOf(this);
      }
   }


   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      // TODO Auto-generated method stub
      return null;
   }
}
