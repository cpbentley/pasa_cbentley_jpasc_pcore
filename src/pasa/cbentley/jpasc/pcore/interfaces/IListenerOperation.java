/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

public interface IListenerOperation {

   public void listenTo(Operation operation);
}
