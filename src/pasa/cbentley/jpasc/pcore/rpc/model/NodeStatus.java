package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;
import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class NodeStatus extends ObjectPCore implements Serializable {

   public NodeStatus(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long  serialVersionUID = 1L;

   /** 
    * json=ready
    * 
    * Must be true, otherwise Node is not ready to execute operations
    */
   protected Boolean          ready;

   /** 
    * json=ready_s
    * Human readable information about ready or not
    */
   protected String           readyDescriptor;

   /** 
    * json=status_s
    * Human readable information about node status...Running, downloading blockchain, discovering servers...
    */
   protected String           statusDescriptor;

   /** 
    * json=port
    * 
    * Server port
    */
   protected Short            port;

   /** 
    * json=locked
    * 
    * True when this wallet is locked, false otherwise
    */
   protected Boolean          locked;

   /** 
    * json=timestamp
    * 
    * Timestamp of the Node
    */
   protected Long             timestamp;

   /** 
    * json=version
    * 
    * Server version
    */
   protected String           version;

   /** 
    * json=netprotocol
    * 
    * JSON Object with protocol versions
    */
   protected NetProtocol      netProtocol;

   /** 
    * json=blocks
    * 
    * Blockchain blocks
    */
   protected Integer          blocks;

   /** 
    * json=netstats
    * 
    * JSON Object with net information
    */
   protected NetStats         netStats;

   /** 
    * json=nodeservers
    * 
    * JSON Array with servers candidates
    */
   protected List<NodeServer> nodeServers;

   /**
    * json=openssl
    * 
    *  OpenSSL library version as described in OpenSSL_version_num 
    *  ( https://www.openssl.org/docs/man1.1.0/crypto/OPENSSL_VERSION_NUMBER.html )
    */
   protected String           openssl;

   public Boolean getReady() {
      return ready;
   }

   public void setReady(Boolean ready) {
      this.ready = ready;
   }

   public String getReadyDescriptor() {
      return readyDescriptor;
   }

   public void setReadyDescriptor(String readyDescriptor) {
      this.readyDescriptor = readyDescriptor;
   }

   public String getStatusDescriptor() {
      return statusDescriptor;
   }

   public void setStatusDescriptor(String statusDescriptor) {
      this.statusDescriptor = statusDescriptor;
   }

   public Short getPort() {
      return port;
   }

   public void setPort(Short port) {
      this.port = port;
   }

   public Boolean getLocked() {
      return locked;
   }

   public void setLocked(Boolean locked) {
      this.locked = locked;
   }

   public Long getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
   }

   public String getVersion() {
      return version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public NetProtocol getNetProtocol() {
      return netProtocol;
   }

   public void setNetProtocol(NetProtocol netProtocol) {
      this.netProtocol = netProtocol;
   }

   public Integer getBlocks() {
      return blocks;
   }

   public void setBlocks(Integer blocks) {
      this.blocks = blocks;
   }

   public NetStats getNetStats() {
      return netStats;
   }

   public void setNetStats(NetStats netStats) {
      this.netStats = netStats;
   }

   public List<NodeServer> getNodeServers() {
      return nodeServers;
   }

   public void setNodeServers(List<NodeServer> nodeServers) {
      this.nodeServers = nodeServers;
   }

   public String getOpenssl() {
      return openssl;
   }

   public void setOpenssl(String openssl) {
      this.openssl = openssl;
   }

}
