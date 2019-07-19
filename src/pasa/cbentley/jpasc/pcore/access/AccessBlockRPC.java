/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.access;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Block;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessBlockDBolet;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;

/**
 * Access Account data via RPC on the current {@link RPCConnection} client.
 * 
 * @author Charles Bentley
 *
 */
public class AccessBlockRPC implements IAccessBlockDBolet {

   protected final PCoreCtx pc;

   public AccessBlockRPC(PCoreCtx pc) {
      this.pc = pc;
   }

   public List<Block> getBlocksLast(Integer last) {
      return pc.getPClient().getBlocks(last, null, null);
   }

   public List<Block> getBlocksLast(PagerAbstract<Block> page) {
      //#mdebug
      if (page == null) {
         throw new NullPointerException();
      }
      //#enddebug
      return pc.getPClient().getBlocks(null, page.getStart(), page.getEnd());
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "AccessBlockRPC");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AccessBlockRPC");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
