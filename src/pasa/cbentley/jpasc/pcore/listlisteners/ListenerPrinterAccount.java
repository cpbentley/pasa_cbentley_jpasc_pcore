/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

public class ListenerPrinterAccount implements IListListener<Account> {

   protected final PCoreCtx pc;

   private int              counter = 0;

   public ListenerPrinterAccount(PCoreCtx pc) {
      this.pc = pc;
   }

   public void newDataAvailable(List<Account> list) {
      //#debug
      toDLog().pTest(list.size() + "\t Listing Accounts in newly fed list", null, ListenerPrinterAccount.class, "newDataAvailable", LVL_05_FINE, true);
      for (Account object : list) {
         String str = counter + "\t\t" + object.getAccount() + " " + object.getName();
         //#debug
         toDLog().pTest(str, null, ListenerPrinterAccount.class, "newDataAvailable", LVL_05_FINE, true);
         counter++;
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
      dc.root(this, "ListenerPrinter");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListenerPrinter");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
