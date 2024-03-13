/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Used when decoding a byte array compressed balance/price value.
 * <br>
 * 
 * For mathematical operations use {@link PascalCoinValue}
 * 
 * @author Charles Bentley
 *
 */
public class PascalCoinDouble {


   private int                          mantisse;

   private int                          molinas;

   protected final PCoreCtx pc;

   public PascalCoinDouble(PCoreCtx pc, int mantisse, int molinas) {
      this.pc = pc;
      this.mantisse = mantisse;
      this.molinas = molinas;
   }

   public PascalCoinDouble(PCoreCtx pc, Double balance) {
      this.pc = pc;
      
      if (balance != null) {
         String balanceStr = pc.getPascalCoinsFormat().format(balance.doubleValue());
         //.toString(balance.doubleValue());
         int indexOfDot = balanceStr.indexOf('.');
         if (indexOfDot != -1) {
            String maintisseStr = balanceStr.substring(0, indexOfDot);
            mantisse = Integer.valueOf(maintisseStr);
            String molinasStr = balanceStr.substring(indexOfDot + 1, balanceStr.length());
            if (molinasStr.length() > pc.getDecimalSize()) {
               //we cannot have a double with more than 4 decimals
               throw new IllegalArgumentException();
            }
            if (!molinasStr.equals("")) {
               molinas = Integer.valueOf(molinasStr);
            }
         }
      }
   }

   public String getMolinasStr() {
      return String.valueOf(molinas);
   }

   public int getMolinasInt() {
      return molinas;
   }

   public int getMantisse() {
      return mantisse;
   }

   public String getString() {
      //for the string we have to make sure we have 00 in front
      StringBBuilder sb = new StringBBuilder(pc.getUC());
      sb.append(mantisse);
      sb.append(".");
      sb.appendPrettyFront(molinas, 4, '0');
      return sb.toString();
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
