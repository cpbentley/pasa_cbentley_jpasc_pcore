/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.operations;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPayload;
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.rpc.model.PayLoadEncryptionMethod;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;

/**
 * Base class
 * @author Charles Bentley
 *
 */
public abstract class OperationJavaAbstract implements IStringable {

   protected final PCoreCtx          pc;

   protected Double                  fee;

   protected byte[]                  payload;

   /**
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_00_NONE}
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_01_DEST}
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_02_SENDER}
    * <li> {@link IPayload#PAYLOAD_ENCRYPTION_03_AES}
    */
   protected int                     payloadEncryptionMethod;

   protected PayLoadEncryptionMethod payloadMethod;

   protected String                  pwd;

   protected Operation               op;

   protected boolean                 hasRun;

   protected boolean                 isSuccess;

   protected boolean                 isParamValidated;

   public OperationJavaAbstract(PCoreCtx pc) {
      this.pc = pc;
   }

   /**
    * 
    * @return
    * @throws IllegalStateException when parameters are invalid
    */
   public boolean execute() {
      if (hasRun) {
         throw new IllegalStateException("Operation has run already");
      }
      if (!isParamValidated) {
         throw new IllegalStateException("Params not validated");
      }
      try {
         executeOperation();
      } catch (RPCApiException e) {
         e.printStackTrace();
         pc.getLog().consoleLogError("RPCApiException: changeAccountInfo operation failed " + e.getMessage());
      }
      hasRun = true;
      if (op == null) {
         isSuccess = false;
      } else {
         isSuccess = true;
         executePostSuccess();
      }
      return isSuccess;
   }

   protected void setPreValidation() {
      isParamValidated = true;
   }

   public boolean isSuccess() {
      return op != null;
   }

   protected abstract void executePostSuccess();

   protected abstract void executeOperation();

   protected abstract String getMessage();

   public IPascalCoinClient getClient() {
      return pc.getPClient();
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
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "OperationJava");
      toStringPrivate(dc);
      
      dc.nl();
      dc.appendVarWithSpace("fee", fee);
      if(payload == null) {
         dc.appendWithSpace("No payload");
      } else {
         dc.nl();
         dc.appendVarWithSpace("Payload", toStringPayloadEncryptionMethod(payloadEncryptionMethod));
         dc.appendVarWithSpace("pwd", pwd);
         String str = pc.getUC().getBU().toStringBytes(payload, 16);
         dc.nl();
         dc.append(str);
      }
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("hasRun", hasRun);
      dc.appendVarWithSpace("isSuccess", isSuccess);
      dc.appendVarWithSpace("isParamValidated", isParamValidated);
   }
   
   public String toStringPayloadEncryptionMethod(int type) {
      switch (type) {
         case IPayload.PAYLOAD_ENCRYPTION_00_NONE:
           return "None";
         case IPayload.PAYLOAD_ENCRYPTION_01_DEST:
            return "Dest Key";
         case IPayload.PAYLOAD_ENCRYPTION_02_SENDER:
            return "Sender Key";
         case IPayload.PAYLOAD_ENCRYPTION_03_AES:
            return "Password";
         default:
            return "Unknown"+type;
      }
   }

   public String toString(PublicKey pkNew, Account buyer, Account bought, Double fee) {
      StringBBuilder sb = new StringBBuilder(pc.getUC());
      sb.append("Account ");
      sb.append(buyer.getAccount());
      sb.append(" buys ");
      sb.append(bought.getAccount());
      sb.append(" for ");
      sb.append(bought.getPrice());
      sb.append(" fee ");
      sb.append(fee.doubleValue());
      sb.append(" sent to ");
      sb.append(pkNew.getName());
      return sb.toString();
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "OperationJava");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUC();
   }
   //#enddebug

}
