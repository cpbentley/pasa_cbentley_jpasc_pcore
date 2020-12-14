package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class DecodeOpHashResult extends ObjectPCore implements Serializable {
   public DecodeOpHashResult(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * json=block
    */
   protected Integer         block;

   /**
    * json=account
    */
   protected Integer         account;

   /**
    * json=n_operation
    */
   protected Integer         nOperation;

   /**
    * json=md160hash
    */
   protected String          md160Hash;

   public Integer getBlock() {
      return block;
   }

   public void setBlock(Integer block) {
      this.block = block;
   }

   public Integer getAccount() {
      return account;
   }

   public void setAccount(Integer account) {
      this.account = account;
   }

   public Integer getnOperation() {
      return nOperation;
   }

   public void setnOperation(Integer nOperation) {
      this.nOperation = nOperation;
   }

   public String getMd160Hash() {
      return md160Hash;
   }

   public void setMd160Hash(String md160Hash) {
      this.md160Hash = md160Hash;
   }

}
