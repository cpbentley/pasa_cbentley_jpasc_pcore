/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccount;
import pasa.cbentley.jpasc.pcore.filter.SetFilterAccount;
import pasa.cbentley.jpasc.pcore.filter.SetFilterKey;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.task.ListTask;
import pasa.cbentley.jpasc.pcore.task.ListTaskPage;

/**
 * Stub task for listing accounts, chain or wallet or anything.
 * 
 * <br>
 * <br>
 * Merely provides a template with filters sets
 * @author Charles Bentley
 *
 */
public abstract class ListTaskAccountAbstract extends ListTaskPage<Account> {

   protected SetFilterAccount filterSetAccount;

   protected SetFilterKey     filterSetKey;

   public ListTaskAccountAbstract(PCoreCtx pc, IListListener<Account> listener) {
      super(pc, listener);
   }

   public void addFilterAccount(IFilterAccount filter) {
      if (filterSetAccount == null) {
         filterSetAccount = new SetFilterAccount(pc);
      }
      filterSetAccount.addFilter(filter);
   }

   protected PagerAbstract<Account> createPagerDefault() {
      Integer numAccounts = pc.getPClient().getBlockCount() * 5;
      PagerAccount pageAccount = new PagerAccount(pc);
      pageAccount.setLookUpRangeStart(0); //start at beginning of chain
      pageAccount.setLookUpRangeEnd(numAccounts);
      pageAccount.setTimingEnabled(true);
      pageAccount.setManualExactPageSize(false);
      pageAccount.setPageSize(pc.getDefaultPageSizeRootAccount());
      pageAccount.build();
      return pageAccount;
   }

   protected List<Account> getFiltered(List<Account> list) {
      if (filterSetAccount != null && list != null) {
         List<Account> filteredList = new ArrayList<Account>(list.size());
         for (Account acc : list) {
            if (filterSetAccount.filterAccount(acc)) {
               filteredList.add(acc);
               //#debug
               toDLog().pData("ACCEPTED " + pc.toPD().d1(acc), null, ListTaskAccountAbstract.class, "filterAndPublish", LVL_03_FINEST, true);
            } else {
               //#debug
               toDLog().pData("REJECTED " + pc.toPD().d1(acc), null, ListTaskAccountAbstract.class, "filterAndPublish", LVL_03_FINEST, true);
            }
         }
         return filteredList;
      } else {
         return list;
      }
   }

   public SetFilterAccount getFilterSetAccount() {
      return filterSetAccount;
   }

   public SetFilterKey getFilterSetKeys() {
      return filterSetKey;
   }

   /**
    * if null, removes any previous filters
    * @param filterSet
    */
   public void setFilterKeySet(SetFilterKey filterSet) {
      this.filterSetKey = filterSet;
   }

   public void setFilterSetAccount(SetFilterAccount filterSet) {
      this.filterSetAccount = filterSet;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountAbstract");
      toStringPrivate(dc);
      super.toString(dc.sup());

      dc.nlLvl("FilterSet for Accounts", filterSetAccount);
      dc.nlLvl("FilterSet for Keys", filterSetKey);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountAbstract");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
