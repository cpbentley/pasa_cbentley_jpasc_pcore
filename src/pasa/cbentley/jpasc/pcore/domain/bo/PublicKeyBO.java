/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.bo;

import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.structs.IntBuffer;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * What about dynamic sizes?
 * 
 * A {@link PublicKeyBO} is always built from an {@link AccountBO}.
 * 
 * Collects the offsets at which the key occurs in the chain.
 * 
 * <br>
 * <br>
 * This byteobject is one reference
 * @author Charles Bentley
 *
 */
public class PublicKeyBO extends PascalByteObject {

   private static final int BASIC_SIZE = 0;

   private IntBuffer offsets;

   private PCoreCtx pc;
   
   public PublicKeyBO(PCoreCtx pc, BOCtx boc) {
      super(boc, new byte[BASIC_SIZE]);
      this.pc = pc;
   }
   
   public String getPublicKey() {
      
      int len = 0;
      //depends on the type
      //read x bytes from offset
      String str = pc.getPU().hexStringFromByteArray(data,this.index, len);
      return str;
   }
   
   

}
