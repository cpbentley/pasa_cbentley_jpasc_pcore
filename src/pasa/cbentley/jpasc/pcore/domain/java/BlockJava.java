/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.java;

import java.io.Serializable;

import com.github.davidbolet.jpascalcoin.api.model.Block;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * 
 * @author Charles Bentley
 * 
 * @see Block
 *
 */
public class BlockJava  implements Serializable, IStringable {

   
   protected final PCoreCtx pc;

   /**
    * Block number
    */  
   protected Integer block;

   /**
    * Last block that updated this account. If equal to blockchain blocks count it means that it has pending operations to be included to the blockchain
    */  
   protected String encPubKey;

   /**
    * Reward of first account's block
    */  
   protected Double reward;

   /**
    * Fee obtained by operations (PASCURRENCY)
    */  
   protected Double fee;

   /**
    * Pascal Coin protocol used
    */  
   protected Integer version;

   /**
    * Pascal Coin protocol available by the miner
    */  
   protected Integer availableVersion;

   /**
    * Unix timestamp
    */  
   protected Long timestamp;

   /**
    * Target used
    */  
   protected Integer compactTarget;

   /**
    * Nonce used
    */  
   protected Long nonce;

   /**
    * Miner's payload
    */  
   protected String payload;

   /**
    * SafeBox Hash
    */  
   protected String safeBoxHash;

   /**
    * Operations hash
    */  
   protected String operationsHash;

   /**
    * Proof of work
    */  
   protected String proofOfWork;

   /**
    * Number of operations included in this block
    */  
   protected Integer operationCount;

   /**
    * Estimated network hashrate calculated by previous 50 blocks average
    */  
   protected Long last50HashRateKhs;

   /**
    * Number of blocks in the blockchain higher than this
    */  
   protected Integer maturation;
   
   
   public BlockJava(PCoreCtx pc) {
      this.pc = pc;
   }
   
   
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "BlockJava");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "BlockJava");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   //#enddebug
   

}
