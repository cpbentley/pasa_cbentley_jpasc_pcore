package pasa.cbentley.jpasc.pcore.network;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;

public class RPCConnectionThreadSlave implements IStringable {

   private IPascalCoinClient client;

   protected final PCoreCtx  pc;

   private RPCConnection     rpcConnection;

   public RPCConnectionThreadSlave(PCoreCtx pc, RPCConnection rpcConnection, IPascalCoinClient client) {
      this.pc = pc;
      this.rpcConnection = rpcConnection;
      this.client = client;

   }

   public IPascalCoinClient getClient() {
      return client;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "RPCConnectionThreadSlave");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "RPCConnectionThreadSlave");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
