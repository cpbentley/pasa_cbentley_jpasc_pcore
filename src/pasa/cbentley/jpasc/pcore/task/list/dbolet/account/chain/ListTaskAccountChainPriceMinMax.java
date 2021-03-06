/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.ITechPascRPC;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountPriceMinMax;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Task for searching wallet accounts being sold within a price range.
 * 
 * @author Charles Bentley
 * 
 * @see FilterAccountPriceMinMax
 */
public class ListTaskAccountChainPriceMinMax extends ListTaskAccountChainFindAccounts8 {

   public ListTaskAccountChainPriceMinMax(PCoreCtx pc, IListListener<Account> listener, Double priceMin, Double priceMax) {
      super(pc, listener);
      this.statusType = ITechPascRPC.STATUS_TYPE_FOR_SALE;
      if(priceMin != null || priceMax != null) {
         this.addFilterAccount(new FilterAccountPriceMinMax(pc, priceMin, priceMax));
      }
   }
   
   protected PagerAbstract<Account> createPagerDefault() {
      Integer numAccounts = pc.getPClient().getBlockCount() * 5;
      PagerAccount pageAccount = new PagerAccount(pc);
      pageAccount.setLookUpRangeStart(0); //start at beginning of chain
      pageAccount.setLookUpRangeEnd(numAccounts);
      pageAccount.setPagerToDefaultAdaptive();
      pageAccount.build();
      return pageAccount;
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainPriceMinMax");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainPriceMinMax");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
