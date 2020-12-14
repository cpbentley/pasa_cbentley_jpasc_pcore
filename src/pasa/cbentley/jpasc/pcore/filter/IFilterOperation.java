/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter;

import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;

public interface IFilterOperation extends IStringable  {
   
   /**
    * 
    * @param operation
    * @return
    */
   public boolean filterOperation(Operation operation);
}
