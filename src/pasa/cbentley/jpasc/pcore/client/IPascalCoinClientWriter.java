package pasa.cbentley.jpasc.pcore.client;

import java.util.List;

import pasa.cbentley.jpasc.pcore.rpc.model.KeyType;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.rpc.model.PayLoadEncryptionMethod;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;

public interface IPascalCoinClientWriter {

   /**
    * Executes a change key operation, changing "account" public key for a new one.
    * Note that new one public key can be another Wallet public key, or none.When none, it's like a transaction, tranferring account owner to an external owner
    * @param account: Account number to change key
    * @param accountSigner: Account that signs and pays the fee (must have same public key that delisted account, or be the same)
    * @param newEncPubKey: HEXASTRING - New public key in encoded format
    * @param newB58PubKey: New public key in Base 58 format (the same that Application Wallet exports)
    * @param fee: PASCURRENCY - Fee of the operation
    * @param payload: Payload "item" that will be included in this operation
    * @param payloadMethod: Encode type of the item payload
    * @param pwd: Used to encrypt payload with aes as a payload_method. If none equals to empty password
    * Only one or none of new_b58_pubkey, new_enc_pubkey should be used. Populating both will result in an error.
    * @return If operation is successfull will return a JSON Object in "Operation Object" format. */
   public Operation changeKey(Integer account, Integer accountSigner, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd);

   /**
    * Creates a new Private key and stores it on the wallet, returning an enc_pubkey value
    * @param ecNid: Type of key encryption 
    * @param name: Name to alias this new private key 
    * @return PublicKey object for new generated privateKey
    * */
   public PublicKey addNewKey(KeyType ecNid, String name);

   /**
    * Executes a change key operation, changing "account" public key for a new one, in multiple accounts.
    * Works like changekey
    * @param accounts: List of accounts separated by a comma
    * @param newEncPubKey: HEXASTRING - New public key in encoded format
    * @param newB58PubKey: New public key in Base 58 format (the same that Application Wallet exports)
    * @param fee: PASCURRENCY - Fee of the operation
    * @param payload: Payload "item" that will be included in this operation
    * @param payloadMethod: Encode type of the item payload
    * @param pwd: Used to encrypt payload with aes as a payload_method. If none equals to empty password
    * @return If operation is successfull will return a JSON Array with Operation object items for each key If operation cannot be made, a JSON-RPC error message is returned */
   public List<Operation> changeKeys(String accounts, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd);

   /**
    * Lists an account for sale (public or private)
    * Only one or none of new_b58_pubkey, new_enc_pubkey should be used. Populating both will result in an error.
    * @param accountTarget: Account to be listed
    * @param accountSigner: Account that signs and pays the fee (must have same public key that listed account, or be the same)
    * @param price: price account can be purchased for
    * @param sellerAccount: Account that will receive "price" amount on sell
    * @param newB58PubKey: Base58 encoded public key (for private sale only)
    * @param newEncPubKey: Hex-encoded public key (for private sale only)
    * @param lockedUntilBlock: Block number until this account will be locked (a locked account cannot execute operations while locked)
    * @param fee: PASCURRENCY - Fee of the operation
    * @param payload: Payload "item" that will be included in this operation
    * @param payloadMethod: Encode type of the item payload
    * @param pwd: Used to encrypt payload with aes as a payload_method. If none equals to empty password
    * @return If operation is successful will return a JSON Object in "Operation Object" format. 
    * */
   public Operation listAccountForSale(Integer accountTarget, Integer accountSigner, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Integer lockedUntilBlock, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd);

   /**
    * Delist an account from sale.
    * @param accountTarget: Account to be delisted
    * @param accountSigner: Account that signs and pays the fee (must have same public key that delisted account, or be the same)
    * @param fee: PASCURRENCY - Fee of the operation
    * @param payload: Payload "item" that will be included in this operation
    * @param payloadMethod: Encode type of the item payload
    * @param pwd: Used to encrypt payload with aes as a payload_method. If none equals to empty password
    * @return If operation is successfull will return a JSON Object in "Operation Object" format. */
   public Operation delistAccountForSale(Integer accountTarget, Integer accountSigner, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd);

   /**
    * Executes a transaction operation from "sender" to "target"
    * @param sender: Sender account
    * @param target: Destination account
    * @param amount: Coins to be transferred
    * @param fee: Fee of the operation
    * @param payload: Payload "item" that will be included in this operation
    * @param payloadMethod: Payload "item" that will be included in this operation
    * @param pwd: Used to encrypt payload with aes as a payload_method. If none equals to empty password
    * @return If transaction is successfull will return a JSON Object in "Operation Object" format. Otherwise, will return a JSON-RPC error code with description */
   public Operation sendTo(Integer sender, Integer target, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd);

   /**
    * Buy an account currently listed for sale (public or private)
    * @param buyerAccount: Account number of buyer who is purchasing the account
    * @param accountToPurchase: Account number being purchased
    * @param price: Settlement price of account being purchased
    * @param sellerAccount: Account of seller, receiving payment
    * @param newB58PubKey: Post-settlement public key in base58 encoded format.
    * @param newEncPubKey: Post-settlement public key in hexaDouble encoded format.
    * @param amount: Amount being transferred from buyer_account to seller_account (the settlement). This is a PASCURRENCY value.
    * @param fee: Fee of the operation. This is a PASCURRENCY value.
    * @param payload: Payload "item" that will be included in this operation
    * @param payloadMethod: Encode type of the item payload
    * @param pwd: Used to encrypt payload with aes as a payload_method. If none equals to empty password
    * @return If operation is successfull will return a JSON Object in "Operation Object" format. */
   public Operation buyAccount(Integer buyerAccount, Integer accountToPurchase, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd);

   /**
    * Changes an account Public key, or name, or type value (at least 1 on 3)
    * @param accountTarget: Account being changed
    * @param accountSigner: Account paying the fee (must have same public key as account_target)
    * @param newEncPubKey: New account public key encoded in hexaDouble format
    * @param newB58PubKey: New account public key encoded in base58 format
    * @param newName: New account name encoded in PascalCoin64 format (null means keep current name)
    * @param newType: New account type (null means keep current type)
    * @param fee: PASCURRENCY - Fee of the operation
    * @param payload: Payload "item" that will be included in this operation
    * @param payloadMethod: Encode type of the item payload
    * @param pwd: Used to encrypt payload with aes as a payload_method. If none equals to empty password
    * Only one or none of new_b58_pubkey, new_enc_pubkey should be used. Populating both will result in an error.
    * @return If operation is successfull will return a JSON Object in "Operation Object" format. */
   public Operation changeAccountInfo(Integer accountTarget, Integer accountSigner, String newEncPubKey, String newB58PubKey, String newName, Short newType, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd);

}
