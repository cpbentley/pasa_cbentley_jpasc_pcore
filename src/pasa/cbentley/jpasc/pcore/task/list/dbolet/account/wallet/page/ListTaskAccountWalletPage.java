/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.page;

import java.util.List;

import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolder;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKey;
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.pages.PagerAccountWallet;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.ListTaskAccountWalletPubKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.ListTaskPublicKeyWalletCanUse;

/**
 * Base Task for listing accounts of a wallet for all keys or a given set of keys
 * 
 * The order of the accounts will depend on the key paging.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWalletPage extends ListTaskAccountAbstract implements IListListener<PublicKey> {

   public ListTaskAccountWalletPage(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
   }

  
   public void runAbstract() {
//      PagerAccountWallet pagerMain = (PagerAccountWallet) getPager();
//      if(pagerMain.getPagerKey() == null) {
//         //start a new key task 
//         ListTaskPublicKeyWalletCanUse task = new ListTaskPublicKeyWalletCanUse(pc, this);
//         task.setPager(pagerMain.getPagerKey());
//         task.runAsSubTaskOf(this);
//      } else {
//         PublicKey pk = pagerMain.getKeyCurrent();
//         if(pk != null) {
//            //
//            ListTaskAccountWalletPubKey accountPubKeyListTask = new ListTaskAccountWalletPubKey(pc, getListener(), pk);
//            accountPubKeyListTask.setFilterSetAccount(this.filterSetAccount);
//            accountPubKeyListTask.runAsSubTaskOf(this);
//         }
//      }
   }
   
   private void runAbstractForKey(IPascalCoinClient pclient, PublicKey pubKey) {
      //only list from can use
      if (pubKey == null) {
         //warn and continue
         //warn and silently returns
         //#debug
         toDLog().pNull("pubKey is null", pclient, ListTaskAccountWalletPage.class, "runAbstractForKey", LVL_09_WARNING, false);
         return;
      }
      //even with a filter, this class only accept
      //hardcoded security. even if someone puts a public key in the filter, it won't go through
      Boolean canUse = pubKey.getCanUse();
      if (canUse == null) {
         //warn and continue
         //#debug
         toDLog().pNull("pubKey canUse null", pclient, ListTaskAccountWalletPage.class, "runAbstractForKey", LVL_09_WARNING, false);
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
         ListTaskAccountWalletPubKeyPage accountPubKeyListTask = new ListTaskAccountWalletPubKeyPage(pc, getListener(), pubKey);
         PagerAccountWallet pagerMain = (PagerAccountWallet) getPager();
         //accountPubKeyListTask.setPager(pagerMain.getPagerAccount());
         accountPubKeyListTask.setFilterSetAccount(this.filterSetAccount);
         accountPubKeyListTask.runAsSubTaskOf(this);
      }
   }



   public void newDataAvailable(List<PublicKey> list) {
      //add to pager
      
      for (PublicKey key : list) {
         //create a new page for that key
      }
   }


   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer max) {
      // TODO Auto-generated method stub
      return null;
   }
}
