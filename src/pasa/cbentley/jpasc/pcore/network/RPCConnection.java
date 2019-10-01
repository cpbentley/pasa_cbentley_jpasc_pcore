/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.network;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import com.github.davidbolet.jpascalcoin.api.constants.PascalCoinConstants;
import com.github.davidbolet.jpascalcoin.exception.RPCIOException;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.event.BusEvent;
import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.thread.ITechRunnable;
import pasa.cbentley.jpasc.pcore.client.PascalAccountCache;
import pasa.cbentley.jpasc.pcore.client.PascalClientDummy;
import pasa.cbentley.jpasc.pcore.ctx.IEventsPCore;
import pasa.cbentley.jpasc.pcore.ctx.ITechPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.dboletbridge.PascalCoinClientNoLoggerBridge;
import pasa.cbentley.jpasc.pcore.interfaces.IBlockListener;
import pasa.cbentley.jpasc.pcore.ping.PingRunnable;

/**
 * 
 * @author Charles Bentley
 *
 */
public class RPCConnection implements IBlockListener , IEventsPCore {

   /**
    *  Thread safe. when accessed by {@link PingRunnable}.
    */
   private List<IBlockListener> blockListeners      = new Vector<>();

   private Integer              lastBlockMined      = 0;

   private int                  lastBlockMinedValue = 0;

   private Integer              currentPending      = 0;

   boolean                      isLocked            = true;

   private PCoreCtx             pc;

   private IPascalCoinClient    pclient;

   private PingRunnable         pinger;

   private String               ip;

   private Short                port;

   private boolean              isConnected         = false;

   public RPCConnection(PCoreCtx pcc) {
      this.pc = pcc;
      pclient = new PascalClientDummy(pcc);
      isConnected = false;
      mapThreadToRPC = new HashMap<Thread, RPCConnectionThreadSlave>();
      stackUnused = new Stack<RPCConnectionThreadSlave>();
   }

   /**
    * Closes the pinger of this {@link RPCConnection}.
    * Set state.
    * Currently executing threads? interrupt them all
    */
   public void disconnect() {
      if (pinger != null) {
         pinger.requestNewState(ITechRunnable.STATE_3_STOPPED);
      }
      pclient = new PascalClientDummy(pc);
      isConnected = false;
      String ipStr = " " + ip + ":" + port;

      //interrupt all working threads
      
      pc.getLog().consoleLog("Disconnected from Node @ " + ipStr);
   }

   public String getIP() {
      return ip;
   }

   public int getPortAsIntValue() {
      if (port == null) {
         return 0;
      }
      return port;
   }

   /**
    * Called by a thread whenever it wants to access this {@link RPCConnection}.
    * 
    * Note we don't use weakreference so the code can be ported to a framework that does not
    * support this feature.
    * A thread MUST call {@link RPCConnection#threadClosingDown()} when it is done using the {@link RPCConnection}
    * 
    * @return
    */
   public RPCConnectionThreadSlave createConnectionForThread() {
      RPCConnectionThreadSlave con = null;
      if (stackUnused.isEmpty()) {
         PascalCoinClientNoLoggerBridge pclient = new PascalCoinClientNoLoggerBridge(pc, ip, port);
         con = new RPCConnectionThreadSlave(pc, this, pclient);
      } else {
         con = stackUnused.pop();
      }
      return con;
   }

   private HashMap<Thread, RPCConnectionThreadSlave> mapThreadToRPC;

   private Stack<RPCConnectionThreadSlave>           stackUnused;

   /**
    * Called by a thread when it has finished using
    */
   public void threadClosingDown() {
      Thread thread = Thread.currentThread();
      RPCConnectionThreadSlave slave = mapThreadToRPC.remove(thread);
      if (slave != null) {
         //stack is thread safe
         stackUnused.add(slave);
      }
   }

   /**
    * 
    * @return
    */
   public boolean connectLocalhostTestNet() {
      Short port = PascalCoinConstants.DEFAULT_TEST_RPC_PORT;
      boolean b = connectLocalhost(port);
      return b;
   }

