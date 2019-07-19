/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.block;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Block;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerBlock;

/**
 * 
 * List blocks from current to the past
 * 
 * Paging is useless in this case. so create a page with the number.
 * 
 * So this class is user friendly only for small values of blocksInThePast.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskBlockInThePast extends ListTaskBlockAbstract {

   private Integer blocksInThePast;

   /**
    * Listener will have to be method injected.
    * @param pc
    * @param blocksInThePast
    */
   public ListTaskBlockInThePast(PCoreCtx pc, Integer blocksInThePast) {
      this(pc,null,blocksInThePast);
   }

   public ListTaskBlockInThePast(PCoreCtx pc, IListListener<Block> listener, Integer blocksInThePast) {
      super(pc, listener);
      this.blocksInThePast = blocksInThePast;
   }


   protected List<Block> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.getBlocks(blocksInThePast, null, null);
   }

   protected PagerAbstract<Block> createPagerDefault() {
      //paging is not effective here
      PagerBlock pager = new PagerBlock(pc);
      pager.setLookUpRangeEnd(blocksInThePast);
      pager.setTimingEnabled(false);
      pager.setPageSize(blocksInThePast);
      pager.build();
      return pager;
   }

}
