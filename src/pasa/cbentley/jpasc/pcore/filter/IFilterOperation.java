/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.core.src4.logging.IStringable;

public interface IFilterOperation extends IStringable  {
   
   /**
    * 
    * @param operation
    * @return
    */
   public boolean filterOperation(Operation operation);
}
