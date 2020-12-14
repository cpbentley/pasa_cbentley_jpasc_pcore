package pasa.cbentley.jpasc.pcore.rpc.model;

public enum OperationType {
	
	//("0")
	BLOCKCHAINREWARD(0),
	
	//("1")
	TRANSACTION(1),
	
	//("2")
	CHANGEKEY(2),
	
	/**
	//("3")
	 * Recover founds (lost keys)
	 */
	RECOVERFUNDS(3);
	
	private final int value;
    public int getValue() {
        return value;
    }

    private OperationType(int value) {
        this.value = value;
    }
}
