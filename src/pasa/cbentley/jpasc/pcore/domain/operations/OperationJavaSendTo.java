/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.operations;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

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

   private Double  amount;

   private Integer sender;

   private Integer target;

   public OperationJavaSendTo(PCoreCtx pc) {
      super(pc);
   }

   protected void executeOperation() {
      op = getClient().sendTo(sender, target, amount, fee, payload, payloadMethod, pwd);
   }

   protected void executePostSuccess() {
      // TODO Auto-generated method stub

   }

   public Double getAmount() {
      return amount;
   }

   protected String getMessage() {
      return "sendTo operation successfull sender=" + sender + " target=" + target + " amount=" + amount;
   }

   public Integer getSender() {
      return sender;
   }

   public Integer getTarget() {
      return target;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
   }

   public void setSender(Integer sender) {
      this.sender = sender;
   }

   public void setTarget(Integer target) {
      this.target = target;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "SendToOperation");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "SendToOperation");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }
 

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("sender", sender);
      dc.appendVarWithSpace("amount", amount);
      dc.appendVarWithSpace("target", target);
      dc.appendVarWithSpace("fee", fee);
      dc.appendVarWithSpace("pwd", pwd);
   }
   //#enddebug
}
