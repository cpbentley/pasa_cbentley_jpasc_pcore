/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.java;

import java.io.Serializable;

import com.github.davidbolet.jpascalcoin.api.model.KeyType;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.structs.IntBuffer;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class PublicKeyJava implements Serializable, IStringable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
   * Human readable name stored at the Wallet for this key
   */
   protected String          name;

   /**
   * If false then Wallet doesn't have Private key for this public key, so, Wallet cannot execute operations with this key
   */
   protected Boolean         canUse;

   /**
   * Encoded value of this public key.This HEXASTRING has no checksum, so, if using it always must be sure that value is correct
   */
   protected String          encPubKey;

   /**
   * Encoded value of this public key in Base 58 format, also contains a checksum.This is the same value that Application Wallet exports as a public key
   */
   protected String          base58PubKey;

   /**
   *Indicates which EC type is used (EC_NID)
   */
   protected KeyType         keyType;

   /**
   * HEXASTRING with x value of public key
   */
   protected String          x;

   /**
   * HEXASTRING with y value of public key
   */
   protected String          y;

   /**
    * when null, not computed
    */
   private Integer           numAccounts;

   /**
    * total number of coins on this accounts'key
    * when null, not computed
    */
   private Double            numCoins;

   private boolean           isNew;

   private IntBuffer         accounts;

   protected final PCoreCtx  pc;

   private int cachedTableIndex = -1;

   public PublicKeyJava(PCoreCtx pc) {
      this.pc = pc;
   }

   public void addAccount(Integer account) {
      if (account != null) {
         if (accounts == null) {
            accounts = new IntBuffer(pc.getUCtx());
         }
         accounts.addInt(account.intValue());
         numAccounts = accounts.getSize();
      }
   }

   public int[] getAccounts() {
      if (accounts == null) {
         return null;
      } else {
         return accounts.getIntsClonedTrimmed();
      }

   }

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

   /**
    * The number of accounts might have to be computed.
    * <br>
    * 
    * @return
    */
   public Integer getNumAccounts() {
      return numAccounts;
   }

   public void setNumAccounts(int numAccounts) {
      this.numAccounts = numAccounts;
   }

   public Double getNumCoins() {
      return numCoins;
   }

   public void setNumCoins(Double numCoins) {
      this.numCoins = numCoins;
   }

   public boolean isNew() {
      return isNew;
   }

   public void setNew(boolean isNew) {
      this.isNew = isNew;
   }

   
   public int getCachedTableIndex() {
      return cachedTableIndex;
   }
   
   
   
   public void setCachedTableIndex(int cachedTableIndex) {
      this.cachedTableIndex = cachedTableIndex;
   }

   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((encPubKey == null) ? 0 : encPubKey.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      PublicKeyJava other = (PublicKeyJava) obj;
      if (encPubKey == null) {
         if (other.encPubKey != null)
            return false;
      } else if (!encPubKey.equals(other.encPubKey))
         return false;
      return true;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PublicKeyJava");
      toStringPrivate(dc);

      dc.appendVarWithNewLine("base58PubKey", base58PubKey);
      dc.appendVarWithNewLine("encPubKey", encPubKey);
      dc.appendVarWithNewLine("x", x);
      dc.appendVarWithNewLine("y", y);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("name", name);
      dc.appendVarWithSpace("canUse", canUse);
      dc.appendVarWithSpace("numAccounts", numAccounts);
      dc.appendVarWithSpace("numCoins", numCoins);
      dc.appendVarWithSpace("keyType", keyType);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PublicKeyJava");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }


   //#enddebug

}
