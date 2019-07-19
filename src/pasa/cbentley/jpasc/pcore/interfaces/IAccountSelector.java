/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

/**
 * Interface to an input mechanism for choosing an account
 * @author Charles Bentley
 *
 */
public interface IAccountSelector {

   /**
    * The name of the account 
    * @return
    */
   public String getAccountName();

   /**
    * The account number
    * @return
    */
   public Integer getAccountInteger();

}
