package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;
import java.util.List;

public class MultiOperation  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//("rawoperations")
	
	protected String rawOperations;
	
	/**
	 * ARRAY of objects - When is a transaction, this array contains each sender 
	 */
	//("senders")
	
	List<OpSender> senders;
	
	/**
	 * ARRAY of objects - When is a transaction, this array contains each receiver 
	 */
	//("receivers")
	
	List<OpReceiver> receivers;
	
	/**
	 * "changers" : ARRAY of objects - When accounts changed state 
	 */
	//("changers")
	
	List<OpChanger> changers;

	/** 
	 * Amount of coins received by receivers
	 * PASCURRENCY 
	*/
	//("amount")
	
	protected Double amount;
	
	/** 
	 * Fee of this operation, Equal to "total send" - "total received"
	*/
	//("fee")
	
	protected Double fee;
	
	/** 
	 * Integer with info about how many accounts are signed. Does not check if signature is valid 
	 * for a multioperation not included in blockchain
	*/
	//("signed_count")
	
	protected Integer signedCount;
	
	/** 
	 * Integer with info about how many accounts are pending to be signed
	*/
	//("not_signed_count")
	
	protected Integer notSignedCount;
	
	/**
	 * "signed_can_execute" : Boolean. True if everybody signed. Does not check if MultiOperation is well formed 
	 * or can be added to Network because is an offline call
	 */
	//("signed_can_execute")
	
	protected Boolean signedCanExecute;
	
	/**
	 * "senders_count" : Integer. Number of senders
	 */
	//("senders_count")
	
	protected Integer sendersCount;

	/**
	 * "senders_count" : Integer. Number of receivers
	 */
	//("receivers_count")
	
	protected Integer receiversCount;
	
	/**
	 * "senders_count" : Integer. Number of changers
	 */
	//("changesinfo_count")
	
	protected Integer changesCount;

	public String getRawOperations() {
		return rawOperations;
	}

	public void setRawOperations(String rawOperations) {
		this.rawOperations = rawOperations;
	}

	public List<OpSender> getSenders() {
		return senders;
	}

	public void setSenders(List<OpSender> senders) {
		this.senders = senders;
	}

	public List<OpReceiver> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<OpReceiver> receivers) {
		this.receivers = receivers;
	}

	public List<OpChanger> getChangers() {
		return changers;
	}

	public void setChangers(List<OpChanger> changers) {
		this.changers = changers;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getSignedCount() {
		return signedCount;
	}

	public void setSignedCount(Integer signedCount) {
		this.signedCount = signedCount;
	}

	public Integer getNotSignedCount() {
		return notSignedCount;
	}

	public void setNotSignedCount(Integer notSignedCount) {
		this.notSignedCount = notSignedCount;
	}

	public Boolean getSignedCanExecute() {
		return signedCanExecute;
	}

	public void setSignedCanExecute(Boolean signedCanExecute) {
		this.signedCanExecute = signedCanExecute;
	}
	
	
	
}