   public boolean connectLocalhost(Short port) {
      String ip = "127.0.0.1";
      this.ip = ip;
      this.port = port;
      //TODO introduce client factory
      pclient = new PascalCoinClientNoLoggerBridge(pc, ip, port);

      String ipStr = " " + ip + ":" + port;
      try {
         Integer blockCount = pclient.getBlockCount();
         setLastBlockMined(pclient.getBlockCount() - 1);
         if (blockCount != null) {
            isConnected = true;
            boolean wasLocked = pclient.getNodeStatus().getLocked();
            boolean isNotLocked = !wasLocked;
            if (isNotLocked) {
               pc.getLog().consoleLog("Connection to localhost: Wallet is not locked. Configure lock settings");
            }
            //by default, we try to auto lock the wallet
            boolean autoLockOnLogin = pc.getPrefs().getBoolean(ITechPCore.PKEY_AUTO_LOCK, true);
            if (autoLockOnLogin) {
               //try auto locking
               isLocked = pclient.lock(); //by default when connecting. it locks the wallet
               //return false is there is no password
               if (!isLocked) {
                  pc.getLog().consoleLogError("Jascal thinks that the reference wallet is not password protected.");
                  pc.getLog().consoleLogError("Please secure the wallet with a strong password to protect your keys.");
               } else {
                  pc.getLog().consoleLogDateGreen("Security: Locking wallet on Jascal Login. Change Settings ->");
               }
            } else {
               isLocked = wasLocked;
            }

            StringBBuilder sb = new StringBBuilder(pc.getUCtx());
            sb.append("Connected to Wallet Node with #");
            sb.append(blockCount);
            sb.append(" blocks");
            sb.append(ipStr);
            sb.append(" Version:");
            sb.append(pclient.getNodeStatus().getVersion());
            pc.getLog().consoleLog(sb.toString());
            return true;
         } else {
            isConnected = false;
            pc.getLog().consoleLogError("Failed to connect to " + ipStr);
            return false;
         }
      } catch (RPCIOException e) {
         pc.getLog().consoleLogError("Failed to connect to reference wallet" + ipStr);
         isConnected = false;
         return false;
      } catch (Exception e) {
         pc.getLog().consoleLogError("Failed to connect to reference wallet" + ipStr);
         isConnected = false;
         pc.getLog().consoleLogError("Major exception: " + e.getMessage());
         e.printStackTrace();
         return false;
      }
   }

   /**
    * Blocking call.
    * 
    * @return true if connection was successful
    */
   public boolean connectLocalhost() {
      Short port = PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT;
      return connectLocalhost(port);
   }

   /**
    * It could be a GUI listener. We have no idea.
    * <br>
    * It will be the job of the listener to synch.
    * Usually the GUI Context will act as an intermediary.
    * <br>
    * A Swing context will register on the {@link RPCConnection}
    * <br>
    * Check if not already 
    * @param listener
    */
   public void addBlockListener(IBlockListener listener) {
      if (!blockListeners.contains(listener)) {
         blockListeners.add(listener);
      }
   }

   /**
    * Never null. Will be 0 if not connected
    * @return
    */
   public Integer getLastBlockMined() {
      return lastBlockMined;
   }

   /**
    * 
    * @return
    */
   public int getLastBlockMinedValue() {
      return lastBlockMinedValue;
   }

   public int getNumConnectionSlaves() {
      return mapThreadToRPC.size();
   }

   /**
    * The reference should never be stored outside
    * @return
    */
   public IPascalCoinClient getPClient() {
      return getRPCConnectionThreadSlave().getClient();
   }

   public RPCConnectionThreadSlave getRPCConnectionThreadSlave() {
      Thread thread = Thread.currentThread();
      RPCConnectionThreadSlave slave = mapThreadToRPC.get(thread);
      if (slave == null) {
         slave = createConnectionForThread();
         mapThreadToRPC.put(thread, slave);
      }
      return slave;
   }

   /**
    * Is the {@link RPCConnection} caching accounts in memory?
    * @return
    */
   public boolean isCachedAccount() {
      return pclient instanceof PascalAccountCache;
   }

   /**
    * Is this {@link RPCConnection} actually connected to a real client
    * @return
    */
   public boolean isConnected() {
      return isConnected;
   }

