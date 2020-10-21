/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.block;

import java.util.ArrayList;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Block;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterBlock;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.task.ListTaskPage;

/**
 * Stub task for listing accounts
 * @author Charles Bentley
 *
 */
public abstract class ListTaskBlockAbstract extends ListTaskPage<Block> {

   private IFilterBlock filterBlock;

   private boolean      isAscendingOrder;

   public ListTaskBlockAbstract(PCoreCtx pc, IListListener<Block> listener) {
      super(pc, listener);
   }

   public IFilterBlock getFilterBlock() {
      return filterBlock;
   }

   protected List<Block> getFiltered(List<Block> list) {
      if (filterBlock != null && list != null) {
         List<Block> filteredList = new ArrayList<Block>(list.size());
         for (Block acc : list) {
            if (filterBlock.filterBlock(acc)) {
               filteredList.add(acc);
            }
         }
         return filteredList;
      } else {
         return list;
      }
   }

   public boolean isAscendingOrder() {
      return isAscendingOrder;
   }

   /**
    * When false, list blocks from highest to lowest
    * When true, list blocks from lowest(older) to highest(younger)
    * @param isAscendingOrder
    */
   public void setAscendingOrder(boolean isAscendingOrder) {
      this.isAscendingOrder = isAscendingOrder;
   }

   public void setFilterBlock(IFilterBlock filterBlock) {
      this.filterBlock = filterBlock;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskBlockAbstract");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.nlLvl(filterBlock, "IFilterBlock");
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskBlockAbstract");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
      dc.nlLvl1Line(filterBlock, "IFilterBlock");
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug
}
