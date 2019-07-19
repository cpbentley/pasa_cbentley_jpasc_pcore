/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.ipc;

public interface IPCAccountMutable {

   public void setName(String name);


   
   /**
    * Commit current values to the underlying safebox of the blockchain
    * @return
    */
   public boolean commit();
}
