/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain;

import java.util.ArrayList;

import com.github.davidbolet.jpascalcoin.api.model.Block;

/**
 * Class for computing statistics of miners as their appear in the block Miner Payload.
 * 
 * We use the first 8 characters to ID the mining entity
 * 
 * This object is listed in a Table.
 * 
 * @author Charles Bentley
 *
 */
public class MinerPool {

   private int              count;

   private String           str8;

   private String           full;

   private ArrayList<Block> blocksMined = new ArrayList<>();

   public MinerPool(String str8) {
      this.str8 = str8;
   }

   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((str8 == null) ? 0 : str8.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      MinerPool other = (MinerPool) obj;
      if (str8 == null) {
         if (other.str8 != null)
            return false;
      } else if (!str8.equals(other.str8))
         return false;
      return true;
   }

   public String getName() {
      return str8;
   }

   public int getNumBlocks() {
      return count;
   }

   public void addBlock(Block block) {
      count++;
      blocksMined.add(block);
   }

   /**
    * 
    * @param payload
    */
   public void updateFull(String payload) {
      if (full == null) {
         //compute the full name based on similar characters with ABC TODO
      }
   }

}