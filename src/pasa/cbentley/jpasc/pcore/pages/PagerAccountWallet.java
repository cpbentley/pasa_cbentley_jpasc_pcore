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
public class PagerAccountWallet extends PagerAbstract<Account> {

   public PagerAccountWallet(PCoreCtx pc) {
      super(pc);
   }

   /**
    * In a contiguous list, next 
    * Next start must avoid loops. Consecutive calls to {@link PagerAbstract#getNextStart(List, List)}
    */
   public Integer getNextStart(List<Account> listProcessed, List<Account> listPublished) {
      return lookUpPointer + listProcessed.size();
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "PagerAccountWallet");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PagerAccountWallet");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
