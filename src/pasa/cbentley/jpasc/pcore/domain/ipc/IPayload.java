/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.ipc;

public interface IPayload {

   public static final int PAYLOAD_ENCRYPTION_00_NONE   = 0;

   public static final int PAYLOAD_ENCRYPTION_01_DEST   = 1;

   public static final int PAYLOAD_ENCRYPTION_02_SENDER = 2;

   public static final int PAYLOAD_ENCRYPTION_03_AES    = 3;
}
