/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.thread.AbstractBRunnable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public abstract class PCoreTask extends AbstractBRunnable {

   private PCoreTask  parentTask;

   protected PCoreCtx pc;

   public PCoreTask(PCoreCtx pc) {
      super(pc.getUCtx());
      this.pc = pc;
   }

   public PCoreTask getParentTask() {
      return parentTask;
   }

   public boolean isContinue() {
      if (parentTask != null) {
         boolean isParentContinue = parentTask.isContinue();
         if (!isParentContinue) {
            return false;
         }
      }
      return super.isContinue();
   }

   /**
    * Run Task as if it was running sequentially.
    */
   public void runAsSubTaskOf(PCoreTask parentTask) {
      this.parentTask = parentTask;
      runAbstract();
   }

   //#mdebug
   public IDLog toDLog() {
      return pc.toDLog();
   }

   public void toString(Dctx dc) {
      dc.root(this, "PCoreTask");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.nlLvl(parentTask, "ParentTask");
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PCoreTask");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug

}
