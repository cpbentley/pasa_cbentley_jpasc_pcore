/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.predicates;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterBlock;

/**
 * Will be mapped to a set of {@link IFilterBlock}
 * @author Charles Bentley
 *
 */
public class BlockPredicate extends PPredicate {

   public BlockPredicate(PCoreCtx pc) {
      super(pc);
   }

   private Integer startingBlock;

   private Integer minimumOperations;

   private Integer maximumOperations;

   private boolean isUp;

   private Double  minVolume;

   private Double  maxVolume;

   private Integer maxSearch;

   public Integer getStartingBlock() {
      return startingBlock;
   }

   public Integer getMinimumOperations() {
      return minimumOperations;
   }

   public Integer getMaximumOperations() {
      return maximumOperations;
   }

   public boolean isUp() {
      return isUp;
   }

   public void setStartingBlock(Integer startingBlock) {
      this.startingBlock = startingBlock;
   }

   public void setMinimumOperations(Integer minimumOperations) {
      this.minimumOperations = minimumOperations;
   }

   public void setMaximumOperations(Integer maximumOperations) {
      this.maximumOperations = maximumOperations;
   }

   public void setUp(boolean isUp) {
      this.isUp = isUp;
   }

   public void setMaxSearch(Integer maxSearch) {
      this.maxSearch = maxSearch;
   }

   public Integer getMaxSearch() {
      return maxSearch;
   }

   public boolean isValidNumOperations(Integer count) {
      if(minimumOperations != null) {
         if(count < minimumOperations) {
            return false;
         }
      }
      if(maximumOperations != null) {
         if(count > minimumOperations) {
            return false;
         }
      }
      return true;
   }

}
