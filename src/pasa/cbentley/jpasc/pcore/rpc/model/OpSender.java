package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * OpSender Object, created on version 3.0
 * This object represents an element of the "senders" array for Operation/Multioperation objects
 * @author davidbolet
 *
 */
public class OpSender extends ObjectPCore implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * json=account
    * 
    * Sending account
    */
   protected Integer         account;

   /** 
    * json=amount
    * 
    * Amount of coins transferred from this sender
    * PASCURRENCY - In negative value, due it's outgoing from "account"
    */
   protected Double          amount;

   /**
    * json=amount_s
    */
   private String            amountString;

   /**
    * json=n_operation
    * 
    * n_operation param, the same as aadded in version 2.1.6, but for multioperation
    * n_operation is an incremental value to protect double spend
    */
   protected Integer         nOperation;

   /** 
    * json=payload
    * 
    * HEXASTRING Operation payload introduced by this sender
    */
   protected String          payLoad;

   public OpSender(PCoreCtx pc) {
      super(pc);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (!(obj instanceof OpSender)) {
         return false;
      }
      OpSender other = (OpSender) obj;
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

   public Integer getnOperation() {
      return nOperation;
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

   public void setnOperation(Integer nOperation) {
      this.nOperation = nOperation;
   }

   public void setPayLoad(String payLoad) {
      this.payLoad = payLoad;
   }

}
