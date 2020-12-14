package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;

public class RawOperation implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Count how many operations has rawoperations param
	 */
	//("operations")
	
	protected Integer numOperations;

	/**
	 * Total amount
	 */
	//("amount")
	
	protected Double totalAmount;

	/**
	 * Total fee
	 */
	//("fee")
	
	protected Double totalFee;

	/**
	 * This is the operations in raw format.
	 */
	//("rawoperations")
	
	protected String rawOperations;

	public Integer getNumOperations() {
		return numOperations;
	}

	public void setNumOperations(Integer numOperations) {
		this.numOperations = numOperations;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getRawOperations() {
		return rawOperations;
	}

	public void setRawOperations(String rawOperations) {
		this.rawOperations = rawOperations;
	}
}
