/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.java;

import java.util.HashMap;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * A cache collection of {@link PublicKeyJava} for building an in-memory vision of which {@link Account} a key owns without querying the blockchain
 * <br>
 * <br>
 * You need the encoded public key of a Public key to retrieve the {@link PublicKeyJava}.
 * <br>
 * <br>
 * 
 * @author Charles Bentley
 *
 */
public class PublicKeyJavaCache implements IStringable {

   private PCoreCtx                       pc;

   private HashMap<String, PublicKeyJava> map;

   private int                            countAccount;

   public PublicKeyJavaCache(PCoreCtx pc) {
      this.pc = pc;
      map = new HashMap<>();
   }
   

   public void clear() {
      map.clear();
   }

   /**
    * The number of Accounts controlled by the collection of {@link PublicKeyJava} in this Cache.
    * @return
    */
   public int getNumAccounts() {
      return countAccount;
   }

   /**
    * Return key if any in the cache
    * @param encPubKey
    * @return
    */
   public PublicKeyJava getKey(String encPubKey) {
      return map.get(encPubKey);
   }

   /**
    * Associates the {@link Account} this the <code>encPubKey</code>.
    * 
    * Creates or get the {@link PublicKeyJava} for the given <code>encPubKey</code>
    * @param encPubKey
    * @param account Account
    * @return PublicKeyJava 
    */
   public PublicKeyJava updateKey(String encPubKey, Account account) {
      countAccount++;
      PublicKeyJava publicKeyJava = map.get(encPubKey);
      if (publicKeyJava == null) {
         publicKeyJava = new PublicKeyJava(pc);
         publicKeyJava.setCache(this);
         map.put(encPubKey, publicKeyJava);
         publicKeyJava.setEncPubKey(encPubKey);
         //what name to use? see if it has been named
         String name = pc.getKeyNameProvider().getKeyName(encPubKey);
         publicKeyJava.setName(name);
      }
      publicKeyJava.addAccount(account.getAccount());
      publicKeyJava.addCoins(account.getBalance());
      if (account.getSellerAccount() != null) {
         publicKeyJava.addSaleCount(1);
      }
      publicKeyJava.setLastUpdatedBlockMinMax(account.getUpdatedB());
      return publicKeyJava;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, PublicKeyJavaCache.class);
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("#keys", map.size());
      dc.appendVarWithSpace("#accounts", countAccount);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, PublicKeyJavaCache.class);
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   //#enddebug

}
