/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.actions;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPayload;
import pasa.cbentley.jpasc.pcore.domain.java.PayloadJava;
import pasa.cbentley.jpasc.pcore.domain.operations.OperationJavaSendTo;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.utils.PascalCoinValue;

/**
 * 
 * Intermdiady between UI and operation object.
 * Provides validation services.
 * 
 * <br>
 * For mass sending, use the corresponding mass send UserAction.
 * 
 * Used as a validator as well when . It is an additional barrier to mistakes.
 * <br>
 * We are never enough on the safe side in crypto.
 * <br>
 *  
 * @author Charles Bentley
 *
 */
public class ActionSendCoins extends ActionAbstract {


   private String          fromAccountName;

   private String          toAccountName;

   private Integer         fromAccountInteger;

   private Integer         toAccountInteger;

   private Account         from;

   private Account         to;

   private PascalCoinValue amount;

   private PascalCoinValue fee;

   private PayloadJava     payload;

   public ActionSendCoins(PCoreCtx pc) {
      super(pc);
   }

   public Account getFrom() {
      return from;
   }

   public void setFrom(Account from) {
      this.from = from;
   }

   public Account getTo() {
      return to;
   }

   /**
    * 
    * @param to
    */
   public void setTo(Account to) {
      this.to = to;
   }

   public PascalCoinValue getAmount() {
      return amount;
   }

   public void setAmount(PascalCoinValue amount) {
      this.amount = amount;
   }

   public PascalCoinValue getFee() {
      return fee;
   }

   public void setFee(PascalCoinValue fee) {
      this.fee = fee;
   }

   public PayloadJava getPayload() {
      return payload;
   }

   public void setPayload(PayloadJava payload) {
      this.payload = payload;
   }

   public void cmdSend() {
      OperationJavaSendTo sendOperation = new OperationJavaSendTo(pc);

      //mandatory otherwise.. action fails
      sendOperation.setSender(from.getAccount());
      sendOperation.setTarget(to.getAccount());
      sendOperation.setAmount(amount.getDouble());

      //if no fee.. set it to zero
      sendOperation.setFee(fee.getDouble());

      if (payload != null) {
         int type = payload.getPayloadEncryption();
         if (type == IPayload.PAYLOAD_ENCRYPTION_03_AES) {
            sendOperation.setPwd(payload.getPassword());
         }
         sendOperation.setPayloadEncryptionMethod(type);
         //encodes the data as 
         sendOperation.setPayload(payload.getPayload());
      }
      sendOperation.execute();

   }

   public boolean isValid() {
      return false;
   }

   public String getFromAccountName() {
      return fromAccountName;
   }

   public void setFromAccountName(String fromAccountName) {
      this.fromAccountName = fromAccountName;
   }

   public String getToAccountName() {
      return toAccountName;
   }

   public void setToAccountName(String toAccountName) {
      this.toAccountName = toAccountName;
   }

   public Integer getFromAccountInteger() {
      return fromAccountInteger;
   }

   public void setFromAccountInteger(Integer fromAccountInteger) {
      this.fromAccountInteger = fromAccountInteger;
   }

   public Integer getToAccountInteger() {
      return toAccountInteger;
   }

   public void setToAccountInteger(Integer toAccountInteger) {
      this.toAccountInteger = toAccountInteger;
   }
}
