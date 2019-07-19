/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;

public class PagerAccountBO extends PagerAbstract<AccountBO> {

   /**
    * 
    * @param pc
    */
   public PagerAccountBO(PCoreCtx pc) {
      super(pc, 0, 10);
   }

   /**
    * When the total number of account is known
    * @param pc
    * @param numAccounts
    */
   public PagerAccountBO(PCoreCtx pc, Integer numAccounts) {
      super(pc, 0, Math.min(numAccounts, 10));
      setLookUpRangeEnd(numAccounts);
   }

   /**
    * In a contiguous list, next 
    */
   public Integer getNextStart(List<AccountBO> list, List<AccountBO> listPublished) {
      return getCountProcessed() + 1;
   }

}
