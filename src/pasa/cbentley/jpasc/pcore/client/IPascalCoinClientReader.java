/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.client;

import java.util.List;

import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.rpc.model.Connection;
import pasa.cbentley.jpasc.pcore.rpc.model.DecryptedPayload;
import pasa.cbentley.jpasc.pcore.rpc.model.KeyType;
import pasa.cbentley.jpasc.pcore.rpc.model.NodeStatus;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;

/**
 * Only methods that read the chain
 * @author Charles Bentley
 *
 */
public interface IPascalCoinClientReader extends IStringable {

   /**
   * Returns a JSON Object with account information including pending operations not included in blockchain yet, but affecting this account.       
   * To know if there are pending operations, must look at updated_b param.It tells last block that modified this account.
   * If this number is equal to blockchain blocks then this account is affected by pending operations (send/receive or change key)
   * @param account: Cardinal containing account number 
   * @return Account the account object */
   public Account getAccount(Integer account);

   /**
    * Returns a JSON array with all wallet accounts.
    * @param encPubKey:  HEXASTRING (optional). If provided, return only accounts of this public key
    * @param b58PubKey: String (optional). If provided, return only accounts of this public key. Note: If use enc_pubkey and b58_pubkey together and is not the same public key, will return an error
    * @param start: Integer (optional, default = 0). If provided, will return wallet accounts starting at this position (index starts at position 0)
    * @param max: Integer (optional, default = 100). If provided, will return max accounts. If not provided, max=100 by default
    * @return Each JSON array item contains an Account Object */
   public List<Account> getWalletAccounts(String encPubKey, String b58PubKey, Integer start, Integer max);

   /**
    * Get number of available wallet accounts (total or filtered by public key)
    * @param encPubKey: HEXASTRING (optional). If provided, return only accounts of this public key
    * @param b58PubKey: String (optional). If provided, return only accounts of this public key. Note: If use enc_pubkey and b58_pubkey together and is not the same public key, will return an error
    * @return Returns an integer with total */
   public Integer getWalletAccountsCount(String encPubKey, String b58PubKey);

   /** 
   * Returns a JSON Array with all pubkeys of the Wallet (address)
   * @param start: Integer (optional, default = 0). If provided, will return wallet public keys starting at this position (index starts at position 0)
   * @param max: Integer (optional, default = 100). If provided, will return max public keys. If not provided, max=100 by default
   * @return Returns a JSON Array with "Public Key Object" */
   public List<PublicKey> getWalletPubKeys(Integer start, Integer max);

   /**
    * Returns a JSON Object with a public key if found in the Wallet
    * @param encPubKey: HEXASTRING
    * @param b58PubKey: String
    * Note: If use enc_pubkey and b58_pubkey together and is not the same public key, will return an error
    * @return Returns a JSON Object with a "Public Key Object" */
   public PublicKey getWalletPubKey(String encPubKey, String b58PubKey);

   /**
    * Returns coins balance.
    * 
    * @param encPubKey: HEXASTRING (optional). If provided, return only this public key balance
    * @param b58PubKey: String (optional). If provided, return only this public key balance 
    * If use encPubKey and b58PubKey together and is not the same public key, will throw an error
    * @return Returns a PASCURRENCY value with maximum 4 Doubles */
   public Double getWalletCoins(String encPubKey, String b58PubKey);

   /**
    * Returns a JSON Object with a block information
    * @param block: Block number (0..blocks count-1)
    * @return Returns a JSON Object with a "Block Object" */
   public Block getBlock(Integer block);

   /**
    * Returns a JSON Array with blocks information from "start" to "end" (or "last" n blocks) Blocks are returned in DESCENDING order. 
    * @see getBlock
    * @param last: Last n blocks in the blockchain (n&gt;0 and n&lt;=1000)
    * @param start: From this block
    * @param end: To this block
    * Must use last exclusively, or start and end, or error
    * @return JSON Array with blocks information */
   public List<Block> getBlocks(Integer last, Integer start, Integer end);

   public List<Block> getBlocksASC(Integer start, Integer end);

   /**
    * Returns an Integer with blockcount of node
    * @return Total blocks */
   public Integer getBlockCount();

   /**
    * Returns a JSON Object with an operation inside a block
    * @param block: Block number
    * @param opblock: Operation (0..operations-1) of this block
    * @return JSON Object with a "Operation Object" */
   public Operation getBlockOperation(Integer block, Integer opblock);

   /**
    * Returns a JSON Array with all operations of specified block Operations are returned in DESCENDING order
    * @param block: Block number
    * @param start: Integer (optional, default = 0). If provided, will start at this position (index starts at position 0)
    * @param max: Integer (optional, default = 100). If provided, will return max registers. If not provided, max=100 by default
    * @return Returns a JSON Array with "Operation Object" items */
   public List<Operation> getBlockOperations(Integer block, Integer start, Integer max);

   /**
    * Return a JSON Array with "Operation Object" items. Operations made over an account Operations are returned in DESCENDING order
    * @param account: Account number (0..accounts count-1)
    * @param depth: Integer - (Optional, default value 100) Depth to search on blocks where this account has been affected. Allowed to use deep as a param name too.
    * @param start: Integer (optional, default = 0). If provided, will start at this position (index starts at position 0). If start is -1, then will include pending operations, otherwise only operations included on the blockchain
    * @param max: Integer (optional, default = 100). If provided, will return max registers. If not provided, max=100 by default
    * @return Returns an array holding operations made over account in "Operation Object" format */
   public List<Operation> getAccountOperations(Integer account, Integer depth, Integer start, Integer max);

