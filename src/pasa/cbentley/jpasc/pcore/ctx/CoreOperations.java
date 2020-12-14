package pasa.cbentley.jpasc.pcore.ctx;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.rpc.model.PayLoadEncryptionMethod;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;

public class CoreOperations implements IStringable {

   protected final PCoreCtx pc;

   public CoreOperations(PCoreCtx pc) {
      this.pc = pc;

   }

   public boolean buyAccount(PublicKey pkNew, Account buyer, Account bought, Double priceAndFunding, Double fee) {

      String newEncPubKey = pkNew.getEncPubKey();
      String newB58PubKey = null;
      Integer buyerAccount = buyer.getAccount();
      byte[] payload = null;
      PayLoadEncryptionMethod payloadMethod = null;
      String pwd = null;
      Double amount = priceAndFunding;
      Account acToBuy = bought;
      Double price = acToBuy.getPrice();
      Integer sellerAccount = acToBuy.getSellerAccount();
      Integer accountToPurchase = acToBuy.getAccount();
      Operation op = pc.getPClient().buyAccount(buyerAccount, accountToPurchase, price, sellerAccount, newB58PubKey, newEncPubKey, amount, fee, payload, payloadMethod, pwd);
      if (op != null) {
         pc.getLog().consoleLogGreen("Buy transaction of " + acToBuy.getAccount() + " sent successfull " + op.getTypeDescriptor());
         return true;
      } else {
         pc.getLog().consoleLogGreen("Buy tx failed to be sent");
         return false;
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
      dc.root(this, "CoreOperations");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "CoreOperations");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   //#enddebug

}
