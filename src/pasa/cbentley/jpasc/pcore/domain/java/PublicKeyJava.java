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
import pasa.cbentley.jpasc.pcore.utils.PascalCoinValue;
import pasa.cbentley.jpasc.pcore.utils.PublicKeyJavaCache;

public class PublicKeyJava implements Serializable, IStringable {
   /**
    * 
    */
   private static final long  serialVersionUID = 1L;

   private IntBuffer          accounts;

   /**
   * Encoded value of this public key in Base 58 format, also contains a checksum.This is the same value that Application Wallet exports as a public key
   */
   protected String           base58PubKey;

   private PublicKeyJavaCache cache;

   private int                cachedTableIndex = -1;

   /**
   * If false then Wallet doesn't have Private key for this public key, so, Wallet cannot execute operations with this key
   */
   protected Boolean          canUse;

   /**
   * Encoded value of this public key.This HEXASTRING has no checksum, so, if using it always must be sure that value is correct
   */
   protected String           encPubKey;

   private boolean            isNew;

   private boolean            isWalletKey;

   /**
   *Indicates which EC type is used (EC_NID)
   */
   protected KeyType          keyType;

   /**
   * Human readable name stored at the Wallet for this key
   */
   protected String           name;

   /**
    * when null, not computed
    */
   private int                numAccounts;

   /**
    * total number of coins on this accounts'key
    * when null, not computed
    */
   private PascalCoinValue    numCoins;

   protected final PCoreCtx   pc;

   private int                saleCount;

   private int                updateBlockMax;

   private int                updateBlockMin;

   /**
   * HEXASTRING with x value of public key
   */
   protected String           x;

   /**
   * HEXASTRING with y value of public key
   */
   protected String           y;

   public PublicKeyJava(PCoreCtx pc) {
      this.pc = pc;
   }

   /**
    * Simply appends the account without checking if its already inside.
    * @param account
    */
   public void addAccount(Integer account) {
      if (account != null) {
         if (accounts == null) {
            accounts = new IntBuffer(pc.getUCtx(), 16, 16);
         }
         accounts.addInt(account.intValue());
         numAccounts++;
      }
   }

   public void addCoins(Double balance) {
      if (balance == null) {
         return;
      }
      if (numCoins == null) {
         numCoins = new PascalCoinValue(pc, balance.doubleValue());
      } else {
         numCoins.addMutable(balance);
      }
   }

   public void addSaleCount(int i) {
      saleCount += i;
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

   public int[] getAccounts() {
      if (accounts == null) {
         return null;
      } else {
         return accounts.getIntsClonedTrimmed();
      }
   }

   public String getBase58PubKey() {
      return base58PubKey;
   }

   public PublicKeyJavaCache getCache() {
      return cache;
   }

   public int getCachedTableIndex() {
      return cachedTableIndex;
   }

   public Boolean getCanUse() {
      return canUse;
   }

   public String getEncPubKeyOrFetch() {
      if (encPubKey == null) {

      }
      return encPubKey;
   }

   public String getEncPubKey() {
      return encPubKey;
   }

   public KeyType getKeyType() {
      return keyType;
   }

   public String getName() {
      return name;
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

   public int getNumAccountsValue() {
      return numAccounts;
   }

   public PascalCoinValue getNumCoins() {
      return numCoins;
   }

   public int getSaleCount() {
      return saleCount;
   }

   public int getUpdateBlockMax() {
      return updateBlockMax;
   }

   public int getUpdateBlockMin() {
      return updateBlockMin;
   }

   public String getX() {
      return x;
   }

   public String getY() {
      return y;
   }

   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((encPubKey == null) ? 0 : encPubKey.hashCode());
      return result;
   }

   public boolean isNew() {
      return isNew;
   }

   public boolean isWalletKey() {
      return isWalletKey;
   }

   public void setBase58PubKey(String base58PubKey) {
      this.base58PubKey = base58PubKey;
   }

   public void setCache(PublicKeyJavaCache cache) {
      this.cache = cache;
   }

   public void setCachedTableIndex(int cachedTableIndex) {
      this.cachedTableIndex = cachedTableIndex;
   }

   public void setCanUse(Boolean canUse) {
      this.canUse = canUse;
   }

   public void setEncPubKey(String encPubKey) {
      this.encPubKey = encPubKey;
   }

   public void setKeyType(KeyType keyType) {
      this.keyType = keyType;
   }

   public void setLastUpdatedBlockMinMax(int block) {
      if (block > getUpdateBlockMax()) {
         updateBlockMax = block;
      }
      if (block < getUpdateBlockMin()) {
         updateBlockMin = block;
      }
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setNew(boolean isNew) {
      this.isNew = isNew;
   }

   public void setNumAccounts(int numAccounts) {
      this.numAccounts = numAccounts;
   }

   public void setWalletKey(boolean isWalletKey) {
      this.isWalletKey = isWalletKey;
   }

   public void setX(String x) {
      this.x = x;
   }

   public void setY(String y) {
      this.y = y;
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

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PublicKeyJava");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("name", name);
      dc.appendVarWithSpace("canUse", canUse);
      dc.appendVarWithSpace("numAccounts", numAccounts);
      dc.appendVarWithSpace("numCoins", numCoins);
      dc.appendVarWithSpace("keyType", keyType);
   }

   //#enddebug

}
