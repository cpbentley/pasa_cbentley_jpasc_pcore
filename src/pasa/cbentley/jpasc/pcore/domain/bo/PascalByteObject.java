/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.bo;

import pasa.cbentley.byteobjects.core.ByteObject;
import pasa.cbentley.byteobjects.ctx.BOCtx;

public class PascalByteObject extends ByteObject {

   public PascalByteObject(BOCtx boc, byte[] data) {
      super(boc, data);
   }

}