   /**
    * Return a JSON Array with "Operation Object" items. Operations made over an account Operations are returned in DESCENDING order
    * @param account: Account number (0..accounts count-1)
    * @param startblock: Integer - (Optional, default value 0) start searching backwards on a specific block where this account has been affected. Allowed to use deep as a param name too.
    * @param depth: Integer - (Optional, default value 100) Depth to search on blocks where this account has been affected. Allowed to use deep as a param name too.
    * @param start: Integer (optional, default = 0). If provided, will start at this position (index starts at position 0). If start is -1, then will include pending operations, otherwise only operations included on the blockchain
    * @param max: Integer (optional, default = 100). If provided, will return max registers. If not provided, max=100 by default
    * @return Returns an array holding operations made over account in "Operation Object" format */
   public List<Operation> getAccountOperations(Integer account, Integer startblock, Integer depth, Integer start, Integer max);

   /**
    * Return a JSON Array with "Operation Object" items with operations pending to be included at the Blockchain.
    * @return Returns an array holding pending operations in "Operation Object" format */
   public List<Operation> getPendings();

   public List<Operation> getPendings(Integer start, Integer max);

   /**
    * Return an Integer with item count of operations pending to be included at the Blockchain.
    * @return Returns an Integer with number of pending operations*/
   public Integer getPendingsCount();

   /**
    * Return a JSON Object in "Operation Object" format.
    * @param ophash: HEXASTRING - Value ophash received on an operation
    *  @return Returns "Operation Object" format JSON object */
   public Operation findOperation(String ophash);

   /**
    * Search an operation made to an account based on n_operation field 
    * Return a JSON Object in "Operation Object" format.
    * @param account Account number
    * @param nOperation: is an incremental value to protect double spend 
    *  @return Returns "Operation Object" format JSON object */
   public Operation findNOperation(Integer account, Integer nOperation);

   /**
    * Return a JSON Array with "Operation Object" Search an operation made to an account based on n_operation .
    * @param account Account number
    * @param nOperationMin Min n_operation to search
    * @param nOperationMax  Max n_operation to search
    * @param startBlock  (optional) Block number to start search. 0=Search all, including pending operations
    * @return Returns an array holding pending operations in "Operation Object" format
    */
   public List<Operation> findNOperations(Integer account, Integer nOperationMin, Integer nOperationMax, Integer startBlock);

   /**
   * Find accounts by name/type and returns them as an array of Account objects
   * @param name: If has value, will return the account that match name
   * @param type: If has value, will return accounts with same type
   * @param start: Starting account number (by default, 0) 
   * @param max: Max of accounts returned in array (by default, 100) 
   * @return list of accounts matching selected criteria
   * */
   public List<Account> findAccounts(String name, Integer type, Integer start, Integer max);

   /**
    * Find accounts by name/type/forSale/balanceMin/balanceMax and returns them as an array of Account objects
    * @param name If has value, will be used to find the account that matches name
    * @param exact Used in conjunction with name. By default is set to true. If it's true,will return that matches exactly with name, otherwise will return all Account objects 
    *              whose name starts with name's parameter value
    * @param type  If has value, will return accounts with same type
    * @param listed By default set to false. If it's set to true, will return only accounts for sale
    * @param minBalance If set, will return accounts whose balance is greater or equal than its value
    * @param maxBalance If set, will return accounts whose balance is less or equal than its value
    * @param start Starting account number (by default, 0) 
    * @param max Max of accounts returned in result's array (by default, 100) 
    * @return list of accounts matching selected criteria
    */
   public List<Account> findAccounts(String name, Boolean exact, Integer type, Boolean listed, Double minBalance, Double maxBalance, Integer start, Integer max);

   /**
    * Encodes a public key based on params information
    * @param ecNid: key type 
    * @param x: HEXASTRING with x value of public key 
    * @param y: HEXASTRING with y value of public key 
    * @return Returns a HEXASTRING with encoded public key */
   public String encodePubKey(KeyType ecNid, String x, String y);

   /**
    * Decodes an encoded public key
    * @param encPubKey: HEXASTRING with encoded public key 
    * @param b58PubKey: String. b58_pubkey is the same value that Application Wallet exports as a public key 
    * Note: If use enc_pubkey and b58_pubkey together and is not the same public key, will return an error
    * @return Returns a JSON Object with a "Public Key Object" */
   public PublicKey decodePubKey(String encPubKey, String b58PubKey);

   /**
    * Returns a HEXASTRING with decrypted text (a payload) using private keys in the wallet or a list of Passwords (used in "aes" encryption)
    *
    * @param payload: HEXASTRING - Encrypted data 
    * @param pwds: List of passwords to use 
    * If using one of private keys is able to decrypt payload then returns value "key" in payload_method and enc_pubkey contains encoded public key in HEXASTRING
    * If using one of passwords to decrypt payload then returns value "pwd" in payload_method and pwd contains password used
    * @return Decryped payload */
   public DecryptedPayload payloadDecrypt(String payload, String[] pwds);

}
