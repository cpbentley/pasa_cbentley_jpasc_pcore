package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class NetStats extends ObjectPCore implements Serializable {

   public NetStats(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * json=active
    */
   protected Integer         active;

   /**
    * json=clients
    */
   protected Integer         clients;

   /**
    * json=servers
    */
   protected Integer         servers;

   /**
    * json=servers_t
    */
   protected Integer         serversT;

   /**
    * json=total
    */
   protected Integer         total;

   /**
    * json=tclients
    */
   protected Integer         totalClients;

   /**
    * json=tservers
    */
   protected Integer         totalServers;

   /**
    * json=breceived
    */
   protected Long            bytesReceived;

   /**
    * json=bsend
    */
   protected Long            bytesSent;

   public Integer getActive() {
      return active;
   }

   public void setActive(Integer active) {
      this.active = active;
   }

   public Integer getClients() {
      return clients;
   }

   public void setClients(Integer clients) {
      this.clients = clients;
   }

   public Integer getServers() {
      return servers;
   }

   public void setServers(Integer servers) {
      this.servers = servers;
   }

   public Integer getServersT() {
      return serversT;
   }

   public void setServersT(Integer serversT) {
      this.serversT = serversT;
   }

   public Integer getTotal() {
      return total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public Integer getTotalClients() {
      return totalClients;
   }

   public void setTotalClients(Integer totalClients) {
      this.totalClients = totalClients;
   }

   public Integer getTotalServers() {
      return totalServers;
   }

   public void setTotalServers(Integer totalServers) {
      this.totalServers = totalServers;
   }

   public Long getBytesReceived() {
      return bytesReceived;
   }

   public void setBytesReceived(Long bytesReceived) {
      this.bytesReceived = bytesReceived;
   }

   public Long getBytesSent() {
      return bytesSent;
   }

   public void setBytesSent(Long bytesSent) {
      this.bytesSent = bytesSent;
   }

}
