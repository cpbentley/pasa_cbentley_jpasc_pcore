/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter;

import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;

public interface IFilterAccountBO extends IStringable  {

   public boolean filterAccount(AccountBO account);
}
