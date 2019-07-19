/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;

public class PagerPublicKeyJava extends PagerAbstract<PublicKeyJava> {

   public PagerPublicKeyJava(PCoreCtx pc) {
      super(pc, 0, 10);
   }

   public Integer getNextStart(List<PublicKeyJava> listProcessed, List<PublicKeyJava> listPublished) {
      return lookUpPointer + listProcessed.size();
   }


}
