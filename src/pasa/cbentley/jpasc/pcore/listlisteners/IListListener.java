/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import java.util.List;

import pasa.cbentley.core.src4.logging.IStringable;

public interface IListListener<T> extends IStringable {

   public void newDataAvailable(List<T> list);
}
