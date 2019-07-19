/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import pasa.cbentley.core.src4.logging.IStringable;

public interface IBlockListener extends IStringable {

   /**
    * Ping when a new block was returned by daemon.
    * @param newBlock
    * @param millis
    */
   public void pingNewBlock(Integer newBlock, long millis);

   /**
    * Normal Ping when connection is live but no new block was found.
    * @param millis
    */
   public void pingNoBlock(long millis);

   public void pingNewPendingCount(Integer count, Integer oldCount);

   /**
    * A disconnect is detected. Display necessary message
    */
   public void pingDisconnect();
   
   /**
    * A serious error occurred.Display necessary message. User should stop all activity and investigate
    * the daemon.
    * This might be due to difference in API
    */
   public void pingError();
   
}
