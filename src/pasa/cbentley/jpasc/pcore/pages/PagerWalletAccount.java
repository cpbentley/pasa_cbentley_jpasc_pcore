/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * A pager of {@link Account} over several keys
 * 
 * @author Charles Bentley
 *
 */
public class PagerWalletAccount extends PagerAbstract<Account> {
   
   private PagerAbstract<PublicKey> pagerKey;
   
   private PublicKey keyCurrent;
   private PagerAbstract<Account> pagerAccount;
   
   
   public PagerAbstract<Account> getPagerAccount() {
      return pagerAccount;
   }

   public PublicKey getKeyCurrent() {
      return keyCurrent;
   }

   public PagerWalletAccount(PCoreCtx pc) {
      super(pc);
   }
   
   public PagerAbstract<PublicKey> getPagerKey(){
      return pagerKey;
   }
   /**
    * In a contiguous list, next 
    * Next start must avoid loops. Consecutive calls to {@link PagerAbstract#getNextStart(List, List)}
    */
   public Integer getNextStart(List<Account> listProcessed, List<Account> listPublished) {
      int indexLastAccountProcessed = listProcessed.size() - 1;
      Account account = listProcessed.get(indexLastAccountProcessed);
      if (account != null) {
         return account.getAccount() + 1;
      }
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
