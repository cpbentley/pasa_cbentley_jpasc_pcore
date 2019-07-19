/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.ping;

public class PingParams {

   private long SLEEP_NORMAL     = 1000;

   private long SLEEP_ERROR      = 10000;

   private long SLEEP_DISCONNECT = 5000;

   /**
    * Time before trying to reconnect
    */
   private long SLEEP_RECONNECT  = 15000;

   public long getSLEEP_NORMAL() {
      return SLEEP_NORMAL;
   }

   public void setSLEEP_NORMAL(long sLEEP_NORMAL) {
      SLEEP_NORMAL = sLEEP_NORMAL;
   }

   public long getSLEEP_ERROR() {
      return SLEEP_ERROR;
   }

   public void setSLEEP_ERROR(long sLEEP_ERROR) {
      SLEEP_ERROR = sLEEP_ERROR;
   }

   public long getSLEEP_DISCONNECT() {
      return SLEEP_DISCONNECT;
   }

   public void setSLEEP_DISCONNECT(long sLEEP_DISCONNECT) {
      SLEEP_DISCONNECT = sLEEP_DISCONNECT;
   }

   /**
    * Time before trying to reconnect
    */
   public long getSLEEP_RECONNECT() {
      return SLEEP_RECONNECT;
   }

   public void setSLEEP_RECONNECT(long sLEEP_RECONNECT) {
      SLEEP_RECONNECT = sLEEP_RECONNECT;
   }
}
