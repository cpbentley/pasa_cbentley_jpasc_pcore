/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

public abstract class ListTask<T> extends PCoreTask {

   private IListListener<T> listener;

   public ListTask(PCoreCtx pc, IListListener<T> listener) {
      super(pc);
      this.setListener(listener);
   }

   
   public IListListener<T> getListener() {
      return listener;
   }
   
   public void publish(T t) {
      if (t != null) {
         ArrayList<T> list = new ArrayList<>(1);
         list.add(t);
         //send it to the listener or 
         getListener().newDataAvailable(list);
      }
   }

   /**
    * Send our work to your listener.
    * 
    * @param list
    */
   public void publishList(List<T> list) {
      if (list != null) {
         //send it to the listener or 
         getListener().newDataAvailable(list);
      }
   }


   public void setListener(IListListener<T> listener) {
      this.listener = listener;
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTask");
      toStringPrivate(dc);
      super.toString(dc.sup());
      if(listener != null) {
         dc.nlLvl1Line(listener,"IListListener");
      } else {
         dc.append("IListListner is null");
      }
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTask");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug
   

}
