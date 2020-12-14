/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;

public class PagerPublicKey extends PagerAbstract<PublicKey> {

   public PagerPublicKey(PCoreCtx pc) {
      super(pc, 0, 10);
   }

   public Integer getNextStart(List<PublicKey> listProcessed, List<PublicKey> listPublished) {
      return lookUpPointer + listProcessed.size();
   }


}
