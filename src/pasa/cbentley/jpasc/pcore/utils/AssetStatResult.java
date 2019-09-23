package pasa.cbentley.jpasc.pcore.utils;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class AssetStatResult implements IStringable {

   private int              block         = 0;

   private int              numPublicKeys = 0;

   private int              pasa;

   private boolean          isCanUse;

   private PascalCoinValue  pasc;

   protected final PCoreCtx pc;

   public AssetStatResult(PCoreCtx pc) {
      this.pc = pc;
      pasc = pc.getZero();
   }

   public void addCoins(Double numCoinsPk) {
      pasc = pasc.add(pc.create(numCoinsPk));
   }

   public int getBlock() {
      return block;
   }

   public String getBlockStr() {
      return String.valueOf(block);
   }

   public int getNumPasas() {
      return pasa;
   }

   public String getNumPasaStr() {
      return String.valueOf(pasa);
   }

   public int getNumPublicKeys() {
      return numPublicKeys;
   }

   public String getNumPublicKeysStr() {
      return String.valueOf(numPublicKeys);
   }

   public PascalCoinValue getPascalCoinValue() {
      return pasc;
   }

   public void incrementNumKeys() {
      numPublicKeys += 1;
   }

   public void incrementNumPasas(int numAccounts) {
      pasa += numAccounts;
   }

   public void setBlock(int block) {
      this.block = block;
   }

   public void setNumPasas(int pasa) {
      this.pasa = pasa;
   }

   public void setNumPublicKeys(int pks) {
      this.numPublicKeys = pks;
   }

   public void setPascalCoinValue(PascalCoinValue pasc) {
      this.pasc = pasc;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "AssetStatResult");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AssetStatResult");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }

   public boolean isCanUse() {
      return isCanUse;
   }

   public void setCanUse(boolean isCanUse) {
      this.isCanUse = isCanUse;
   }

   //#enddebug

}