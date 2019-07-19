/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IListenerOperation;

public class ListenerOperations extends ListListenerAbstract implements IListListener<Operation> {

   private IListenerOperation listener;

   public ListenerOperations(PCoreCtx pc, IListenerOperation listener) {
      super(pc);
      this.listener = listener;
   }

   public void newDataAvailable(List<Operation> list) {
      for (Operation operation : list) {
         listener.listenTo(operation);
      }
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListenerOperations");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListenerOperations");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
