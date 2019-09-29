/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import java.util.ArrayList;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccountArray;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;

/**
 * Task for listing all accounts of a given key
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainArrayValue extends ListTaskAccountAbstract {

   private int[] accountsArray;

   public ListTaskAccountChainArrayValue(PCoreCtx pc, IListListener<Account> listener, int[] accounts) {
      super(pc, listener);
      this.accountsArray = accounts;
   }

   protected PagerAbstract<Account> createPagerDefault() {
      PagerAccountArray pageAccount = new PagerAccountArray(pc);
      pageAccount.setLookUpRangeStart(0); //start at beginning of chain
      pageAccount.setLookUpRangeEnd(accountsArray.length - 1);
      pageAccount.setPagerToDefaultAdaptive();
      pageAccount.build();
      return pageAccount;
   }

   protected List<Account> findItems(IPascalCoinClient client, Integer start, Integer pageSize) {
      ArrayList<Account> accounts = new ArrayList<Account>(pageSize);
      int end = Math.min(accountsArray.length, start + pageSize + 1);
      for (int j = start; j < end; j++) {
         Integer account = accountsArray[j];
         Account ac = client.getAccount(account);
         if (ac != null) {
            accounts.add(ac);
         }
      }
      return accounts;
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