   /**
    * Our current view of the Lock state might be stale and we want an update.
    * 
    * Can be called from any thread?
    */
   public void synchLockStatus() {
      boolean isLockedNewState = getPClient().getNodeStatus().getLocked();
      if(isLocked != isLockedNewState) {
         //send a lock update event
         BusEvent be = pc.getEventBusPCore().createEvent(PID_3_STATUS, EID_3_STATUS_1_LOCK, this);
         be.createParam().setWasIs(isLocked, isLockedNewState);
         be.putOnBus();
      }
   }
   
   public boolean isLocked() {
      return isLocked;
   }

   /**
    * 
    * @return
    */
   public boolean isDaemonLocked() {
      return pclient.getNodeStatus().getLocked();
   }

   /**
    * Returns true if locked.
    * <br>
    * <br>
    * False if it could not lock wallet because no password has been set
    * @return
    */
   public boolean lock() {
      isLocked = pclient.lock();
      return isLocked;
   }

   /**
    * 
    * @param pwd
    * @return true if unlocked
    */
   public boolean unlock(String pwd) {
      boolean r = pclient.unlock(pwd);
      isLocked = !r;
      return !isLocked;
   }

   public void pingDisconnect() {
      for (IBlockListener bl : blockListeners) {
         bl.pingDisconnect();
      }
   }

   public void pingError() {
      for (IBlockListener bl : blockListeners) {
         bl.pingError();
      }
   }

   public void forceUpdateLastMinedBlock() {
      setLastBlockMined(pclient.getBlockCount() - 1);
   }

   /**
    * 
    */
   public void pingNewBlock(Integer newBlock, long millis) {
      setLastBlockMined(newBlock);
      for (IBlockListener bl : blockListeners) {
         bl.pingNewBlock(newBlock, millis);
      }
   }

   public void pingNewPendingCount(Integer count, Integer oldCount) {
      currentPending = count;
      for (IBlockListener bl : blockListeners) {
         bl.pingNewPendingCount(count, oldCount);
      }
   }

   public void pingNoBlock(long millis) {
      for (IBlockListener bl : blockListeners) {
         bl.pingNoBlock(millis);
      }
   }

   public void setEnableCaching(boolean b) {
      if (pclient instanceof PascalAccountCache) {
         ((PascalAccountCache) pclient).cacheEnable(b);
      } else {
         pclient = new PascalAccountCache(pc, pclient);
         ((PascalAccountCache) pclient).cacheEnable(b);
      }
   }

   public void reconnectTry() {
      String ipStr = " " + ip + ":" + port;
      pc.getLog().consoleLog("Reconnecting ... to " + ipStr);
   }

   public void setPClient(IPascalCoinClient pclient) {
      if (pclient == null) {
         throw new NullPointerException();
      }
      this.pclient = pclient;
      //TODO manage connection exception
      try {
         setLastBlockMined(pclient.getBlockCount() - 1);
      } catch (RPCIOException e) {
         //daemon is not connected

      }
      isLocked = pclient.getNodeStatus().getLocked();
   }

   /**
    * Runs the ping thread if not already running.
    */
   public void startPinging() {
      //restart the ping ? when disconnected? TODO
      if (pinger != null) {
         pinger.requestNewState(ITechRunnable.STATE_3_STOPPED);
      }
      
      pinger = new PingRunnable(pc, this);
      pc.getExecutorService().execute(pinger);
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "RPCConnection");
      dc.appendVarWithSpace("lastBlockMined", lastBlockMined);
      dc.appendVarWithSpace("currentPending", currentPending);

      dc.nlLvl(pinger);
      if (pclient instanceof PascalClientDummy) {
         dc.append("PascalClientDummy");
      } else if (pclient instanceof PascalAccountCache) {
         dc.nlLvl((PascalAccountCache) pclient, "PascalAccountCache");
      } else {
         dc.nlLvlO(pclient, "PascalCoinClient");
      }
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "RPCConnection");
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }
   //#enddebug

   public void setLastBlockMined(Integer lastBlockMined) {
      if (lastBlockMined != null) {
         this.lastBlockMined = lastBlockMined;
         lastBlockMinedValue = lastBlockMined.intValue();
      }
   }

}
