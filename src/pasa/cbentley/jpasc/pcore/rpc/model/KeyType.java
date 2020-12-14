package pasa.cbentley.jpasc.pcore.rpc.model;


public enum KeyType {
	
	SECP256K1(714),
	
	SECP384R1(715),
	
	SECP283K1(729),
	
	SECP521R1(716);
	
	private final int value;
    public int getValue() {
        return value;
    }

    private KeyType(int value) {
        this.value = value;
    }
}
