package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class PublicKey extends ObjectPCore implements Serializable {
   public PublicKey(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * json=name
    * 
    * Human readable name stored at the Wallet for this key
    */
   protected String          name;

   /**
    * json=can_use
    * 
    * If false then Wallet doesn't have Private key for this public key, so, Wallet cannot execute operations with this key
    */
   protected Boolean         canUse;

   /**
    * json=enc_pubkey
    * 
    * Encoded value of this public key.This HEXASTRING has no checksum, so, if using it always must be sure that value is correct
    */
   protected String          encPubKey;

   /**
    * json=b58_pubkey
    * 
    * Encoded value of this public key in Base 58 format, also contains a checksum.This is the same value that Application Wallet exports as a public key
    */
   protected String          base58PubKey;

   /**
    * json=ec_nid
    * 
    * Indicates which EC type is used (EC_NID)
    */
   protected KeyType         keyType;

   /**
    * json=x
    * 
    * HEXASTRING with x value of public key
    */
   protected String          x;

   /**
    * json=y
    * 
    * HEXASTRING with y value of public key
    */
   protected String          y;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Boolean getCanUse() {
      return canUse;
   }

   public void setCanUse(Boolean canUse) {
      this.canUse = canUse;
   }

   public String getEncPubKey() {
      return encPubKey;
   }

   public void setEncPubKey(String encPubKey) {
      this.encPubKey = encPubKey;
   }

   public String getBase58PubKey() {
      return base58PubKey;
   }

   public void setBase58PubKey(String base58PubKey) {
      this.base58PubKey = base58PubKey;
   }

   public KeyType getKeyType() {
      return keyType;
   }

   public void setKeyType(KeyType keyType) {
      this.keyType = keyType;
   }

   public String getX() {
      return x;
   }

   public void setX(String x) {
      this.x = x;
   }

   public String getY() {
      return y;
   }

   public void setY(String y) {
      this.y = y;
   }
}
