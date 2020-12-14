package pasa.cbentley.jpasc.pcore.rpc.model;

public enum DecryptedPayloadMethod {
	KEY("key"), 
	PWD("pwd") ;
	
	private final String value;
    public String getValue() {
        return value;
    }

    private DecryptedPayloadMethod(String value) {
        this.value = value;
    }
}
