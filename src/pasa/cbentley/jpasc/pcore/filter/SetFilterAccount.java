/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class SetFilterAccount implements IFilterAccount {

   private IFilterAccount       accountFilter;

   private List<IFilterAccount> accountFilters;

   private PCoreCtx pc;

   public SetFilterAccount(PCoreCtx pc) {
      this.pc = pc;

   }

   public void addFilter(IFilterAccount filter) {
      if (accountFilter == null) {
         accountFilter = filter;
      } else {
         if (accountFilters == null) {
            accountFilters = new ArrayList<IFilterAccount>(2);
            accountFilters.add(accountFilter);
         }
         accountFilters.add(filter);
      }
   }

   public boolean filterAccount(Account account) {
      if (accountFilters == null) {
         if (accountFilter == null) {
            return true;
         } else {
            return accountFilter.filterAccount(account);
         }
      } else {
         //all filters must return true
         for (IFilterAccount filter : accountFilters) {
            if(!filter.filterAccount(account)) {
               return false;
            }
         }
         return true;
      }
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "SetFilterAccount");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "SetFilterAccount");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
