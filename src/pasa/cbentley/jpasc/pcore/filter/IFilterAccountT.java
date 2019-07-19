/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter;

import pasa.cbentley.core.src4.logging.IStringable;

public interface IFilterAccountT<T> extends IStringable {

   public boolean filterAccount(T account);
}
