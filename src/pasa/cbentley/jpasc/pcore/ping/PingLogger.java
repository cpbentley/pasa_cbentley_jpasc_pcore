/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.ping;

import java.util.concurrent.TimeUnit;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IUserLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IBlockListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;

/**
 * Log ping calls to {@link IUserLog}
 * 
 * @author Charles Bentley
 *
 */
public class PingLogger implements IBlockListener {

   private boolean  isFirstBlock;

   private int      lastSeenBlock;

   private PCoreCtx pc;

   public PingLogger(PCoreCtx pc) {
      this.pc = pc;
      isFirstBlock = true;
      lastSeenBlock = 0;
      //we listen on new connections
      
      pc.getRPCConnection().addBlockListener(this);
   }

   public void pingDisconnect() {
      pc.getLog().consoleLogError("PingRunnerCmdLine# Disconnected from deamon... who closed the deamon? ");
   }

   public void pingError() {
      pc.getLog().consoleLogError("PingRunnerCmdLine# Serious error occured with deamon... Misconfiguration, Recent API changes? ");
   }

   public void pingNewBlock(Integer newBlock, long millis) {
      if (!isFirstBlock && lastSeenBlock + 1 != newBlock) {
         pc.getLog().consoleLogDateGreen("Synchronizing old blocks... currently at " + newBlock);
      } else {
         long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
         String str = String.format("%d,%d min,sec", minutes, TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

         StringBBuilder sb = new StringBBuilder(pc.getUCtx());
         //TODO introduce sentence for i8n
         sb.append("New Block " + newBlock);
         sb.append(" found after " + str + ".");
         Block block = pc.getPClient().getBlock(newBlock);
         if (block != null) {
            sb.append("\t#ops=" + block.getOperationCount());
            Double fee = block.getFee();
            if (fee != null) {
               String feeStr = pc.getUCtx().getStrU().prettyDouble(fee.doubleValue(), 4);
               sb.append("\tfees=" + feeStr);
            }
            sb.append("\tminer=" + block.getPayload());
         }
         pc.getLog().consoleLogDateGreen(sb.toString());
      }
      lastSeenBlock = newBlock;
      isFirstBlock = false;
   }

   public void pingNewPendingCount(Integer count, Integer oldCount) {
      int difference = count - oldCount;
      String prettyDiff = pc.getUCtx().getStrU().prettyIntPaddStr(Math.abs(difference), 5, " ");
      if (difference < 0) {
         //number of txs mined
         String mined = prettyDiff + " transactions mined.";
         if (count == 0) {
            mined += "No more pending transactions";
         } else {
            mined += count + " transactions are still pending";
         }
         pc.getLog().consoleLogDateGreen(mined);
      } else {
         String prettyCount = pc.getUCtx().getStrU().prettyIntPaddStr(Math.abs(count), 5, " ");
         pc.getLog().consoleLogDate(prettyDiff + " operations recieved." + " Total pending=" + prettyCount);
      }
   }

   public void pingNoBlock(long millis) {

   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PingLogger");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PingLogger");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug

}
