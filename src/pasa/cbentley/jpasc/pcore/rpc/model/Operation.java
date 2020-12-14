package pasa.cbentley.jpasc.pcore.rpc.model;

import java.io.Serializable;
import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.ObjectPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Operation Object, modified on version 3.0
 * @author davidbolet
 *
 */
public class Operation extends ObjectPCore implements Serializable {

   public Operation(PCoreCtx pc) {
      super(pc);
   }

   /**
    * 
    */
   private static final long  serialVersionUID = 1L;

   /**
    * json=senders
    * 
    * ARRAY of objects - When is a transaction, this array contains each sender 
    */
   List<OpSender>             senders;

   /**
    * json=receivers
    * 
    * ARRAY of objects - When is a transaction, this array contains each receiver 
    */
   List<OpReceiver>           receivers;

   /**
    * json=changers
    * 
    * "changers" : ARRAY of objects - When accounts changed state 
    */
   List<OpChanger>            changers;

   /**
    * json=n_operation
    * <br>
    * n_operation param, added in version 2.1.6
    * n_operation is an incremental value to protect double spend
    */
   protected Integer          nOperation;

   /** 
    * json=ophash
    * 
    * Operation hash used to find this operation in the blockchain. (HEXASTRING).
    */
   protected String           opHash;

   /** 
    * json=valid
    * 
    * If operation is invalid, value=false  (optional)
    */
   protected Boolean          valid;

   /** 
    * json=errors
    * If operation is invalid, an error description (optional)
    */
   protected String           errors;

   /** 
    * json=block
    * 
    * Block number (only when valid) 
    */
   protected Integer          block;

   /** 
    * json=time
    * 
    * Block timestamp (only when valid) 
    */
   protected Long             time;

   /** 
    * json=opblock
    * <br>
    * Operation index inside a block(0..operations-1). Note: If opblock = -1 means that is a blockchain reward (only when valid)
    */
   protected Integer          operationBlock;

   /** 
    * json=maturation
    * 
    * Return null when operation is not included on a blockchain yet, 0 means that is included in highest block and so on... (New on Build 1.4.3)
    */
   protected Integer          maturation;

   /** 
    * json=optype
    * 
    * Operation type.
    */
   protected OperationType    type;

   /** 
    * json=subtype
    * 
    * Operation sub-type.
    */
   protected OperationSubType subType;

   /** 
    * json=account
    * 
    * Account affected by this operation.Note: A transaction has 2 affected accounts.
    */
   protected int              account;

   /** 
    * json=optxt
    * Account affected by this operation.Note: A transaction has 2 affected accounts.
    */
   protected String           typeDescriptor;

   /** 
    * json=amount
    * 
    * Amount of coins transferred from sender_account to dest_account (Only apply when optype = 1) (PASCURRENCY)
    */
   protected Double           amount;

   /**
    * json=amount_s
    */
   private String           amountString;

   /** 
    * json=fee
    * Fee of this operation (PASCURRENCY)
    */
   protected Double           fee;

   /**
    * json=fee_s
    */
   private String           feeString;

   /** 
    * json=balance
    * 
    * Balance of account after this block is introduced in the Blockchain (PASCURRENCY).
    * balance is a calculation based on current safebox account balance and previous operations, it's only returned on pending operations and account operations
    */
   protected Double           balance;

   /** 
    * json=sender_account
    * 
    * Sender account in a single transaction (optype = 1)
    */
   protected Integer          senderAccount;

   /** 
    * json=dest_account
    * 
    * Destination account in a transaction (optype = 1)
    */
   protected Integer          destAccount;

   /** 
    * json=signer_account
    * 
    * Account that signed operation, and pays network fee.
    */
   protected Integer          signerAccount;

   /** 
    * json=enc_pubkey
    * 
    * Encoded protected key used in either a change key operation (optype = 2) or a list account for sale (private sale) operation. (HEXASTRING).
    * See decodepubkey method to deconstruct datatype.
    */
   protected String           encPubKey;

   /** 
    * json=payload
    * 
    * Operation payload in hex format
    */
   protected String           payLoad;

   /** 
    * json=old_ophash
    * Operation hash calculated using V1 algorithm. Only provided for operations before V2 activation. (HEXASTRING).
    */
   protected String           v1Ophash;

   public Integer getNOperation() {
      return this.nOperation;
   }

   public void setNOperation(Integer nOperation) {
      this.nOperation = nOperation;
   }

   public String getOpHash() {
      return opHash;
   }

   public void setOpHash(String opHash) {
      this.opHash = opHash;
   }

   public Boolean getValid() {
      return valid;
   }

   public void setValid(Boolean valid) {
      this.valid = valid;
   }

   public String getErrors() {
      return errors;
   }

   public void setErrors(String errors) {
      this.errors = errors;
   }

   public Integer getBlock() {
      return block;
   }

   public void setBlock(Integer block) {
      this.block = block;
   }

   public Long getTime() {
      return time;
   }

   public void setTime(Long time) {
      this.time = time;
   }

   public Integer getOperationBlock() {
      return operationBlock;
   }

   public void setOperationBlock(Integer operationBlock) {
      this.operationBlock = operationBlock;
   }

   public Integer getMaturation() {
      return maturation;
   }

   public void setMaturation(Integer maturation) {
      this.maturation = maturation;
   }

   public OperationType getType() {
      return type;
   }

   public void setType(OperationType type) {
      this.type = type;
   }

   public OperationSubType getSubType() {
      return subType;
   }

   public void setSubType(OperationSubType subType) {
      this.subType = subType;
   }

   public int getAccount() {
      return account;
   }

   public void setAccount(int account) {
      this.account = account;
   }

   public String getTypeDescriptor() {
      return typeDescriptor;
   }

   public void setTypeDescriptor(String typeDescriptor) {
      this.typeDescriptor = typeDescriptor;
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

   public Double getBalance() {
      return balance;
   }

   public void setBalance(Double balance) {
      this.balance = balance;
   }

   public Integer getSenderAccount() {
      return senderAccount;
   }

   public void setSenderAccount(Integer senderAccount) {
      this.senderAccount = senderAccount;
   }

   public Integer getDestAccount() {
      return destAccount;
   }

   public void setDestAccount(Integer destAccount) {
      this.destAccount = destAccount;
   }

   public Integer getSignerAccount() {
      return signerAccount;
   }

   public void setSignerAccount(Integer signerAccount) {
      this.signerAccount = signerAccount;
   }

   public String getEncPubKey() {
      return encPubKey;
   }

   public void setEncPubKey(String encPubKey) {
      this.encPubKey = encPubKey;
   }

   public String getPayLoad() {
      return payLoad;
   }

   public void setPayLoad(String payLoad) {
      this.payLoad = payLoad;
   }

   public String getV1Ophash() {
      return v1Ophash;
   }

   public void setV1Ophash(String v1Ophash) {
      this.v1Ophash = v1Ophash;
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

   public String getFeeString() {
      return feeString;
   }

   public void setFeeString(String feeString) {
      this.feeString = feeString;
   }

   public String getAmountString() {
      return amountString;
   }

   public void setAmountString(String amountString) {
      this.amountString = amountString;
   }

}
