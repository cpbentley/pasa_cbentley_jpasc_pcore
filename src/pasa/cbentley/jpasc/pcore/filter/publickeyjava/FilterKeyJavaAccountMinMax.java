/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.publickeyjava;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.FilterAbstract;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKeyJava;

public class FilterKeyJavaAccountMinMax extends FilterAbstract implements IFilterPublicKeyJava {

   private Integer countMinimum;
   private Integer countMaximum;

   public FilterKeyJavaAccountMinMax(PCoreCtx pc,Integer countMinimum, Integer countMaximum ) {
      super(pc);
      this.countMinimum = countMinimum;
      this.countMaximum = countMaximum;
   }

   public boolean filterPublicKey(PublicKeyJava publicKey) {
      int numAccounts = publicKey.getNumAccounts();
      if(countMinimum != null) {
         if(numAccounts < countMinimum.intValue()) {
            return false;
         }
      }
      if(countMaximum != null) {
         if(numAccounts > countMaximum.intValue()) {
            return false;
         }
      }
      return true;
   }

}
