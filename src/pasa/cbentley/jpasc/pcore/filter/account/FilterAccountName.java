/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * Filter accounts based on name String.
 * If name is null, then accounts without a name will be filtered as true.
 * @author Charles Bentley
 *
 */
public class FilterAccountName extends FilterAccountAbstract {


   private String name;

   public FilterAccountName(PCoreCtx pc, String name) {
      super(pc);
      this.name = name;
   }

   public boolean filterAccount(Account account) {
      String nameAccount = account.getName();
      if (this.name == null) {
         if (nameAccount == null || nameAccount.equals("")) {
            return true; 
         } else {
            return false;
         }
      } else {
         if(nameAccount.indexOf(this.name) != -1) {
            return true;
         } else {
            return false;
         }
      }
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterAccountName");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterAccountName");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
