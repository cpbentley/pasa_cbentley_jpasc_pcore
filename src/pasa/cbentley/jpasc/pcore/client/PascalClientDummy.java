/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.client;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.AccountKey;
import com.github.davidbolet.jpascalcoin.api.model.Block;
import com.github.davidbolet.jpascalcoin.api.model.Connection;
import com.github.davidbolet.jpascalcoin.api.model.DecodeOpHashResult;
import com.github.davidbolet.jpascalcoin.api.model.DecryptedPayload;
import com.github.davidbolet.jpascalcoin.api.model.KeyType;
import com.github.davidbolet.jpascalcoin.api.model.MultiOperation;
import com.github.davidbolet.jpascalcoin.api.model.NodeStatus;
import com.github.davidbolet.jpascalcoin.api.model.OpChanger;
import com.github.davidbolet.jpascalcoin.api.model.OpReceiver;
import com.github.davidbolet.jpascalcoin.api.model.OpSender;
import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.PayLoadEncryptionMethod;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;
import com.github.davidbolet.jpascalcoin.api.model.RawOperation;
import com.github.davidbolet.jpascalcoin.api.model.SignResult;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IUserLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;

public class PascalClientDummy implements IPascalCoinClient {

   private PCoreCtx pcc;

   public PascalClientDummy(PCoreCtx pcc) {
      this.pcc = pcc;
   }

   public Integer addNode(String nodes) {
      getLog().consoleLog("PascalClientDummy#addNode " + nodes);
      return null;
   }

   public Account getAccount(Integer account) {
      getLog().consoleLog("PascalClientDummy#getAccount " + account);
      return null;
   }

   public List<Account> getWalletAccounts(String encPubKey, String b58PubKey, Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#getWalletAccounts " + encPubKey + " " + b58PubKey);
      return null;
   }

   public Integer getWalletAccountsCount(String encPubKey, String b58PubKey) {
      getLog().consoleLog("PascalClientDummy#getWalletAccounts " + encPubKey + " " + b58PubKey);
      return null;
   }

   public List<PublicKey> getWalletPubKeys(Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#getWalletPubKeys " + start + " " + max);
      return null;
   }

   public PublicKey getWalletPubKey(String encPubKey, String b58PubKey) {
      getLog().consoleLog("PascalClientDummy#getWalletPubKey " + encPubKey + " " + b58PubKey);
      return null;
   }

   public Double getWalletCoins(String encPubKey, String b58PubKey) {
      getLog().consoleLog("PascalClientDummy#getWalletPubKey " + encPubKey + " " + b58PubKey);
      return null;
   }

   public Block getBlock(Integer block) {
      getLog().consoleLog("PascalClientDummy#getBlock " + " block=" + block);
      return null;
   }

   public List<Block> getBlocks(Integer last, Integer start, Integer end) {
      getLog().consoleLog("PascalClientDummy#getBlocks " + start + " end=" + end + " last=" + last);
      return null;
   }

   public Integer getBlockCount() {
      getLog().consoleLog("PascalClientDummy#getBlock");
      return null;
   }

   public Operation getBlockOperation(Integer block, Integer opblock) {
      getLog().consoleLog("PascalClientDummy#getBlockOperation " + " block=" + block);
      return null;
   }

   public List<Operation> getBlockOperations(Integer block, Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#getBlockOperations " + start + " max=" + max + " block=" + block);
      return null;
   }

   public List<Operation> getAccountOperations(Integer account, Integer depth, Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#getAccountOperations " + start + " max=" + max + " account=" + account);
      return null;
   }

   public List<Operation> getAccountOperations(Integer account, Integer startblock, Integer depth, Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#getAccountOperations " + start + " max=" + max + " account=" + account);
      return null;
   }

   public List<Operation> getPendings() {
      getLog().consoleLog("PascalClientDummy#getPendings");
      return null;
   }

   public Integer getPendingsCount() {
      getLog().consoleLog("PascalClientDummy#getPendingsCount");
      return null;
   }

   public Operation findOperation(String ophash) {
      getLog().consoleLog("PascalClientDummy#findOperation");
      return null;
   }

   public Operation findNOperation(Integer account, Integer nOperation) {
      getLog().consoleLog("PascalClientDummy#findNOperation");
      return null;
   }

   public List<Operation> findNOperations(Integer account, Integer nOperationMin, Integer nOperationMax, Integer startBlock) {
      getLog().consoleLog("PascalClientDummy#findNOperations");
      return null;
   }

   public List<Account> findAccounts(String name, Integer type, Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#findAccounts");
      return null;
   }

   public List<Account> findAccounts(String name, Boolean exact, Integer type, Boolean listed, Double minBalance, Double maxBalance, Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#findAccounts");
      return null;
   }

   public Operation sendTo(Integer sender, Integer target, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      getLog().consoleLog("PascalClientDummy#sendTo");
      return null;
   }

   public Operation changeKey(Integer account, Integer accountSigner, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      getLog().consoleLog("PascalClientDummy#changeKey");
      return null;
   }

   public List<Operation> changeKeys(String accounts, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      getLog().consoleLog("PascalClientDummy#changeKeys");
      return null;
   }

   public Operation listAccountForSale(Integer accountTarget, Integer accountSigner, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Integer lockedUntilBlock, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      getLog().consoleLog("PascalClientDummy#listAccountForSale");
      return null;
   }

   public Operation delistAccountForSale(Integer accountTarget, Integer accountSigner, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      getLog().consoleLog("PascalClientDummy#delistAccountForSale");
      return null;
   }

   public Operation buyAccount(Integer buyerAccount, Integer accountToPurchase, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      getLog().consoleLog("PascalClientDummy#buyAccount");
      return null;
   }

