package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;


public class AccountKey  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Account that will sign
	 */
    private Integer account;
	
	/**
	 * Public key that will sign in encoded format
	 */
	private String encPubkey;  
	

	/**
	* Public key that will sign in Base 58 format, also contains a checksum.This is the same value that Application Wallet exports as a public key
	*/
	protected String base58PubKey;


	public Integer getAccount() {
		return account;
	}


	public void setAccount(Integer account) {
		this.account = account;
	}


	public String getEncPubkey() {
		return encPubkey;
	}


	public void setEncPubkey(String encPubkey) {
		this.encPubkey = encPubkey;
	}


	public String getBase58PubKey() {
		return base58PubKey;
	}


	public void setBase58PubKey(String base58PubKey) {
		this.base58PubKey = base58PubKey;
	}
}
