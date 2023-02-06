/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.operations;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.rpc.model.PayLoadEncryptionMethod;

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

   private Integer accountSigner = null;

   private Integer accountTarget = null;

   private String  newB58PubKey  = null;

   private String  newEncPubKey  = null;

   private String  newName       = null;

   private Short   newType       = null;

   /**
    * At min
    * @param pc
    */
   public OperationJavaChangeAccountInfo(PCoreCtx pc) {
      super(pc);
   }


   protected void executeOperation() {
      op = getClient().changeAccountInfo(accountTarget, accountSigner, newEncPubKey, newB58PubKey, newName, newType, fee, payload, payloadMethod, pwd);
   }

   protected void executePostSuccess() {
      pc.getPasaServices().registerAccountInPendingOperations(op);
   }
   
   public void doValidateParams() {
      if(accountTarget == null) {
         throw new NullPointerException();
      }
      if((newType == null && newName == null) && (newEncPubKey == null && newB58PubKey == null)) {
         throw new NullPointerException();
      }
      isParamValidated = true;
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

   public PayLoadEncryptionMethod getPayloadMethod() {
      return payloadMethod;
   }

   public void setAccountSigner(Integer accountSigner) {
      this.accountSigner = accountSigner;
   }

   public void setAccountTarget(Integer accountTarget) {
      this.accountTarget = accountTarget;
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

   protected String getMessage() {
      return "newName=" + newName + " newType=" + newType + " newEncPubKey=" + newEncPubKey;
   }

}
