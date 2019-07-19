/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.bo;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccountBO;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.task.ListTask;

public abstract class ListTaskAccountBOAbstract extends ListTask<AccountBO> {

   public ListTaskAccountBOAbstract(PCoreCtx pc, IListListener<AccountBO> listener) {
      super(pc, listener);
   }

   private IFilterAccountBO accountFilter;

   public void filterAndPublish(List<AccountBO> list) {
      if (accountFilter != null) {
         List<AccountBO> filteredList = new ArrayList<AccountBO>(list.size());
         for (AccountBO acc : list) {
            if (accountFilter.filterAccount(acc)) {
               filteredList.add(acc);
            }
         }
         publishList(filteredList);
      } else {
         publishList(list);
      }
   }

   public IFilterAccountBO getAccountFilter() {
      return accountFilter;
   }

   public void setAccountFilter(IFilterAccountBO accountFilter) {
      this.accountFilter = accountFilter;
   }
}
