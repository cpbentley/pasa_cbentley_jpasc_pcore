/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClient;
import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountAge;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;

/**
 * Task for listing accounts on chain with an age filter
 * @author Charles Bentley
 *
 */
public class ListTaskAccountChainAge extends ListTaskAccountChainFindAccounts4 {


   public ListTaskAccountChainAge(PCoreCtx pc, IListListener<Account> listener, Integer ageMinimum, Integer ageMaximum) {
      super(pc, listener);
      
      Integer blockReference = pc.getPClient().getBlockCount();
      FilterAccountAge filter = new FilterAccountAge(pc, blockReference , ageMinimum, ageMaximum);
      this.addFilterAccount(filter);
   }
   

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskAccountChainAge");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskAccountChainAge");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
