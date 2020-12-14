package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class Account extends ObjectPCore implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * json=account
    */
   private Integer           account;

   /**
    * json=amount_to_swap
    */
   private Double            amountToSwap;

   /**
    * json=amount_to_swap_s
    */
   private String            amountToSwapString;

   /**
    * json=balance 
    */
   private Double            balance;

   /**
    * json=balance_s 
    */
   private String            balanceString;

   private transient Integer cacheBlock;

   /**
    * json=data 
    * 
    * hexa decimal string
    */
   private String            data;

   /**
    * json=enc_pubkey
    */
   private String            encPubkey;

   /**
    * json=hashed_secret
    */
   private String            hashedSecret;

   /**
    * json=locked_until_block
    */
   private Integer           lockedUntilBlock;

   /**
    * json=name
    */
   private String            name;

   /**
    * json=new_enc_pubkey
    */
   private String            newEncPubkey;

   /**
    * json=n_operation 
    */
   private Integer           nOperation;

   private Object objectSupport;

   /**
    * json=price 
    */
   private Double            price;

   /**
    * json=price_s 
    */
   private String            priceString;

   /**
    * json=private_sale
    */
   private Boolean           privateSale;

   /**
    * json=receiver_swap_account
    */
   private Integer           receiverSwapAccount;

   /**
    * json=seal
    */
   private String            seal;

   /**
    * json=seller_account 
    */
   private Integer           sellerAccount;

   /**
    * json=state 
    */
   private AccountState      state;

   /**
    * json=type
    */
   private Integer           type;

   /**
    * json=updated_b 
    */
   private Integer           updatedB;

   /**
    * json=updated_b_active_mode 
    */
   private Integer           updatedBlockActive;

   /**
    * json=updated_b_passive_mode 
    */
   private Integer           updatedBlockPassive;

   public Account(PCoreCtx pc) {
      super(pc);
   }

   /**
    * Account number
    * @return Integer 
    */
   public Integer getAccount() {
      return account;
   }

   public Double getAmountToSwap() {
      return amountToSwap;
   }

   public String getAmountToSwapString() {
      return amountToSwapString;
   }

   /**
    * Account balance (PASCURRENCY)
    * @return Double
    */
   public Double getBalance() {
      return balance;
   }

   public String getBalanceString() {
      return balanceString;
   }

   public Integer getCacheBlock() {
      return cacheBlock;
   }

   public String getData() {
      return data;
   }

   /**
    * Encoded public key value (hexastring)
    * @return String with the encoded public key
    */
   public String getEncPubkey() {
      return encPubkey;
   }

   public String getHashedSecret() {
      return hashedSecret;
   }

   /**
    * Only if the account is listed
    * Account locked until this blocknumber is reached 
    * @return Integer
    */
   public Integer getLockedUntilBlock() {
      return lockedUntilBlock;
   }

   /**
    * Account name in PascalCoin64 Encoding - abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&amp;*()-+{}[]_:"|&lt;&gt;,.?/~
    * First char cannot start with number
    * Must empty/null or 3..64 characters in length
    * @return String
    */
   public String getName() {
      return name;
   }

   /**
    * Only for listed accounts for PrivateSale. This indicates the buyers public key
    * @return String
    */
   public String getNewEncPubkey() {
      return newEncPubkey;
   }

   /**
    * Operations made by this account (Note: When an account receives a transaction, n_operation is not changed)
    * @return Integer
    */
   public Integer getnOperation() {
      return nOperation;
   }

   /**
    * Gets the support Object. 
    * @return null if none set
    */
   public Object getObjectSupport() {
      return objectSupport;
   }

   /**
    * Account price if account is listed
    * @return Double
    */
   public Double getPrice() {
      return price;
   }

   public String getPriceString() {
      return priceString;
   }

   /**
    * For Listed accounts, this indicates whether it's private or public sale
    * @return Boolean
    */
   public Boolean getPrivateSale() {
      return privateSale;
   }

   public Integer getReceiverSwapAccount() {
      return receiverSwapAccount;
   }

   public String getSeal() {
      return seal;
   }

   /**
    * Account Seller if account is listed
    * @return Integer
    */
   public Integer getSellerAccount() {
      return sellerAccount;
   }

   /**
    * Account State
    * @return AccountState (Normal or listed)
    */
   public AccountState getState() {
      return state;
   }

   /**
    * Account Type
    * @return Integer
    */
   public Integer getType() {
      return type;
   }

   /**
    * Last block that updated this account. If equal to blockchain blocks count it means that it has pending 
    * operations to be included to the blockchain
    * @return Integer
    */
   public Integer getUpdatedB() {
      return updatedB;
   }

   public Integer getUpdatedBlockActive() {
      return updatedBlockActive;
   }

   public Integer getUpdatedBlockPassive() {
      return updatedBlockPassive;
   }

   public void setAccount(Integer account) {
      this.account = account;
   }

   public void setAmountToSwap(Double amountToSwap) {
      this.amountToSwap = amountToSwap;
   }

   public void setAmountToSwapString(String amountToSwapString) {
      this.amountToSwapString = amountToSwapString;
   }

   public void setBalance(Double balance) {
      this.balance = balance;
   }

   public void setBalanceString(String balance) {
      this.balanceString = balance;
   }

   /**
    * The block count at which the Account object was put into the cache.
    * <br>
    * This allows the cache to know when it has to update an Account.
    * RPC calls are costly.
    */
   public void setCacheBlock(Integer cacheBlock) {
      this.cacheBlock = cacheBlock;
   }

   public void setData(String data) {
      this.data = data;
   }

   public void setEncPubkey(String encPubkey) {
      this.encPubkey = encPubkey;
   }

   public void setHashedSecret(String hashedSecret) {
      this.hashedSecret = hashedSecret;
   }

   public void setLockedUntilBlock(Integer lockedUntilBlock) {
      this.lockedUntilBlock = lockedUntilBlock;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setNewEncPubkey(String newEncPubkey) {
      this.newEncPubkey = newEncPubkey;
   }

   public void setnOperation(Integer nOperation) {
      this.nOperation = nOperation;
   }

   /**
    * Object that can be used as support by the GUI
    * @param objectSupport
    */
   public void setObjectSupport(Object objectSupport) {
      this.objectSupport = objectSupport;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public void setPriceString(String priceString) {
      this.priceString = priceString;
   }

   public void setPrivateSale(Boolean privateSale) {
      this.privateSale = privateSale;
   }

   public void setReceiverSwapAccount(Integer receiverSwapAccount) {
      this.receiverSwapAccount = receiverSwapAccount;
   }

   public void setSeal(String seal) {
      this.seal = seal;
   }

   public void setSellerAccount(Integer sellerAccount) {
      this.sellerAccount = sellerAccount;
   }

   public void setState(AccountState state) {
      this.state = state;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public void setUpdatedB(Integer updatedB) {
      this.updatedB = updatedB;
   }

   public void setUpdatedBlockActive(Integer updatedBlockActive) {
      this.updatedBlockActive = updatedBlockActive;
   }

   public void setUpdatedBlockPassive(Integer updatedBlockPassive) {
      this.updatedBlockPassive = updatedBlockPassive;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, Account.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.append(account);

      dc.nl();

      dc.appendVarWithSpace("account", account);
      dc.appendVarWithSpace("name", name);
      dc.appendVarWithSpace("type", type);
      dc.appendVarWithSpace("balance", balance);
      dc.appendVarWithSpace("balanceString", balanceString);
      dc.appendVarWithSpace("nOperation", nOperation);
      dc.nl();
      dc.appendVarWithSpace("seal", seal);
      dc.appendVarWithSpace("data", data);
      dc.nl();
      dc.appendVarWithSpace("encPubkey", encPubkey);
      dc.nl();
      dc.appendVarWithSpace("updatedBlockActive", updatedBlockActive);
      dc.appendVarWithSpace("updatedB", updatedB);
      dc.appendVarWithSpace("updatedBlockPassive", updatedBlockPassive);

      dc.nl();
      dc.appendVarWithSpace("state", state);
      dc.nl();
      dc.appendVarWithSpace("price", price);
      dc.appendVarWithSpace("priceStr", priceString);
      dc.appendVarWithSpace("privateSale", privateSale);
      dc.appendVarWithSpace("sellerAccount", sellerAccount);
      dc.appendVarWithSpace("lockedUntilBlock", lockedUntilBlock);
      dc.nl();
      dc.appendVarWithSpace("cacheBlock", cacheBlock);
      dc.nlLvlO(objectSupport, "objectSupport");
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, Account.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
