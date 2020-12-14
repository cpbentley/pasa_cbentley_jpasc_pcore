/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.ping;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.thread.AbstractBRunnable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IBlockListener;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;

/**
 * Pings the Pascal deamon encapsulated in a {@link PascalCoinClient} for new info such as
 * <li> New block
 * <li> New pending operations
 * <li> 
 * @author Charles Bentley
 *
 */
public class PingRunnable extends AbstractBRunnable {

   private Integer           lastBlock;

   private Integer           lastPending;

   private IBlockListener    blistener;

   private long              timeLastBlockFound;

   private long              timeAccumulated;

   private boolean           isConnected      = false;

   private PCoreCtx          pc;


   private long              sleepTime        = 1000;

   /**
    * Pings the {@link RPCConnection} of the {@link PCoreCtx}.
    * <br>
    * It will try to reconnect automatically every
    * @param pctx
    * @param pclient
    * @param lis
    */
   public PingRunnable(PCoreCtx pctx, IBlockListener lis) {
      super(pctx.getUCtx());
      this.pc = pctx;
      if (lis == null)
         throw new NullPointerException();
      this.blistener = lis;
      lastBlock = new Integer(0);
      lastPending = new Integer(0);
   }

   /**
    * Pings the daemon for 2 Integers:
    * <li> BlockCount
    * <li> PendingCout
    * @return null if a Exception occured. This is worse than a simple disconnect
    */
   private Integer[] getPingData() {
      try {
         Integer blockCount = pc.getPClient().getBlockCount(); //return null if disconnected
         Integer pendingOps = pc.getPClient().getPendingsCount();
         return new Integer[] { blockCount, pendingOps };
      } catch (RPCApiException e) {
         e.printStackTrace();
         return null;
      }

   }

   public void runAbstract() {
      while (isContinue()) {
         long now = System.currentTimeMillis();
         timeAccumulated = now - timeLastBlockFound;

         Integer[] data = getPingData();
         if (data != null) { //return null if disconnected
            sleepTime = pc.getPingParams().getSLEEP_NORMAL();
            Integer blockCount = data[0];
            Integer pendingOps = data[1];
            //#debug
            //pc.toDLog().pFlow("blockCount="+blockCount + " pendingOps="+pendingOps, this, PingRunnable.class, "runAbstract", LVL_04_FINER, true);
            //first send data about new block
            if (blockCount != null) {
               Integer lastBlockMined = blockCount - 1;
               isConnected = true;
               if (lastBlock.intValue() != lastBlockMined.intValue()) {
                  //notify new block to ui that the 
                  if (lastBlock.intValue() != 0) {
                     //don't ping when kickstarting the pinger
                     blistener.pingNewBlock(lastBlockMined, timeAccumulated);
                  }
                  lastBlock = lastBlockMined;
                  timeLastBlockFound = now;
               } else {
                  blistener.pingNoBlock(timeAccumulated);
               }
            } else {
               //there was a disconnect probably
               if (isConnected) {
                  isConnected = false;
                  blistener.pingDisconnect();
               } else {
                  //try to reconnect
                  pc.getRPCConnection().reconnectTry();
               }
               sleepTime =  pc.getPingParams().getSLEEP_DISCONNECT();
            }
            //second. send pending operations. after a new block, pending operations will be reduced
            if (pendingOps != null) {
               if (lastPending.intValue() != pendingOps.intValue()) {
                  blistener.pingNewPendingCount(pendingOps, lastPending);
                  lastPending = pendingOps;
               }
            }
         } else {
            sleepTime = pc.getPingParams().getSLEEP_ERROR();
         }

         try {
            Thread.sleep(sleepTime);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      
      //#debug
      pc.toDLog().pFlow("Pinger has finished", this, PingRunnable.class, "runAbstract", ITechLvl.LVL_05_FINE, true);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "PingRunnable");
      dc.appendVarWithSpace("isConnected", isConnected);
      dc.appendVarWithSpace("lastBlock", lastBlock);
      dc.appendVarWithSpace("lastBlock", lastPending);
      dc.appendVarWithSpace("timeAccumulated", timeAccumulated);
      dc.appendVarWithSpace("timeLastBlockFound", timeLastBlockFound);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PingRunnable");
      dc.appendVarWithSpace("isConnected", isConnected);
      dc.appendVarWithSpace("lastBlock", lastBlock);
      dc.appendVarWithSpace("lastBlock", lastPending);
      dc.appendVarWithSpace("timeAccumulated", timeAccumulated);
      dc.appendVarWithSpace("timeLastBlockFound", timeLastBlockFound);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
}
