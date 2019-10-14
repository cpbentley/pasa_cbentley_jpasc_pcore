package pasa.cbentley.jpasc.pcore.task.operation;

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

}
