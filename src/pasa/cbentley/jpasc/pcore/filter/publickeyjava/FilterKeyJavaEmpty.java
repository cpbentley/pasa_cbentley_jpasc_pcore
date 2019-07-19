/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.publickeyjava;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.FilterAbstract;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKeyJava;

public class FilterKeyJavaEmpty extends FilterAbstract implements IFilterPublicKeyJava {

   public FilterKeyJavaEmpty(PCoreCtx pc) {
      super(pc);
   }

   public boolean filterPublicKey(PublicKeyJava publicKey) {
      return publicKey.getNumAccounts() == 0;
   }

}
