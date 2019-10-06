/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.run;

import java.util.Scanner;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.helpers.BasicPrefs;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.UserLogSystemOut;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.jpasc.pcore.ctx.ITechPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IBlockListener;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;
import pasa.cbentley.jpasc.pcore.ping.PingLogger;

/**
 * This is just a basic example using default parameters.
 * <br>
 * <br>
 * You could create another more elaborate pinger that enables Audio feedback.
 * That would provide audible notification of something gone wrong.
 * <br>
 * @author Charles Bentley
 *
 */
public class RunPingCmdLine implements IBlockListener {

   public static void main(String[] args) {
      UCtx uc = new UCtx();
      UserLogSystemOut userLog = new UserLogSystemOut(uc);
      uc.setUserLog(userLog);

      C5Ctx c5 = new C5Ctx(uc);
      PCoreCtx pc = new PCoreCtx(uc, c5);

      BasicPrefs prefs = new BasicPrefs(uc);
      //don't auto lock wallet on connection
      prefs.putBoolean(ITechPCore.PKEY_AUTO_LOCK, false);
      pc.setPrefs(prefs);

      RunPingCmdLine pr = new RunPingCmdLine(pc);
      pr.run();
   }

   private PCoreCtx   pc;

   private PingLogger pingLogger;

   public RunPingCmdLine(PCoreCtx pc) {
      this.pc = pc;
      RPCConnection rpc = pc.getRPCConnection();
      rpc.addBlockListener(this);
      rpc.connectLocalhost();
      rpc.startPinging();

      pingLogger = new PingLogger(pc);
   }

   public void pingDisconnect() {
   }

   public void pingError() {
   }

   public void pingNewBlock(Integer newBlock, long millis) {
   }

   public void pingNewPendingCount(Integer count, Integer oldCount) {
   }

   public void pingNoBlock(long millis) {

   }

   public void run() {

      pc.getLog().consoleLog("Welcome to JPasc PCore Pinger : enter quit to exit");

      //command line
      Scanner scan = new Scanner(System.in);
      String str = scan.nextLine();
      while (str != null && !str.equals("quit")) {
         str = scan.nextLine();
      }
      scan.close();

      pc.cmdExit();

      pc.getLog().consoleLog("Good bye!");

      System.exit(0);
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PingRunnerCmdLine");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PingRunnerCmdLine");
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }
   //#enddebug

}
