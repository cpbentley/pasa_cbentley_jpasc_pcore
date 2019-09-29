/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

import java.util.HashMap;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;

/**
 * A cache of {@link PublicKeyJava}.
 * 
 * For building a vision of which key owns what
 * 
 * @author Charles Bentley
 *
 */
public class PublicKeyJavaCache implements IStringable {

   private PCoreCtx pc;

   private HashMap<String, PublicKeyJava> map;
   
   private int countAccount;
   
   public PublicKeyJavaCache(PCoreCtx pc) {
      this.pc = pc;
      map = new HashMap<>();
   }

   public void clear() {
      map.clear();
   }
   
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
      if(account.getSellerAccount() != null) {
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
      dc.root(this, "PublicKeyJavaCache");
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
      dc.root1Line(this, "PublicKeyJavaCache");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   //#enddebug
   

}
