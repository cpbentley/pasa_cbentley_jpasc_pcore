/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.operations;

import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.PayLoadEncryptionMethod;
import com.github.davidbolet.jpascalcoin.exception.RPCApiException;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * For
 * <pre>
 *  Operation changeAccountInfo(
 *      Integer accountTarget, 
 *      Integer accountSigner, 
 *      String newEncPubKey, 
 *      String newB58PubKey, 
 *      String newName, 
 *      Short newType, 
 *      Double fee, 
 *      byte[] payload, 
 *      PayLoadEncryptionMethod payloadMethod, 
 *      String pwd);
 * </pre> 
 * @author Charles Bentley
 *
 */
public class OperationJavaChangeAccountInfo extends OperationJavaAbstract {

   private Integer                 accountSigner   = null;

   private Integer                 accountTarget   = null;

   private Double                  fee             = null;

   private String                  newB58PubKey    = null;

   private String                  newEncPubKey    = null;

   private String                  newName         = null;

   private Short                   newType         = null;

   private byte[]                  payloadData     = null;

   private PayLoadEncryptionMethod payloadMethod   = null;

   private String                  payloadPassword = null;

   /**
    * At min
    * @param pc
    */
   public OperationJavaChangeAccountInfo(PCoreCtx pc) {
      super(pc);
   }

   /**
    * Same thread
    */
   public boolean execute() {
      try {
         Operation op = getClient().changeAccountInfo(accountTarget, accountSigner, newEncPubKey, newB58PubKey, newName, newType, fee, payloadData, payloadMethod, payloadPassword);
         if (op != null) {
            pc.getLog().consoleLogGreen("changeAccountInfo operation successfull newName=" + newName + " newType=" + newType + " newEncPubKey=" + newEncPubKey);
            pc.getPasaServices().registerAccountInPendingOperations(op);
            return true;
         } else {
            pc.getLog().consoleLogError("changeAccountInfo operation failed. Null Returned");
         }
      } catch (RPCApiException e) {
         e.printStackTrace();
         pc.getLog().consoleLogError("RPCApiException: changeAccountInfo operation failed " + e.getMessage());
      }
      return false;
   }

   public Integer getAccountSigner() {
      return accountSigner;
   }

   public Integer getAccountTarget() {
      return accountTarget;
   }

   public Double getFee() {
      return fee;
   }

   public String getNewB58PubKey() {
      return newB58PubKey;
   }

   public String getNewEncPubKey() {
      return newEncPubKey;
   }

   public String getNewName() {
      return newName;
   }

   public Short getNewType() {
      return newType;
   }

   public byte[] getPayloadData() {
      return payloadData;
   }

   public PayLoadEncryptionMethod getPayloadMethod() {
      return payloadMethod;
   }

   public String getPayloadPassword() {
      return payloadPassword;
   }

   public void setAccountSigner(Integer accountSigner) {
      this.accountSigner = accountSigner;
   }

   public void setAccountTarget(Integer accountTarget) {
      this.accountTarget = accountTarget;
   }

   public void setFee(Double fee) {
      this.fee = fee;
   }

   public void setNewB58PubKey(String newB58PubKey) {
      this.newB58PubKey = newB58PubKey;
   }

   public void setNewEncPubKey(String newEncPubKey) {
      this.newEncPubKey = newEncPubKey;
   }

   public void setNewName(String newName) {
      this.newName = newName;
   }

   public void setNewType(Short newType) {
      this.newType = newType;
   }

   public void setPayloadData(byte[] payloadData) {
      this.payloadData = payloadData;
   }

   public void setPayloadMethod(PayLoadEncryptionMethod payloadMethod) {
      this.payloadMethod = payloadMethod;
   }

   public void setPayloadPassword(String payloadPassword) {
      this.payloadPassword = payloadPassword;
   }

}
