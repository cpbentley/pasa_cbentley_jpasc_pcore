package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

public class Connection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * True if this connection is to a server node.False if this connection is a client node
	*/
	protected Boolean isServer;

	//("ip")
	
	protected String IP;

	//("port")
	
	protected Short port;

	/**
	 * seconds of live of this connection
	 */
	//("secs")
	
	protected Integer connectedDurationSec;
	
	/**
	 * Bytes sent
	 */
	//("sent")
	
	protected Long bytesSent;

	/**
	 * Bytes received
	 */
	//("recv")
	
	protected Long bytesReceived;

	/**
	 * Other node App version
	 */
	//("appver")
	
	protected String appVersion;

	/**
	 * Net protocol of other node
	 */
	//("netvar")
	
	protected Integer remoteVersion;

	/**
	 * Net protocol available of other node
	 */
	//("netvar_a")
	
	protected Integer removeAvailableVersion;

	/**
	 * Net timediff of other node (vs wallet)
	 */
	//("timediff")
	
	protected int timeDiff;

	public Boolean getIsServer() {
		return isServer;
	}

	public void setIsServer(Boolean isServer) {
		this.isServer = isServer;
	}

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

	public Integer getConnectedDurationSec() {
		return connectedDurationSec;
	}

	public void setConnectedDurationSec(Integer connectedDurationSec) {
		this.connectedDurationSec = connectedDurationSec;
	}

	public Long getBytesSent() {
		return bytesSent;
	}

	public void setBytesSent(Long bytesSent) {
		this.bytesSent = bytesSent;
	}

	public Long getBytesReceived() {
		return bytesReceived;
	}

	public void setBytesReceived(Long bytesReceived) {
		this.bytesReceived = bytesReceived;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getRemoteVersion() {
		return remoteVersion;
	}

	public void setRemoteVersion(Integer remoteVersion) {
		this.remoteVersion = remoteVersion;
	}

	public Integer getRemoveAvailableVersion() {
		return removeAvailableVersion;
	}

	public void setRemoveAvailableVersion(Integer removeAvailableVersion) {
		this.removeAvailableVersion = removeAvailableVersion;
	}

	public int getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(int timeDiff) {
		this.timeDiff = timeDiff;
	}
}
