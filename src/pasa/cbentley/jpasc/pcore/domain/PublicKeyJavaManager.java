/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;

public class PublicKeyJavaManager {

   protected final PCoreCtx pc;

   private PublicKeyJava    publicKeyJavaAll;

   private PublicKeyJava    publicKeyJavaEmpties;

   public PublicKeyJavaManager(PCoreCtx pc) {
      this.pc = pc;
      publicKeyJavaAll = new PublicKeyJava(pc);
      publicKeyJavaEmpties = new PublicKeyJava(pc);
      
      languageUpdate();
   }

   public PublicKeyJava getEmpties() {
      return publicKeyJavaEmpties;
   }

   public PublicKeyJava getAll() {
      return publicKeyJavaAll;
   }

   public void languageUpdate() {
      publicKeyJavaAll.setName(pc.getResString("text.all.keys"));
      publicKeyJavaEmpties.setName(pc.getResString("text.keys.empties"));
   }

}
