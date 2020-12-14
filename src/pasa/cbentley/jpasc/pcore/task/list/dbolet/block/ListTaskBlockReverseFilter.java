/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.block;

import java.util.Collections;
import java.util.List;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterBlock;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerBlock;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;

/**
 * 
 * Class specifically design for looking in descending order with a filder and a page size.
 * Instead of a range, we think of it as a reference block with a descending range
 * 
 * It can be used to browse the chain back in time using 
 * 
 * With a page size of 1, this task acts as a block finder.
 * <li> The next block down from XXXX with at least 400 txs
 * <li> The 10 next blocks down from XXXX with at least 1 txs
 * 
 *
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskBlockReverseFilter extends ListTaskBlockAbstract {

   private Integer       blockStart;

   private Integer       blockEnd;

   /**
    * must be null when doing ranges
    */
   private final Integer blockLastMustBeNull = null;

   private Integer       pageSize;

   private Integer       blocksInThePast;

   private Integer       blockReference;

   public ListTaskBlockReverseFilter(PCoreCtx pc, IListListener<Block> listener, Integer blockReference, Integer blocksInThePast, IFilterBlock filter, Integer pageSize) {
      super(pc, listener);
      this.blocksInThePast = blocksInThePast;
      this.pageSize = pageSize;
      this.setFilterBlock(filter);
   }

   protected List<Block> findItems(IPascalCoinClient client, Integer start, Integer max) {
      //we have to adapt start-max to a block range for the API
      Integer blockFirst = start;
      Integer blockLast = blockFirst + max - 1; //-1 because blocLast is inclusive and we want an exact page size
      List<Block> blocks = client.getBlocks(blockLastMustBeNull, blockFirst, blockLast);
      Collections.reverse(blocks);
      return blocks;
   }

   protected PagerAbstract<Block> createPagerDefault() {
      PagerBlock pager = new PagerBlock(pc);
      if (blockReference == null) {
         blockReference = pc.getPClient().getBlockCount() - 1;
      }
      pager.setLookUpRangeStart(blockReference);
      pager.setLookUpRangeEnd(blockReference);
      pager.setLookUpRangeEnd(blockReference - blocksInThePast);
      pager.setTimingEnabled(true);
      pager.setManualExactPageSize(false);
      pager.setPageSize(pageSize);
      pager.build();
      return pager;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskBlockRange");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("blockStart", blockStart);
      dc.appendVarWithSpace("blockEnd", blockEnd);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskBlockRange");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
