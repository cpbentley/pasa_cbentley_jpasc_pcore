package pasa.cbentley.jpasc.pcore.rpc.model;

public enum OperationSubType {
	
	//("11")
	TRANSACTION_SENDER(11),
	
	//("12")
	TRANSACTION_RECEIVER(12),
	
	//("13")
	BUYTRANSACTION_BUYER(13),
	
	//("14")
	BUYTRANSACTION_TARGET(14),
	
	//("15")
	BUYTRANSACTION_SELLER(15),	
	
	//("21")
	CHANGE_KEY(21),

	//("31")
	RECOVER(31),
	
	//("41")
	LIST_ACCOUNT_FOR_PUBLIC_SALE(41),
	
	//("42")
	LIST_ACCOUNT_FOR_PRIVATE_SALE(42),
	
	//("51")
	DELIST_ACCOUNT(51),
	
	//("61")
	BUYACCOUNT_BUYER(61),
	
	//("62")
	BUYACCONT_TARGET(62),
	
	//("63")
	BUYACCOUNT_SELLER(63),
	
	//("71")
	CHANGE_KEY_SIGNED(71),
	
	//("81")
	CHANGE_ACCOUNT_INFO(81);
	
	private final int value;
    public int getValue() {
        return value;
    }

    private OperationSubType(int value) {
        this.value = value;
    }
}
