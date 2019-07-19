/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import java.util.List;

import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;

public interface IBOPascalChain {

   /**
    * 
    * @param start
    * @param max
    * @return
    */
   public List<AccountBO> getAccount(Integer start, Integer max);
   
   public AccountBO getAccount(Integer account);
}
