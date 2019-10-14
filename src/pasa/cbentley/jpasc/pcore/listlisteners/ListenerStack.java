package pasa.cbentley.jpasc.pcore.listlisteners;

import java.util.List;
import java.util.Stack;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class ListenerStack<T> implements IListListener<T> {

   /**
    * Stack of keys
    */
   private Stack<T>         data;

   protected final PCoreCtx pc;

   public ListenerStack(PCoreCtx pc) {
      this.pc = pc;
      data = new Stack<T>();

   }

   public void newDataAvailable(List<T> list) {
      data.addAll(list);
   }

   public Stack<T> getStack() {
      return data;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "ListenerStack");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListenerStack");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   //#enddebug

}
