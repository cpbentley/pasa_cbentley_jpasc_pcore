package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class Block extends ObjectPCore implements Serializable {
   /**
    * json=ver_a
    * 
    * Pascal Coin protocol available by the miner
    */
   protected Integer availableVersion;

   /**
    * json=block
    * 
    * Block number
    */
   protected Integer block;

   /**
    * json=target
    * 
    * Target used
    */
   protected Integer compactTarget;

   /**
    * json=enc_pubkey
    * 
    */
   protected String  encPubKey;

   /**
    * json=fee
    * 
    * Fee obtained by operations (PASCURRENCY)
    */
   protected Double  fee;

   /**
    * json=fee_s
    */
   protected String  feeString;

   /**
    * json=hashratekhs
    * 
    * Estimated network hashrate calculated by previous 50 blocks average
    */
   protected Long    last50HashRateKhs;

   /**
    * json=maturation
    * 
    * Number of blocks in the blockchain higher than this
    */
   protected Integer maturation;

   /**
    * json=nonce
    * 
    * Nonce used
    */
   protected Long    nonce;

   private Object    objectSupport;

   /**
    * json=operations
    * 
    * Number of operations included in this block
    */
   protected Integer operationCount;

   /**
    * json=oph
    * 
    * Operations hash
    */
   protected String  operationsHash;

   /**
    * json=payload
    * 
    * Miner's payload
    */
   protected String  payload;

   /**
    * json=pow
    * 
    * Proof of work
    */
   protected String  proofOfWork;

   /**
    * json=reward
    *  
    * Reward of first account's block
    */
   protected Double  reward;

   /**
    * json=reward_s
    */
   protected String  rewardString;

   /**
    * json=sbh
    * 
    * SafeBox Hash
    */
   protected String  safeBoxHash;

   /**
    * json=timestamp
    * 
    * Unix timestamp
    */
   protected Long    timestamp;

   /**
    * json=ver
    * 
    * Pascal Coin protocol used
    */
   protected Integer version;

   public Block(PCoreCtx pc) {
      super(pc);
   }

   public Integer getAvailableVersion() {
      return availableVersion;
   }

   public Integer getBlock() {
      return block;
   }

   public Integer getCompactTarget() {
      return compactTarget;
   }

   public String getEncPubKey() {
      return encPubKey;
   }

   public Double getFee() {
      return fee;
   }

   public Long getLast50HashRateKhs() {
      return last50HashRateKhs;
   }

   public Integer getMaturation() {
      return maturation;
   }

   public Long getNonce() {
      return nonce;
   }

   /**
    * Gets the support Object. 
    * @return null if none set
    */
   public Object getObjectSupport() {
      return objectSupport;
   }

   public Integer getOperationCount() {
      return operationCount;
   }

   public String getOperationsHash() {
      return operationsHash;
   }

   public String getPayload() {
      return payload;
   }

   public String getProofOfWork() {
      return proofOfWork;
   }

   public Double getReward() {
      return reward;
   }

   public String getSafeBoxHash() {
      return safeBoxHash;
   }

   /**
    * Unit time
    * @return
    */
   public Long getTimestamp() {
      return timestamp;
   }

   public Integer getVersion() {
      return version;
   }

   /**
    * json=ver_a
    * 
    * Pascal Coin protocol available by the miner
    */
   public void setAvailableVersion(Integer availableVersion) {
      this.availableVersion = availableVersion;
   }

   /**
    * json=block
    * 
    * Block number
    */
   public void setBlock(Integer block) {
      this.block = block;
   }

   /**
    * json=target
    * 
    * Target used
    */
   public void setCompactTarget(Integer compactTarget) {
      this.compactTarget = compactTarget;
   }

   /**
    * json=enc_pubkey
    * 
    */
   public void setEncPubKey(String encPubKey) {
      this.encPubKey = encPubKey;
   }

   public void setFee(Double fee) {
      this.fee = fee;
   }

   public void setFeeString(String fee) {
      this.feeString = fee;
   }

   public void setLast50HashRateKhs(Long last50HashRateKhs) {
      this.last50HashRateKhs = last50HashRateKhs;
   }

   public void setMaturation(Integer maturation) {
      this.maturation = maturation;
   }

   public void setNonce(Long nonce) {
      this.nonce = nonce;
   }

   /**
    * Object that can be used as support by the GUI
    * @param objectSupport
    */
   public void setObjectSupport(Object objectSupport) {
      this.objectSupport = objectSupport;
   }

   public void setOperationCount(Integer operationCount) {
      this.operationCount = operationCount;
   }

   public void setOperationsHash(String operationsHash) {
      this.operationsHash = operationsHash;
   }

   public void setPayload(String payload) {
      this.payload = payload;
   }

   public void setProofOfWork(String proofOfWork) {
      this.proofOfWork = proofOfWork;
   }

   public void setReward(Double reward) {
      this.reward = reward;
   }

   public void setRewardString(String reward) {
      this.rewardString = reward;
   }

   public void setSafeBoxHash(String safeBoxHash) {
      this.safeBoxHash = safeBoxHash;
   }

   public void setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
   }

   public void setVersion(Integer version) {
      this.version = version;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, Block.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.appendWithSpace(String.valueOf(block));
      dc.nl();
      dc.appendVarWithSpace("timestamp", timestamp);
      dc.appendVarWithSpace("block", block);
      dc.appendVarWithSpace("fee", fee);
      dc.appendVarWithSpace("feeString", feeString);
      dc.appendVarWithSpace("operationCount", operationCount);
      dc.appendVarWithSpace("reward", reward);
      dc.appendVarWithSpace("rewardString", rewardString);

      dc.nl();

      dc.appendVarWithSpace("availableVersion", availableVersion);
      dc.appendVarWithSpace("version", version);
      dc.appendVarWithSpace("target", compactTarget);
      dc.appendVarWithSpace("last50HashRateKhs", last50HashRateKhs);
      dc.appendVarWithSpace("payload", payload);
      dc.nl();
      dc.appendVarWithSpace("maturation", maturation);
      dc.appendVarWithSpace("nonce", nonce);
      dc.nl();
      dc.appendVarWithSpace("encPubKey", encPubKey);
      dc.nl();
      dc.appendVarWithSpace("operationsHash", operationsHash);
      dc.appendVarWithSpace("proofOfWork", proofOfWork);
      dc.nl();
      dc.appendVarWithSpace("safeBoxHash", safeBoxHash);

   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, Block.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
