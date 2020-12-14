package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * OpReceiver Object, created on version 3.0
 * This object represents an element of the "receivers" array for Operation/Multioperation objects
 * @author davidbolet
 *
 */
public class OpReceiver extends ObjectPCore implements Serializable {

   public OpReceiver(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long       serialVersionUID = 1L;

   /**
    * json=account
    * 
    * Sending account
    */
   protected Integer               account;

   private Integer                 payLoadType;

   /**
    * json=payload_type
    */
   private PayLoadEncryptionMethod payLoadEncMethod;

   /** 
    * json=amount
    * 
    * Amount of coins transferred to this sender
    * PASCURRENCY - In positive value, as we are receiving them
    */
   protected Double                amount;

   /**
    * json=amount_s
    * 
    * Sending account
    */
   protected String                amountString;

   /** 
    * json=payload
    * 
    * HEXASTRING Operation payload for this receiver
    */
   protected String                payLoad;

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (!(obj instanceof OpReceiver)) {
         return false;
      }
      OpReceiver other = (OpReceiver) obj;
      if (account == null) {
         if (other.account != null) {
            return false;
         }
      } else if (!account.equals(other.account)) {
         return false;
      }
      return true;
   }

   public Integer getAccount() {
      return account;
   }

   public Double getAmount() {
      return amount;
   }

   public String getAmountString() {
      return amountString;
   }

   public String getPayLoad() {
      return payLoad;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((account == null) ? 0 : account.hashCode());
      return result;
   }

   public void setAccount(Integer account) {
      this.account = account;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
   }

   public void setAmountString(String amountString) {
      this.amountString = amountString;
   }

   public void setPayLoad(String payLoad) {
      this.payLoad = payLoad;
   }


   public void setPayloadEncryption(String str) {
      if (str.equals("none")) {
         setPayLoadEncMethod(PayLoadEncryptionMethod.NONE);
      } else if (str.equals("none")) {

      }
   }


   public PayLoadEncryptionMethod getPayLoadEncMethod() {
      return payLoadEncMethod;
   }

   public void setPayLoadEncMethod(PayLoadEncryptionMethod payLoadEncMethod) {
      this.payLoadEncMethod = payLoadEncMethod;
   }

   public Integer getPayLoadType() {
      return payLoadType;
   }

   public void setPayLoadType(Integer payLoadType) {
      this.payLoadType = payLoadType;
   }

}
