/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class ListenerHolderOperation extends ListenerHolder<Operation> {

   public ListenerHolderOperation(PCoreCtx pc) {
      super(pc);
   }

}
