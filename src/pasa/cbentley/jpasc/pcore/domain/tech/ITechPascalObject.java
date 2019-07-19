/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.tech;

import pasa.cbentley.byteobjects.tech.ITechByteObject;

public interface ITechPascalObject extends ITechByteObject {

   public static final int PASCAL_BASIC_SIZE = A_OBJECT_BASIC_SIZE + 1;
   
   /**
    * Defines the type of this PascalObject
    */
   public static final int PASC_OFFSET_01_TYPE1 = A_OBJECT_BASIC_SIZE;
}
