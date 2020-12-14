package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * OpChanger Object, created on version 3.0
 * This object represents an element of the "changers" array for Operation/Multioperation objects
 * @author davidbolet
 *
 */
public class OpChanger extends ObjectPCore implements Serializable {

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
    * json=account_price
    * 
    * If is listed for sale (public or private) will show account price
    */
   protected Double          accountPrice;

   /** 
    * json=fee
    * 
    * Fee of this operation, in negative value, due it's outgoing from "account" (PASCURRENCY)
   */
   protected Double          fee;

   /**
    * json=locked_until_block
    * 
    * If is listed for for private sale will show block locked
    */
   protected Integer         lockedUntilBlock;

   /**
    * json=new_enc_pubkey
    * 
    * new_enc_pubkey, if public key is changed or when is listed for a private sale
    */
   protected String          newEncPubkey;

   /**
    * json=new_name
    * 
    * new_name, if name is changed
    */
   protected String          newName;

   /**
    * json=new_type
    * 
    * new type, if type is changed
    */
   protected Integer         newType;

   /**
    * json=n_operation
    * 
    * n_operation param, the same as aadded in version 2.1.6, but for multioperation
    * n_operation is an incremental value to protect double spend
    */
   protected Integer         nOperation;

   /**
    * json=seller_account
    * 
    * If is listed for sale (public or private) will show seller account
    */
   protected Integer         sellerAccount;

   public OpChanger(PCoreCtx pc) {
      super(pc);
   }

   public Integer getAccount() {
      return account;
   }

   public Double getAccountPrice() {
      return accountPrice;
   }

   public Double getFee() {
      return fee;
   }

   public Integer getLockedUntilBlock() {
      return lockedUntilBlock;
   }

   public String getNewEncPubkey() {
      return newEncPubkey;
   }

   public String getNewName() {
      return newName;
   }

   public Integer getNewType() {
      return newType;
   }

   public Integer getnOperation() {
      return nOperation;
   }

   public Integer getSellerAccount() {
      return sellerAccount;
   }

   public void setAccount(Integer account) {
      this.account = account;
   }

   public void setAccountPrice(Double accountPrice) {
      this.accountPrice = accountPrice;
   }

   public void setFee(Double fee) {
      this.fee = fee;
   }

   public void setLockedUntilBlock(Integer lockedUntilBlock) {
      this.lockedUntilBlock = lockedUntilBlock;
   }

   public void setNewEncPubkey(String newEncPubkey) {
      this.newEncPubkey = newEncPubkey;
   }

   public void setNewName(String newName) {
      this.newName = newName;
   }

   public void setNewType(Integer newType) {
      this.newType = newType;
   }

   public void setnOperation(Integer nOperation) {
      this.nOperation = nOperation;
   }

   public void setSellerAccount(Integer sellerAccount) {
      this.sellerAccount = sellerAccount;
   }

}
