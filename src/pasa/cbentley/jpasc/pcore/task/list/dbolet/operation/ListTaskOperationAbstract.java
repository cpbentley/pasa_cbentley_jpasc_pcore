/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterOperation;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.task.ListTaskPage;

/**
 * Stub task for listing accounts
 * @author Charles Bentley
 *
 */
public abstract class ListTaskOperationAbstract extends ListTaskPage<Operation> {

   private IFilterOperation       accountFilter;

   private List<IFilterOperation> accountFilters;


   public ListTaskOperationAbstract(PCoreCtx pc, IListListener<Operation> listener) {
      super(pc, listener);
   }

   public void addFilter(IFilterOperation filter) {
      if (accountFilter == null) {
         accountFilter = filter;
      } else {
         if (accountFilters == null) {
            accountFilters = new ArrayList<IFilterOperation>(2);
            accountFilters.add(accountFilter);
         }
         accountFilters.add(filter);
      }
   }
   protected List<Operation> getFiltered(List<Operation> list) {
      if (accountFilter != null) {
         List<Operation> filteredList = new ArrayList<Operation>(list.size());
         if(accountFilters != null) {
            for (Operation acc : list) {
               //all true logical?
               boolean isAdd = true;
               for (IFilterOperation filter : accountFilters) {
                  isAdd = isAdd && filter.filterOperation(acc);
               }
               if(isAdd) {
                  filteredList.add(acc);
               }
            }
         } else {
            for (Operation acc : list) {
               if (accountFilter.filterOperation(acc)) {
                  filteredList.add(acc);
               }
            }
         }
         return filteredList;
      }
      return list;
   }

   public void filterAndPublish(List<Operation> list) {
      if (accountFilter != null) {
         List<Operation> filteredList = new ArrayList<Operation>(list.size());
         if(accountFilters != null) {
            for (Operation acc : list) {
               //all true logical?
               boolean isAdd = true;
               for (IFilterOperation filter : accountFilters) {
                  isAdd = isAdd && filter.filterOperation(acc);
               }
               if(isAdd) {
                  filteredList.add(acc);
               }
            }
         } else {
            for (Operation acc : list) {
               if (accountFilter.filterOperation(acc)) {
                  filteredList.add(acc);
               }
            }
         }
         publishList(filteredList);
      } else {
         publishList(list);
      }
   }

   public IFilterOperation getAccountFilter() {
      return accountFilter;
   }

   public void setAccountFilter(IFilterOperation accountFilter) {
      this.accountFilter = accountFilter;
   }
}
