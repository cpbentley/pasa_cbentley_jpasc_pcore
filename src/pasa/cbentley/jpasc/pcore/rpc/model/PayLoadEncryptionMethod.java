package pasa.cbentley.jpasc.pcore.rpc.model;

public enum PayLoadEncryptionMethod {
	/**
	 * Not encoded. Will be visible for everybody
	 */
	//("none")
	NONE("none"),
	
	/**
	 * Using Public key of "target" account. Only "target" will be able to decrypt this payload
	 */
	//("dest")
	DEST("dest"),
	
	/**
	 * Using sender Public key. Only "sender" will be able to decrypt this payload
	 */
	//("sender")
	SENDER("sender"),
	
	/**
	 * Encrypted data using pwd param
	 */
	//("aes")
	AES("aes");
	
	private final String value;
    public String getValue() {
        return value;
    }

    private PayLoadEncryptionMethod(String value) {
        this.value = value;
    }
}
