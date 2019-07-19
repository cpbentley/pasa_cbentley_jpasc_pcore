/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

/**
 * Local entry linking a Public key to a name
 * <br>
 * A Pasa cannot be used as a reliable link to a human because it can be assigned to a new key.
 * <br>
 * So we have to track PublicKey and Pasa
 * @author Charles Bentley
 *
 */
public class AddressBookEntry {

   public long    dateCreated;

   public String  encodedPubKey;

   public String  name;

   /**
    * 
    */
   public Integer pasa;
}