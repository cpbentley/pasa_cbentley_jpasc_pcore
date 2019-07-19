/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

import java.util.HashMap;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;

public class PublicKeyJavaCache {

   private PCoreCtx pc;

   private HashMap<String, PublicKeyJava> map;
   
   
   public PublicKeyJavaCache(PCoreCtx pc) {
      this.pc = pc;
      map = new HashMap<>();
   }


   public PublicKeyJava updateKey(String encPubKey, Account account) {
      PublicKeyJava publicKeyJava = map.get(encPubKey);
      if (publicKeyJava == null) {
         publicKeyJava = new PublicKeyJava(pc);
         map.put(encPubKey, publicKeyJava);
         publicKeyJava.setEncPubKey(encPubKey);
         //what name to use? see if it has been named
         String name = pc.getPkNameStore().getKeyName(encPubKey);
         if(name == null) {
            name = "key#"+map.size();
         }
         publicKeyJava.setName(name);
      }
      publicKeyJava.addAccount(account.getAccount());
      return publicKeyJava;
   }
   
}
