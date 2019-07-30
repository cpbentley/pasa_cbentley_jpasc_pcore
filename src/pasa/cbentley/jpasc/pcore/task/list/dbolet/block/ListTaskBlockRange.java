/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.block;

import java.util.Collections;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Block;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerBlock;

/**
 * 
 * List blocks on a range
 * @author Charles Bentley
 *
 */
public class ListTaskBlockRange extends ListTaskBlockAbstract {

   private Integer       blockEnd;

   /**
    * must be null when doing ranges
    */
   private final Integer blockLastMustBeNull = null;

   private Integer       blockStart;

   /**
    * 
    * @param pc
    * @param listener
    * @param blockStart inclusive
    * @param blockEnd inclusive
    */
   public ListTaskBlockRange(PCoreCtx pc, IListListener<Block> listener, Integer blockStart, Integer blockEnd) {
      super(pc, listener);
      this.blockStart = blockStart;
      this.blockEnd = blockEnd;
   }

   protected PagerAbstract<Block> createPagerDefault() {
      //attention when page size is bigger than blockRange.. we have more blocks
      PagerBlock pager = new PagerBlock(pc);
      pager.setLookUpRangeStart(blockStart);
      pager.setLookUpRangeEnd(blockEnd);
      pager.setTimingEnabled(true);
      pager.setAscending(isAscendingOrder());
      pager.setFitRange(true); //we don't want results outside the 
      pager.setManualExactPageSize(false);
      pager.setPageSize(pc.getDefaultPageSizeRootBlock());
      pager.build();
      return pager;
   }

   protected List<Block> findItems(IPascalCoinClient client, Integer start, Integer pageSize) {
      //we have to adapt start-max to a block range for the API
      Integer blockFirst = start;
      Integer blockLast = blockFirst + pageSize - 1;
      //pageSize-1 because blockLast is inclusive and we want an exact page size
      //#debug
      toDLog().pData("blockFirst=" + blockFirst + " blockLast=" + blockLast + " pageSize=" + pageSize, this, ListTaskBlockRange.class, "findItems", LVL_04_FINER, true);
      List<Block> blocks = client.getBlocks(blockLastMustBeNull, blockFirst, blockLast);
      if (isAscendingOrder()) {
         Collections.reverse(blocks);
      }
      return blocks;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskBlockRange");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskBlockRange");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("blockStart", blockStart);
      dc.appendVarWithSpace("blockEnd", blockEnd);
   }

   //#enddebug

}
