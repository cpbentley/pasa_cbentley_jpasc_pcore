/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.operations;

import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.PayLoadEncryptionMethod;
import com.github.davidbolet.jpascalcoin.exception.RPCApiException;

import pasa.cbentley.core.src4.logging.Dctx;
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
public class OperationJavaSendTo extends OperationJavaAbstract {

   private Integer                 sender;

   private Integer                 target;

   private Double                  amount;

   private Double                  fee;

   private byte[]                  payload;

   /**
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_00_NONE}
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_01_DEST}
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_02_SENDER}
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_03_AES}
    */
   private int                     payloadEncryptionMethod;

   private PayLoadEncryptionMethod payloadMethod;

   private String                  pwd;

   public OperationJavaSendTo(PCoreCtx pc) {
      super(pc);
   }

   public boolean execute() {
      try {
         Operation op = getClient().sendTo(sender, target, amount, fee, payload, payloadMethod, pwd);
         if (op != null) {
            pc.getLog().consoleLogGreen("sendTo operation successfull sender=" + sender + " target=" + target + " amount=" + amount);
            pc.getPasaServices().registerAccountInPendingOperations(op);
            return true;
         } else {
            pc.getLog().consoleLogError("sendTo operation failed. Null Returned");
         }
      } catch (RPCApiException e) {
         e.printStackTrace();
         pc.getLog().consoleLogError("RPCApiException: changeAccountInfo operation failed " + e.getMessage());
      }
      return false;
   }

   public Integer getSender() {
      return sender;
   }

   public void setSender(Integer sender) {
      this.sender = sender;
   }

   public Integer getTarget() {
      return target;
   }

   public void setTarget(Integer target) {
      this.target = target;
   }

   public Double getAmount() {
      return amount;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
   }

   public Double getFee() {
      return fee;
   }

   public void setFee(Double fee) {
      this.fee = fee;
   }

   public byte[] getPayload() {
      return payload;
   }

   public void setPayload(byte[] payload) {
      this.payload = payload;
   }


   public void setPayloadEncryptionMethod(int type) {
      this.payloadEncryptionMethod = type;
      switch (type) {
         case IPayload.PAYLOAD_ENCRYPTION_00_NONE:
            this.payloadMethod = PayLoadEncryptionMethod.NONE;
            break;
         case IPayload.PAYLOAD_ENCRYPTION_01_DEST:
            this.payloadMethod = PayLoadEncryptionMethod.DEST;
            break;
         case IPayload.PAYLOAD_ENCRYPTION_02_SENDER:
            this.payloadMethod = PayLoadEncryptionMethod.SENDER;
            break;
         case IPayload.PAYLOAD_ENCRYPTION_03_AES:
            this.payloadMethod = PayLoadEncryptionMethod.AES;
            break;
         default:
            throw new IllegalArgumentException();
      }
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "SendToOperation");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("sender", sender);
      dc.appendVarWithSpace("amount", amount);
      dc.appendVarWithSpace("target", target);
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

}
