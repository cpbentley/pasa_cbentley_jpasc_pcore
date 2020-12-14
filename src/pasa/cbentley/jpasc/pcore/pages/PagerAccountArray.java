/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;

/**
 * A page of {@link Account} the next page starts 
 * @author Charles Bentley
 *
 */
public class PagerAccountArray extends PagerAbstract<Account> {
   
   public PagerAccountArray(PCoreCtx pc) {
      super(pc);
   }
   
   /**
    * When the total number of account is known
    * @param pc
    * @param numAccounts the total number of accounts to 
    */
   public PagerAccountArray(PCoreCtx pc, Integer numAccounts) {
      super(pc, 0, Math.min(numAccounts, 10));
      setLookUpRangeEnd(numAccounts);
   }

   /**
    * 
    * @param pc
    * @param numAccounts
    * @param pageStartingSize
    */
   public PagerAccountArray(PCoreCtx pc, Integer numAccounts, Integer pageStartingSize) {
      super(pc, 0, Math.min(numAccounts, pageStartingSize));
      setLookUpRangeEnd(numAccounts);
   }

   public Integer getID(Account account) {
      return account.getAccount();
   }
   
   /**
    * In a contiguous list, next 
    * Next start must avoid loops. Consecutive calls to {@link PagerAbstract#getNextStart(List, List)}
    */
   public Integer getNextStart(List<Account> listProcessed, List<Account> listPublished) {
      return getCountProcessed();
   }

   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "PagerAccount");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PagerAccount");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
