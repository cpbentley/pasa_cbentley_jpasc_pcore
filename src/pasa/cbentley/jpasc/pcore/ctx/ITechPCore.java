/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.ctx;

import pasa.cbentley.core.src4.interfaces.ITech;


/**
 * 
 * @author Charles Bentley
 *
 */
public interface ITechPCore extends ITech {

   /**
    * Preference key for the auto lock of reference wallet when first login in.
    */
   public static final String PKEY_AUTO_LOCK = "pcore.rpc.autolocklogin";
}
