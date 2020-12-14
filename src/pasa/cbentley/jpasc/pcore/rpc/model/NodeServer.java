package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class NodeServer extends ObjectPCore implements Serializable {

   public NodeServer(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * json=ip
    */
   protected String          IP;

   /**
    * json=port
    */
   protected Short           port;

   /**
    * json=lastcon
    */
   protected Long            lastConnect;

   /**
    * json=attempts
    */
   protected Integer         attempts;

   public String getIP() {
      return IP;
   }

   public void setIP(String iP) {
      IP = iP;
   }

   public Short getPort() {
      return port;
   }

   public void setPort(Short port) {
      this.port = port;
   }

   public Long getLastConnect() {
      return lastConnect;
   }

   public void setLastConnect(Long lastConnect) {
      this.lastConnect = lastConnect;
   }

   public Integer getAttempts() {
      return attempts;
   }

   public void setAttempts(Integer attempts) {
      this.attempts = attempts;
   }

}
