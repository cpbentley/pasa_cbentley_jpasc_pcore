/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;
import pasa.cbentley.jpasc.pcore.domain.bo.PublicKeyBO;

/**
 * Objects returned by this interface are {@link ByteObject}s reading directly from local chain memory.
 * <br>
 * <li> {@link AccountBO} each row of the chain
 * <li> {@link PublicKeyBO} each row has a Key
 * 
 * Extra care must be taken when writing to it. You must know exactly what you are doing.
 * Otherwise, you are corrupting the "local blockchain truth".
 * <br>
 * 
 * Reading from this is extremely fast.
 * 
 * <br>
 * Only provides data on on {@link AccountBO}.
 * 
 * <br>
 * Data for Operations and Blocks are not living in memory (too big) and must be queried via RPC
 * to the daemon.
 * <br>
 * @author Charles Bentley
 *
 */
public interface IAccessAccountBO extends IAccessAccount<AccountBO> {


}