   public Operation changeAccountInfo(Integer accountTarget, Integer accountSigner, String newEncPubKey, String newB58PubKey, String newName, Short newType, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      getLog().consoleLog("PascalClientDummy#changeAccountInfo");
      return null;
   }

   public RawOperation signSendTo(Integer senderAccount, Integer targetAccount, String senderEncPubKey, String senderB58PubKey, String targetEncPubKey, String targetB58PubKey, Integer lastNOperation, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String rawoperations) {
      getLog().consoleLog("PascalClientDummy#signSendTo");
      return null;
   }

   public RawOperation signChangeKey(Integer account, Integer accountSigner, String oldEncPubKey, String oldB58PubKey, String newEncPubKey, String newB58PubKey, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String rawOperations) {
      getLog().consoleLog("PascalClientDummy#signChangeKey");
      return null;
   }

   public RawOperation signListAccountForSale(Integer accountTarget, Integer accountSigner, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Integer lockedUntilBlock, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String signerB58PubKey, String signerEncPubKey, String rawOperations) {
      getLog().consoleLog("PascalClientDummy#signListAccountForSale");
      return null;
   }

   public RawOperation signDelistAccountForSale(Integer accountTarget, Integer accountSigner, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String signerB58PubKey, String signerEncPubKey, String rawOperations) {
      getLog().consoleLog("PascalClientDummy#signDelistAccountForSale");
      return null;
   }

   public RawOperation signBuyAccount(Integer buyerAccount, Integer accountToPurchase, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Double amount, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String signerB58PubKey, String signerEncPubKey, String rawOperations) {
      getLog().consoleLog("PascalClientDummy#signBuyAccount");
      return null;
   }

   public Operation signChangeAccountInfo(Integer accountTarget, Integer accountSigner, String newEncPubkey, String newB58PubKey, String newName, Short newType, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String signerB58PubKey,
         String signerEncPubKey, String rawOperations) {
      getLog().consoleLog("PascalClientDummy#signChangeAccountInfo");
      return null;
   }

   public List<Operation> operationsInfo(String rawOperations) {
      getLog().consoleLog("PascalClientDummy#operationsInfo");
      return null;
   }

   public List<Operation> executeOperations(String rawOperations) {
      getLog().consoleLog("PascalClientDummy#executeOperations");
      return null;
   }

   public NodeStatus getNodeStatus() {
      getLog().consoleLog("PascalClientDummy#getNodeStatus");
      return null;
   }

   public String encodePubKey(KeyType ecNid, String x, String y) {
      getLog().consoleLog("PascalClientDummy#encodePubKey");
      return null;
   }

   public PublicKey decodePubKey(String encPubKey, String b58PubKey) {
      getLog().consoleLog("PascalClientDummy#decodePubKey");
      return null;
   }

   public String payloadEncrypt(String payload, String payloadMethod, String pwd, String encPubKey, String b58PubKey) {
      getLog().consoleLog("PascalClientDummy#payloadEncrypt");
      return null;
   }

   public DecryptedPayload payloadDecrypt(String payload, String[] pwds) {
      getLog().consoleLog("PascalClientDummy#payloadDecrypt");
      return null;
   }

   public List<Connection> getConnections() {
      getLog().consoleLog("PascalClientDummy#getConnections");
      return null;
   }

   public PublicKey addNewKey(KeyType ecNid, String name) {
      getLog().consoleLog("PascalClientDummy#addNewKey");
      return null;
   }

   public Boolean lock() {
      getLog().consoleLog("PascalClientDummy#lock");
      return null;
   }

   public Boolean unlock(String pwd) {
      getLog().consoleLog("PascalClientDummy#unlock");
      return null;
   }

   public Boolean setWalletPassword(String pwd) {
      getLog().consoleLog("PascalClientDummy#setWalletPassword");
      return null;
   }

   public Boolean stopNode() {
      getLog().consoleLog("PascalClientDummy#stopNode");
      return null;
   }

   public Boolean startNode() {
      getLog().consoleLog("PascalClientDummy#startNode");
      return null;
   }

   public DecodeOpHashResult decodeOpHash(String ophash) {
      getLog().consoleLog("PascalClientDummy#decodeOpHash");
      return null;
   }

   public SignResult signMessage(String digest, String encPubKey, String b58PubKey) {
      getLog().consoleLog("PascalClientDummy#signMessage");
      return null;
   }

   public SignResult verifySign(String digest, String encPubKey, String signature) {
      getLog().consoleLog("PascalClientDummy#verifySign");
      return null;
   }

   public Integer calculateChecksum(Integer account) {
      getLog().consoleLog("PascalClientDummy#calculateChecksum");
      return null;
   }

   public MultiOperation multiOperationAddOperation(String rawoperations, Boolean autoNOperation, List<OpSender> senders, List<OpReceiver> receivers, List<OpChanger> changers) {
      getLog().consoleLog("PascalClientDummy#multiOperationAddOperation");
      return null;
   }

   public MultiOperation multiOperationSignOffline(String rawoperations, List<AccountKey> signers) {
      getLog().consoleLog("PascalClientDummy#multiOperationSignOffline");
      return null;
   }

   public MultiOperation multiOperationSignOnline(String rawoperations) {
      getLog().consoleLog("PascalClientDummy#multiOperationSignOnline");
      return null;
   }

   public MultiOperation multiOperationDeleteOperation(String rawoperations, Integer index) {
      getLog().consoleLog("PascalClientDummy#multiOperationDeleteOperation");
      return null;
   }

   private IUserLog getLog() {
      return pcc.getLog();
   }
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PascalClientDummy");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PascalClientDummy");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pcc.getUCtx();
   }

   //#enddebug
   


}
