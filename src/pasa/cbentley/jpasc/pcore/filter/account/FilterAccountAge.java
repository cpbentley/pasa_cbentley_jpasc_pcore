/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

public class FilterAccountAge extends FilterAccountAbstract {

   private Integer blockReference;
   //null if none
   private Integer ageMinimum;
   //null if none
   private Integer ageMaximum;

   public FilterAccountAge(PCoreCtx pc, Integer blockReference, Integer ageMinimum, Integer ageMaximum) {
      super(pc);
      this.blockReference = blockReference;
      this.ageMinimum = ageMinimum;
      this.ageMaximum = ageMaximum;
   }

   public boolean filterAccount(Account account) {
      Integer lastBlock = account.getUpdatedB();
      if(lastBlock == null) {
         return true; //assume error. return it
      }
      int difference = blockReference - lastBlock;
      if(ageMinimum != null) {
         if(difference < ageMinimum) {
            return false;
         }  
      }
      if(ageMaximum != null) {
         if(difference > ageMaximum) {
            return false;
         }
      }
      return true;
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterAccountAge");
      toStringPrivate(dc);
      super.toString(dc.sup());
      
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("blockReference", blockReference);
      dc.appendVarWithSpace("ageMinimum", ageMinimum);
      dc.appendVarWithSpace("ageMaximum", ageMaximum);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterAccountAge");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
