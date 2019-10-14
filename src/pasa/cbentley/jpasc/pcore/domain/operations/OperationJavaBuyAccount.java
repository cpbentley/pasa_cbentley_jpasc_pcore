/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.operations;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.PayLoadEncryptionMethod;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;
import com.github.davidbolet.jpascalcoin.exception.RPCApiException;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.jpasc.pcore.ctx.CoreOperations;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPayload;

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

   private Integer buyerAccount;

   private Integer accountToPurchase;

   private Double  price;

   private Double  amount;

   private Integer sellerAccount;

   private String  newEncPubKey;

   private String  newB58PubKey;

   private Account accountBuyer;

   private Account accountBought;

   
   public OperationJavaBuyAccount(PCoreCtx pc) {
      super(pc);
   }

   
   public void doValidateParam(PublicKey pkNew, Account buyer, Account bought, Double priceAndFunding, Double fee) {
      
      this.accountBuyer = buyer;
      this.accountBought = bought;
      //#debug
      toDLog().pTest(toString(pkNew, buyer, bought, fee), null, OperationJavaBuyAccount.class, "doValidateParam", ITechLvl.LVL_08_INFO, true);
      
      newEncPubKey = pkNew.getEncPubKey();
      buyerAccount = buyer.getAccount();
      amount = priceAndFunding;
      price = bought.getPrice();
      sellerAccount = bought.getSellerAccount();
      accountToPurchase = bought.getAccount();
      
      setPreValidation();
   }

   protected void executeOperation() {
      op = pc.getPClient().buyAccount(buyerAccount, accountToPurchase, price, sellerAccount, newB58PubKey, newEncPubKey, amount, fee, payload, payloadMethod, pwd);
   }
   
   protected void executePostSuccess() {
      pc.getPasaServices().registerAccountInPendingOperations(op);
   }
   
   public String getMessage() {
      if(op != null) {
         return "Buy transaction of " + accountToPurchase + " sent successfull " + op.getTypeDescriptor();
      } else {
         return "sendTo operation failed. Null Returned";
      }
   }
   
   public Double getAmount() {
      return amount;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
   }


   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "SendToOperation");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("amount", amount);
      dc.appendVarWithSpace("fee", fee);
      dc.appendVarWithSpace("pwd", pwd);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "SendToOperation");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }
   //#enddebug

   public int getPayloadEncryptionMethod() {
      return payloadEncryptionMethod;
   }


   public Account getAccountBought() {
      return accountBought;
   }


   public Account getAccountBuyer() {
      return accountBuyer;
   }

}
