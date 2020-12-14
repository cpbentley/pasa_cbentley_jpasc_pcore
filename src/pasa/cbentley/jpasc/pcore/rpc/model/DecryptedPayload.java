package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class DecryptedPayload extends ObjectPCore implements Serializable {

	public DecryptedPayload(PCoreCtx pc) {
      super(pc);
   }

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * json=result
	 * 
	 * Decryption result
	 */
	protected Boolean result;

	/**
	 * json=enc_payload
	 * 
	 * HEXASTRING - Same value than param payload sent
	 */
	protected String originalPayload;

	/**
	 * json=unenc_payload
	 * 
	 * Unencoded value in readable format (no HEXASTRING)
	 */
	protected String unencryptedPayload;
                                           
	/**
	 * json=unenc_hexpayload
	 * 
	 * HEXASTRING - Unencoded value in hexaString
	 */
	protected String unencryptedPayloadHex;

	/**
	 * json=payload_method
	 * 
	 * String - "key" or "pwd"
	 */
	protected DecryptedPayloadMethod payloadMethod;

	/**
	 * json=enc_pubkey
	 * 
	 * HEXASTRING - Encoded public key used to decrypt when method = "key"
	 */
	protected String encodedPubKey;

	/**
	 * json=pwd
	 * 
	 *  String value used to decrypt when method = "pwd"
	 */
	protected String decryptPassword;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getOriginalPayload() {
		return originalPayload;
	}

	public void setOriginalPayload(String originalPayload) {
		this.originalPayload = originalPayload;
	}

	public String getUnencryptedPayload() {
		return unencryptedPayload;
	}

	public void setUnencryptedPayload(String unencryptedPayload) {
		this.unencryptedPayload = unencryptedPayload;
	}

	public String getUnencryptedPayloadHex() {
		return unencryptedPayloadHex;
	}

	public void setUnencryptedPayloadHex(String unencryptedPayloadHex) {
		this.unencryptedPayloadHex = unencryptedPayloadHex;
	}

	public DecryptedPayloadMethod getPayloadMethod() {
		return payloadMethod;
	}

	public void setPayloadMethod(DecryptedPayloadMethod payloadMethod) {
		this.payloadMethod = payloadMethod;
	}

	public String getEncodedPubKey() {
		return encodedPubKey;
	}

	public void setEncodedPubKey(String encodedPubKey) {
		this.encodedPubKey = encodedPubKey;
	}

	public String getDecryptPassword() {
		return decryptPassword;
	}

	public void setDecryptPassword(String decryptPassword) {
		this.decryptPassword = decryptPassword;
	}
}