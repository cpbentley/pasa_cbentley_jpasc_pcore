/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import java.util.List;

import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;

public interface IAccessBlock <T> extends IStringable {

   
   /**
    * Returns the last blocks from current block time of this {@link IAccessBlock} instance.
    * Blocking call
    * @param last
    * @param start start must be smaller or equal to end
    * @param end
    * @return
    */
   public List<T> getBlocksLast(Integer last);

   /**
    * Returns a list of blocks for that page
    * Blocking call
    * @param page cannot be null
    * @return
    */
   public List<T> getBlocksLast(PagerAbstract<T> page);

   
}
