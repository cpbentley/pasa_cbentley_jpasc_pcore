package pasa.cbentley.jpasc.pcore.task.operation;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.operations.OperationJavaAbstract;
import pasa.cbentley.jpasc.pcore.listlisteners.IListenerOperation;
import pasa.cbentley.jpasc.pcore.task.PCoreTask;

public class OperationTaskAbstract extends PCoreTask {

   private IListenerOperation    listenerOperation;

   private OperationJavaAbstract operation;

   public OperationTaskAbstract(PCoreCtx pc, IListenerOperation listener, OperationJavaAbstract operation) {
      super(pc);
      listenerOperation = listener;
      this.operation = operation;
   }

   public void runAbstract() {
      boolean success = operation.execute();
      if (listenerOperation != null) {
         listenerOperation.operationFinished(operation);
      }
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "OperationTaskAbstract");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.nlLvl("OperationJavaAbstract", operation);
      dc.nlLvlNullTitle("IListenerOperation", listenerOperation);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "OperationTaskAbstract");
      toStringPrivate(dc);
      dc.nlLvl1Line(operation, "OperationJavaAbstract");
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
