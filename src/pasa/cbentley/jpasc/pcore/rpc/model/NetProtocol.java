package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class NetProtocol extends ObjectPCore implements Serializable {
   public NetProtocol(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Net protocol version
    */

   protected Integer         version;

   /**
    * Net protocol available
    */

   protected Integer         availableVersion;

   public Integer getVersion() {
      return version;
   }

   public void setVersion(Integer version) {
      this.version = version;
   }

   public Integer getAvailableVersion() {
      return availableVersion;
   }

   public void setAvailableVersion(Integer availableVersion) {
      this.availableVersion = availableVersion;
   }
   
   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, NetProtocol.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, NetProtocol.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
   

}
