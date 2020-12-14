package pasa.cbentley.jpasc.pcore.client;
///*
// * (c) 2018-2019 Charles-Philip Bentley
// * This code is licensed under MIT license (see LICENSE.txt for details)
// */
//package pasa.cbentley.jpasc.pcore.dboletbridge;
//
//import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClientNoLogger;
//
//import pasa.cbentley.core.src4.ctx.UCtx;
//import pasa.cbentley.core.src4.logging.Dctx;
//import pasa.cbentley.core.src4.logging.IDLog;
//import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
//
//public class PascalCoinClientNoLoggerBridge extends PascalCoinClientNoLogger implements IPascalCoinClient {
//
//   protected final PCoreCtx pc;
//
//   public PascalCoinClientNoLoggerBridge(PCoreCtx pc,String server, Short port) {
//      super(server,port);
//      this.pc = pc;
//   }
//   
//   //#mdebug
//   public IDLog toDLog() {
//      return toStringGetUCtx().toDLog();
//   }
//
//   public String toString() {
//      return Dctx.toString(this);
//   }
//
//   public void toString(Dctx dc) {
//      dc.root(this, "PascalCoinClientBridge");
//      toStringPrivate(dc);
//   }
//
//   public String toString1Line() {
//      return Dctx.toString1Line(this);
//   }
//
//   private void toStringPrivate(Dctx dc) {
//
//   }
//
//   public void toString1Line(Dctx dc) {
//      dc.root1Line(this, "PascalCoinClientBridge");
//      toStringPrivate(dc);
//   }
//
//   public UCtx toStringGetUCtx() {
//      return pc.getUCtx();
//   }
//
//   //#enddebug
//   
//
//}
