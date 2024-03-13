/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package pasa.cbentley.jpasc.pcore.domain.java;

import java.io.Serializable;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccount;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccountMutable;
import pasa.cbentley.jpasc.pcore.utils.PascalCoinDouble;

/**
 * Pure Java version of an Account.
 * Provides setters. Used sparingly since it takes a lot of memory.
 * <br>
 * {@link AccountBO}
 * @author Charles Bentley
 *
 */
public class AccountJava implements IPCAccount, Serializable, IPCAccountMutable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private Integer           account;

   private PascalCoinDouble  balance;

   private String            encPubkey;

   private Boolean           isPrivateSale;

   private Integer           lastUpdatedBlock;

   private Integer           lockedUntilBlock;

   private String            name;

   private Integer           nOperation;

   private PCoreCtx          pc;

   private PascalCoinDouble  price;

   private String            salePrivateNewEncPubkey;

   private Integer           sellerAccount;

   private int               state;

   private Integer           type;

   public AccountJava(PCoreCtx pc) {
      this.pc = pc;
   }

   /**
    * {@link AccountJava} is commit always
    */
   public boolean commit() {
      return true;
   }

   /**
    * Account balance (PASCURRENCY)
    * @return Double
    */
   public PascalCoinDouble getAccountBalance() {
      return balance;
   }

   /**
    * Last block that updated this account. If equal to blockchain blocks count it means that it has pending 
    * operations to be included to the blockchain
    * @return Integer
    */
   public Integer getAccountBlockLastUpdated() {
      return lastUpdatedBlock;
   }

   /**
    * Only if the account is listed
    * Account locked until this blocknumber is reached 
    * @return Integer
    */
   public Integer getAccountBlockLockedUntil() {
      return lockedUntilBlock;
   }

   /**
    * Encoded public key value (hexastring)
    * @return String with the encoded public key
    */
   public String getAccountEncodedPublicKey() {
      return encPubkey;
   }

   /**
    * Account name in PascalCoin64 Encoding - abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&amp;*()-+{}[]_:"|&lt;&gt;,.?/~
    * First char cannot start with number
    * Must empty/null or 3..64 characters in length
    * @return String
    */
   public String getAccountName() {
      return name;
   }

   /**
    * Operations made by this account (Note: When an account receives a transaction, n_operation is not changed)
    * @return Integer
    */
   public Integer getAccountOperationCounter() {
      return nOperation;
   }

   /**
    * Account State
    * @return AccountState (Normal or listed)
    */
   public int getAccountState() {
      return state;
   }

   /**
    * Account Type
    * @return Integer
    */
   public Integer getAccountType() {
      return type;
   }

   /**
    * Account number
    * @return Integer 
    */
   public Integer getAccountValue() {
      return account;
   }

   public IPCAccountMutable getMutableAccount() {
      return this;
   }

   /**
    * For Listed accounts, this indicates whether it's private or public sale
    * @return Boolean
    */
   public Boolean getSaleIsPrivate() {
      return isPrivateSale;
   }

   /**
    * Account price if account is listed
    * @return Double
    */
   public PascalCoinDouble getSalePrice() {
      return price;
   }

   /**
    * Only for listed accounts for PrivateSale. This indicates the buyers public key
    * @return String
    */
   public String getSalePrivateNewEncPubkey() {
      return salePrivateNewEncPubkey;
   }

   /**
    * Account Seller if account is listed
    * @return Integer
    */
   public Integer getSaleSellerAccount() {
      return sellerAccount;
   }

   public void setAccount(Integer account) {
      this.account = account;
   }

   public void setBalance(PascalCoinDouble balance) {
      this.balance = balance;
   }

   public void setEncPubkey(String encPubkey) {
      this.encPubkey = encPubkey;
   }

   public void setLastUpdatedBlock(Integer updatedB) {
      this.lastUpdatedBlock = updatedB;
   }

   public void setLockedUntilBlock(Integer lockedUntilBlock) {
      this.lockedUntilBlock = lockedUntilBlock;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setNOperation(Integer nOperation) {
      this.nOperation = nOperation;
   }

   public void setSaleIsPrivate(Boolean privateSale) {
      this.isPrivateSale = privateSale;
   }

   public void setSalePrice(PascalCoinDouble price) {
      this.price = price;
   }

   public void setSalePrivateNewEncPubkey(String newEncPubkey) {
      this.salePrivateNewEncPubkey = newEncPubkey;
   }

   public void setSaleSellerAccount(Integer sellerAccount) {
      this.sellerAccount = sellerAccount;
   }

   public void setState(int state) {
      this.state = state;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "AccountJava");
      toStringPrivate(dc);
      dc.appendVarWithSpace("account", account);
      dc.appendVarWithSpace("encPubkey", encPubkey);
      dc.appendVarWithSpace("isPrivateSale", isPrivateSale);
      dc.appendVarWithSpace("nOperation", nOperation);
      dc.appendVarWithSpace("type", type);
      dc.appendVarWithSpace("state", state);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AccountJava");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUC();
   }

   //#enddebug
   

}
