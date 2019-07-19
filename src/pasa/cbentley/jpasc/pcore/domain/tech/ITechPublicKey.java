/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.tech;

import pasa.cbentley.core.src4.interfaces.ITech;

public interface ITechPublicKey extends ITech {

   /**
    * Selectors for listing keys
    */
   public static final int TYPE_0_ALL          = 0;

   public static final int TYPE_1_PRIVATE      = 1;

   public static final int TYPE_2_PRIVATE_EDIT = 2;

   public static final int TYPE_5_INSIDE       = 5;

}
