/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Used when decoding a byte array compressed balance/price value.
 * <br>
 * @author Charles Bentley
 *
 */
public class PascalCoinDouble {

   public static final PascalCoinDouble ZERO = new PascalCoinDouble(0, 0);

   private int                          mantisse;

   private int                          molinas;

   public PascalCoinDouble(int mantisse, int molinas) {
      this.mantisse = mantisse;
      this.molinas = molinas;
   }

   public PascalCoinDouble(PCoreCtx pc, Double balance) {
      if (balance != null) {
         String balanceStr = Double.toString(balance.doubleValue());
         int indexOfDot = balanceStr.indexOf('.');
         if (indexOfDot != -1) {
            String maintisseStr = balanceStr.substring(0, indexOfDot);
            mantisse = Integer.valueOf(maintisseStr);
            String molinasStr = balanceStr.substring(indexOfDot + 1, balanceStr.length() - 1);
            if (molinasStr.length() > pc.getDecimalSize()) {
               //we cannot have a double with more than 4 decimals
               throw new IllegalArgumentException();
            }
            molinas = Integer.valueOf(molinasStr);
         }
      }
   }

   public double getDoubleValue() {
      return Double.parseDouble(mantisse + "." + molinas);
   }

   public void addCoinMolinas(int coins, int molinas) {
      this.mantisse += coins;
      this.molinas += molinas;
   }

   public void reset() {
      mantisse = 0;
      molinas = 0;
   }
}
