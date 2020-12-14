/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;

public class ListenerHolderOperation extends ListenerHolder<Operation> {

   public ListenerHolderOperation(PCoreCtx pc) {
      super(pc);
   }

}
