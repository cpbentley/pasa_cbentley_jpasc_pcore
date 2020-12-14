/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.AccountJava;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;

/**
 * Maps davidbolet classes to {@link AccountJava} classes
 * 
 * 
 * @author Charles Bentley
 *
 */
public class DomainMapper {

   private PCoreCtx pc;

   public DomainMapper(PCoreCtx pc) {
      this.pc = pc;
   }

   /**
    * if null, returns null
    * @param publicKeyJava
    * @return
    */
   public PublicKey mapPublicKeyJava(PublicKeyJava publicKeyJava) {
      if (publicKeyJava == null) {
         return null;
      }
      PublicKey pk = new PublicKey(pc);
      pk.setCanUse(publicKeyJava.getCanUse());
      pk.setName(publicKeyJava.getName());
      pk.setKeyType(publicKeyJava.getKeyType());
      pk.setBase58PubKey(publicKeyJava.getBase58PubKey());
      pk.setEncPubKey(publicKeyJava.getEncPubKey());
      pk.setX(publicKeyJava.getX());
      pk.setY(publicKeyJava.getY());
      return pk;
   }
   
   /**
    * simple mapping. does not compute java value (account nums, pasc value, age range etc)
    * if null, returns null
    * @param publicKey
    * @return
    */
   public PublicKeyJava mapPublicKey(PublicKey publicKey) {
      if (publicKey == null) {
         return null;
      }
      PublicKeyJava pk = new PublicKeyJava(pc);
      pk.setCanUse(publicKey.getCanUse());
      pk.setName(publicKey.getName());
      pk.setKeyType(publicKey.getKeyType());
      pk.setBase58PubKey(publicKey.getBase58PubKey());
      pk.setEncPubKey(publicKey.getEncPubKey());
      pk.setX(publicKey.getX());
      pk.setY(publicKey.getY());
      return pk;
   }
}
