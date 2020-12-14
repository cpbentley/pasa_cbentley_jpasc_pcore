package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

public class SignResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * HEXASTRING with the message to sign
	 */  
	//("digest")
	
	String digest;
	
	/**
	 * HEXASTRING with the public key that used to sign "digest" data
	 */  
	//("enc_pubkey")
	
	String encPubkey;
	
	/**
	 * HEXASTRING with signature
	 */  
	//("signature")
	
	String signature;

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getEncPubkey() {
		return encPubkey;
	}

	public void setEncPubkey(String encPubkey) {
		this.encPubkey = encPubkey;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
}
