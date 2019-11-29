/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.bo;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.BOCtx;

public class PascalByteObject extends ByteObject {

   public PascalByteObject(BOCtx boc, byte[] data) {
      super(boc, data);
   }

}
