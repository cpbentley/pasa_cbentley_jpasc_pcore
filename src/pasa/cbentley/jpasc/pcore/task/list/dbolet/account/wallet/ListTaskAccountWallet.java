/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerStack;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.pages.PagerAccountWallet;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;
import pasa.cbentley.jpasc.pcore.task.list.java.key.ListTaskPublicKeyJavaWalletCanUse;

/**
 * Base Task for listing accounts of a wallet for all keys or a given set of keys
 * 
 * The order of the accounts will depend on the key paging.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountWallet extends ListTaskAccountAbstract implements IListListener<Account> {

   ListTaskAccountWalletPubKey          accountPubKeyListTask;

   private ArrayList<Account>           accounts = new ArrayList<Account>();

   private ListenerStack<PublicKeyJava> stack;

   public ListTaskAccountWallet(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
      stack = new ListenerStack<PublicKeyJava>(pc);
   }

   private PagerAccountWallet createPagerAccount(IPascalCoinClient client, PublicKeyJava key, ListTaskAccountWalletPubKey oldTask) {

      Integer numAccounts = client.getWalletAccountsCount(key.getEncPubKey(), null);
      PagerAccountWallet pagerAccount = new PagerAccountWallet(pc);
      //this class will operate the pager "manually"
      pagerAccount.setLookUpRangeStart(0);
      pagerAccount.setLookUpRangeEnd(numAccounts);
      pagerAccount.setManualExactPageSize(true); //manual paging
      pagerAccount.setTimingEnabled(true);
      pagerAccount.setPageTimingMin(250);
      pagerAccount.setPageSizeMax(10000);
      if (oldTask == null) {
         pagerAccount.setPageSize(10);
      } else {
         PagerAbstract<Account> pagerOld = oldTask.getPager();
         pagerAccount.setPageSize(pagerOld.getMax());
      }
      pagerAccount.build();

      return pagerAccount;
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer pageSize) {
      while (accounts.size() < pageSize && accountPubKeyListTask != null) {
         //we need more account from wallet keys from current key
         accountPubKeyListTask.runAbstract();
         //how to know if
         if (accountPubKeyListTask.getPager().isLookUpRemaining()) {
            //continue with curren task
         } else {
            ListTaskAccountWalletPubKey old = accountPubKeyListTask;
            accountPubKeyListTask = getNextKeyTask(client, old);
         }
      }

      int size = Math.min(pageSize, accounts.size());
      List<Account> listReturn = new ArrayList<>(size);
      Iterator<Account> it = accounts.iterator();
      int count = size;
      while (count >= 0 && it.hasNext()) {
         Account key = it.next();
         listReturn.add(key);
         it.remove();
         count -= 1;
      }
      return listReturn;
   }

   private ListTaskAccountWalletPubKey getNextKeyTask(IPascalCoinClient client, ListTaskAccountWalletPubKey oldTask) {
      Stack<PublicKeyJava> stackKeys = stack.getStack();
      if (stackKeys.isEmpty()) {
         return null;
      } else {
         PublicKeyJava pop = stackKeys.pop();
         ListTaskAccountWalletPubKey task = new ListTaskAccountWalletPubKey(pc, this, pop);
         PagerAccountWallet pager = createPagerAccount(client, pop, oldTask);
         task.setPager(pager);
         return task;
      }
   }

   public void newDataAvailable(List<Account> list) {
      accounts.addAll(list);
   }

   public void runAbstract() {

      //first get a full list of all keys
      ListTaskPublicKeyJavaWalletCanUse taskWalletKey = new ListTaskPublicKeyJavaWalletCanUse(pc, stack);
      taskWalletKey.setPublicKeyFilterJava(filterSetKey);
      taskWalletKey.runAbstract();

      //we have all our keys
      accountPubKeyListTask = getNextKeyTask(pc.getPClient(), null);

      super.runAbstract();
   }

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountWallet");
      toStringPrivate(dc);
      super.toString(dc.sup());
      
      dc.nlLvl(accountPubKeyListTask);
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountWallet");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
