/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import pasa.cbentley.jpasc.pcore.domain.java.AccountJava;

/**
 * 
 * @author Charles Bentley
 *
 */
public interface IAccountValidator {

   public boolean isValidNumber(Integer account);

   /**
    * Use if AccountJava is not needed.
    * <br>
    * When Valid, {@link IAccountValidator#getAccount(String)} will not return null.
    * @param name
    * @return
    */
   public boolean isValidName(String name);

   /**
    * 
    * @param account null if name is not valid
    * @return
    */
   public AccountJava getAccount(String name);

   /**
    * 
    * @param account null if not valid
    * @return
    */
   public AccountJava getAccount(Integer account);

   /**
    * Returns the accounts names that start with the given prefix
    * @param prefix
    * @return
    */
   public String[] getNamesFrom(String prefix);

   /**
    * Returns the accounts number that start with the given prefix
    * @param prefix
    * @return
    */
   public Integer[] getNumberFrom(String prefix);

}
