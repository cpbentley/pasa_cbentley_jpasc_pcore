/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.operations;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;

/**
 * Represents the RPC call sendTo
 * <pre>
 * Operation sendTo(
 *      Integer sender, 
 *      Integer target, 
 *      Double amount, 
 *      Double fee, 
 *      byte[] payload, 
 *      PayLoadEncryptionMethod payloadMethod, 
 *      String pwd);
 * </pre>     
 * @author Charles Bentley
 *
 */
public class OperationJavaBuyAccount extends OperationJavaAbstract {

   private Account accountBought;

   private Account accountBuyer;

   private Integer accountToPurchase;

   private Double  amount;

   private Integer buyerAccount;

   private String  newB58PubKey;

   private String  newEncPubKey;

   private Double  price;

   private Integer sellerAccount;

   public OperationJavaBuyAccount(PCoreCtx pc) {
      super(pc);
   }

   public void doValidateParam(PublicKey pkNew, Account buyer, Account bought, Double priceAndFunding, Double fee) {

      this.accountBuyer = buyer;
      this.accountBought = bought;
      
      //#debug
      toDLog().pFlow(toString(pkNew, buyer, bought, fee), null, OperationJavaBuyAccount.class, "doValidateParam", ITechLvl.LVL_08_INFO, true);

      newEncPubKey = pkNew.getEncPubKey();
      buyerAccount = buyer.getAccount();
      amount = priceAndFunding;
      price = bought.getPrice();
      sellerAccount = bought.getSellerAccount();
      accountToPurchase = bought.getAccount();
      
      this.setFee(fee);
      
      if(newEncPubKey == null) {
         throw new NullPointerException("newEncPubKey");
      }
      if(price == null) {
         throw new NullPointerException("price");
      }
      if(fee == null) {
         throw new NullPointerException("fee");
      }
      if(buyerAccount == null) {
         throw new NullPointerException("buyerAccount");
      }
      setPreValidation();
   }

   protected void executeOperation() {
      op = pc.getPClient().buyAccount(buyerAccount, accountToPurchase, price, sellerAccount, newB58PubKey, newEncPubKey, amount, fee, payload, payloadMethod, pwd);
   }

   protected void executePostSuccess() {
      pc.getPasaServices().registerAccountInPendingOperations(op);
   }

   public Account getAccountBought() {
      return accountBought;
   }

   public Account getAccountBuyer() {
      return accountBuyer;
   }

   public Double getAmount() {
      return amount;
   }

   public String getMessage() {
      if (op != null) {
         return "Buy transaction of " + accountToPurchase + " sent successfully " + op.getTypeDescriptor();
      } else {
         return "sendTo operation failed. Null Returned";
      }
   }


   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "OperationJavaBuyAccount");
      super.toString(dc.sup());
      toStringPrivate(dc);
      dc.nl();
      dc.appendVarWithSpace("buyerAccount", buyerAccount);
      dc.appendVarWithSpace("sellerAccount", sellerAccount);
      dc.nl();
      dc.appendVarWithSpace("newEncPubKey", newEncPubKey);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "OperationJavaBuyAccount");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("price", price);
      dc.appendVarWithSpace("accountToPurchase", accountToPurchase);
   }
   //#enddebug

}
