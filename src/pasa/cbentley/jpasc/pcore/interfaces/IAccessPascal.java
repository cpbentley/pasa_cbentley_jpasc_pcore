/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import pasa.cbentley.core.src4.logging.IStringable;

public interface IAccessPascal extends IStringable {

   public IAccessAccountDBolet getAccessAccountDBolet();

   public IAccessBlockDBolet  getAccessBlockDBolet();
}
