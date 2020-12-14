/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.client;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IUserLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.AccountKey;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.rpc.model.Connection;
import pasa.cbentley.jpasc.pcore.rpc.model.DecodeOpHashResult;
import pasa.cbentley.jpasc.pcore.rpc.model.DecryptedPayload;
import pasa.cbentley.jpasc.pcore.rpc.model.KeyType;
import pasa.cbentley.jpasc.pcore.rpc.model.MultiOperation;
import pasa.cbentley.jpasc.pcore.rpc.model.NodeStatus;
import pasa.cbentley.jpasc.pcore.rpc.model.OpChanger;
import pasa.cbentley.jpasc.pcore.rpc.model.OpReceiver;
import pasa.cbentley.jpasc.pcore.rpc.model.OpSender;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.rpc.model.PayLoadEncryptionMethod;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.rpc.model.RawOperation;
import pasa.cbentley.jpasc.pcore.rpc.model.SignResult;

public class PascalClientDummy implements IPascalCoinClient {

   private PCoreCtx pcc;

   public PascalClientDummy(PCoreCtx pcc) {
      this.pcc = pcc;
   }

   public PublicKey addNewKey(KeyType ecNid, String name) {
      //#debug
      getLog().consoleLog("PascalClientDummy#addNewKey");
      return null;
   }

   public Integer addNode(String nodes) {
      //#debug
      getLog().consoleLog("PascalClientDummy#addNode " + nodes);
      return null;
   }

   public Operation buyAccount(Integer buyerAccount, Integer accountToPurchase, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#buyAccount");
      return null;
   }

   public Integer calculateChecksum(Integer account) {
      //#debug
      getLog().consoleLog("PascalClientDummy#calculateChecksum");
      return null;
   }

   public Operation changeAccountInfo(Integer accountTarget, Integer accountSigner, String newEncPubKey, String newB58PubKey, String newName, Short newType, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#changeAccountInfo");
      return null;
   }

   public Operation changeKey(Integer account, Integer accountSigner, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#changeKey");
      return null;
   }

   public List<Operation> changeKeys(String accounts, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#changeKeys");
      return null;
   }

   public DecodeOpHashResult decodeOpHash(String ophash) {
      //#debug
      getLog().consoleLog("PascalClientDummy#decodeOpHash");
      return null;
   }

   public PublicKey decodePubKey(String encPubKey, String b58PubKey) {
      //#debug
      getLog().consoleLog("PascalClientDummy#decodePubKey");
      return null;
   }

   public Operation delistAccountForSale(Integer accountTarget, Integer accountSigner, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#delistAccountForSale");
      return null;
   }

   public String encodePubKey(KeyType ecNid, String x, String y) {
      //#debug
      getLog().consoleLog("PascalClientDummy#encodePubKey");
      return null;
   }

   public List<Operation> executeOperations(String rawOperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#executeOperations");
      return null;
   }

   public List<Account> findAccounts(String name, Boolean exact, Integer type, Boolean listed, Double minBalance, Double maxBalance, Integer start, Integer max) {
      //#debug
      getLog().consoleLog("PascalClientDummy#findAccounts");
      return null;
   }

   public List<Account> findAccounts(String name, Integer type, Integer start, Integer max) {
      //#debug
      getLog().consoleLog("PascalClientDummy#findAccounts");
      return null;
   }

   public List<Account> findAccounts(String name, String nameSearchType, Integer type, String statusType, Double minBalance, Double maxBalance, Integer start, Integer max) {
      return null;
   }

   public List<Account> findAccountsByName(String name, String searchType, Integer start, Integer max) {
      // TODO Auto-generated method stub
      return null;
   }

   public Operation findNOperation(Integer account, Integer nOperation) {
      //#debug
      getLog().consoleLog("PascalClientDummy#findNOperation");
      return null;
   }

   public List<Operation> findNOperations(Integer account, Integer nOperationMin, Integer nOperationMax, Integer startBlock) {
      //#debug
      getLog().consoleLog("PascalClientDummy#findNOperations");
      return null;
   }

   public Operation findOperation(String ophash) {
      //#debug
      getLog().consoleLog("PascalClientDummy#findOperation");
      return null;
   }

   public Account getAccount(Integer account) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getAccount " + account);
      Account a = new Account(pcc);
      a.setAccount(account);
      a.setBalance(0.0d);
      a.setCacheBlock(0);
      a.setName("dummy client");
      a.setnOperation(0);
      a.setType(0);
      return a;
   }

   public List<Operation> getAccountOperations(Integer account, Integer depth, Integer start, Integer max) {
      getLog().consoleLog("PascalClientDummy#getAccountOperations " + start + " max=" + max + " account=" + account);
      return null;
   }

   public List<Operation> getAccountOperations(Integer account, Integer startblock, Integer depth, Integer start, Integer max) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getAccountOperations " + start + " max=" + max + " account=" + account);
      return null;
   }

   public Block getBlock(Integer block) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getBlock " + " block=" + block);
      Block b = new Block(pcc);
      b.setBlock(block);
      return b;
   }

   public Integer getBlockCount() {
      //#debug
      getLog().consoleLog("PascalClientDummy#getBlock");
      return 0;
   }

   public Operation getBlockOperation(Integer block, Integer opblock) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getBlockOperation " + " block=" + block);
      return null;
   }

   public List<Operation> getBlockOperations(Integer block, Integer start, Integer max) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getBlockOperations " + start + " max=" + max + " block=" + block);
      return null;
   }

   public List<Block> getBlocks(Integer last, Integer start, Integer end) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getBlocks " + start + " end=" + end + " last=" + last);
      return null;
   }

   public List<Connection> getConnections() {
      //#debug
      getLog().consoleLog("PascalClientDummy#getConnections");
      return null;
   }

   private IUserLog getLog() {
      return pcc.getLog();
   }

   public NodeStatus getNodeStatus() {
      //#debug
      getLog().consoleLog("PascalClientDummy#getNodeStatus");
      return null;
   }

   public List<Operation> getPendings() {
      //#debug
      getLog().consoleLog("PascalClientDummy#getPendings");
      return null;
   }

   public List<Operation> getPendings(Integer start, Integer max) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getPendings" + start + " max=" + max);
      return null;
   }

   public Integer getPendingsCount() {
      //#debug
      getLog().consoleLog("PascalClientDummy#getPendingsCount");
      return 0;
   }

   public List<Account> getWalletAccounts(String encPubKey, String b58PubKey, Integer start, Integer max) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getWalletAccounts " + encPubKey + " " + b58PubKey);
      return null;
   }

   public Integer getWalletAccountsCount(String encPubKey, String b58PubKey) {
      getLog().consoleLog("PascalClientDummy#getWalletAccounts " + encPubKey + " " + b58PubKey);
      return null;
   }

   public Double getWalletCoins(String encPubKey, String b58PubKey) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getWalletPubKey " + encPubKey + " " + b58PubKey);
      return null;
   }

   public PublicKey getWalletPubKey(String encPubKey, String b58PubKey) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getWalletPubKey " + encPubKey + " " + b58PubKey);
      return null;
   }

   public List<PublicKey> getWalletPubKeys(Integer start, Integer max) {
      //#debug
      getLog().consoleLog("PascalClientDummy#getWalletPubKeys " + start + " " + max);
      return new ArrayList<PublicKey>();
   }

   public Operation listAccountForSale(Integer accountTarget, Integer accountSigner, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Integer lockedUntilBlock, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#listAccountForSale");
      return null;
   }

   public Boolean lock() {
      //#debug
      getLog().consoleLog("PascalClientDummy#lock");
      return null;
   }

   public MultiOperation multiOperationAddOperation(String rawoperations, Boolean autoNOperation, List<OpSender> senders, List<OpReceiver> receivers, List<OpChanger> changers) {
      //#debug
      getLog().consoleLog("PascalClientDummy#multiOperationAddOperation");
      return null;
   }

   public MultiOperation multiOperationDeleteOperation(String rawoperations, Integer index) {
      //#debug
      getLog().consoleLog("PascalClientDummy#multiOperationDeleteOperation");
      return null;
   }

   public MultiOperation multiOperationSignOffline(String rawoperations, List<AccountKey> signers) {
      //#debug
      getLog().consoleLog("PascalClientDummy#multiOperationSignOffline");
      return null;
   }

   public MultiOperation multiOperationSignOnline(String rawoperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#multiOperationSignOnline");
      return null;
   }

   public List<Operation> operationsInfo(String rawOperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#operationsInfo");
      return null;
   }

   public DecryptedPayload payloadDecrypt(String payload, String[] pwds) {
      //#debug
      getLog().consoleLog("PascalClientDummy#payloadDecrypt");
      return null;
   }

   public String payloadEncrypt(String payload, String payloadMethod, String pwd, String encPubKey, String b58PubKey) {
      //#debug
      getLog().consoleLog("PascalClientDummy#payloadEncrypt");
      return null;
   }

   public Operation sendTo(Integer sender, Integer target, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#sendTo");
      return null;
   }

   public Boolean setWalletPassword(String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#setWalletPassword");
      return null;
   }

   public RawOperation signBuyAccount(Integer buyerAccount, Integer accountToPurchase, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Double amount, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String signerB58PubKey, String signerEncPubKey, String rawOperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#signBuyAccount");
      return null;
   }

   public Operation signChangeAccountInfo(Integer accountTarget, Integer accountSigner, String newEncPubkey, String newB58PubKey, String newName, Short newType, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String signerB58PubKey,
         String signerEncPubKey, String rawOperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#signChangeAccountInfo");
      return null;
   }

   public RawOperation signChangeKey(Integer account, Integer accountSigner, String oldEncPubKey, String oldB58PubKey, String newEncPubKey, String newB58PubKey, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String rawOperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#signChangeKey");
      return null;
   }

   public RawOperation signDelistAccountForSale(Integer accountTarget, Integer accountSigner, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String signerB58PubKey, String signerEncPubKey, String rawOperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#signDelistAccountForSale");
      return null;
   }

   public RawOperation signListAccountForSale(Integer accountTarget, Integer accountSigner, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Integer lockedUntilBlock, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String signerB58PubKey, String signerEncPubKey, String rawOperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#signListAccountForSale");
      return null;
   }

   public SignResult signMessage(String digest, String encPubKey, String b58PubKey) {
      //#debug
      getLog().consoleLog("PascalClientDummy#signMessage");
      return null;
   }

   public RawOperation signSendTo(Integer senderAccount, Integer targetAccount, String senderEncPubKey, String senderB58PubKey, String targetEncPubKey, String targetB58PubKey, Integer lastNOperation, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String rawoperations) {
      //#debug
      getLog().consoleLog("PascalClientDummy#signSendTo");
      return null;
   }

   public Boolean startNode() {
      //#debug
      getLog().consoleLog("PascalClientDummy#startNode");
      return null;
   }

   public Boolean stopNode() {
      //#debug
      getLog().consoleLog("PascalClientDummy#stopNode");
      return null;
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

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PascalClientDummy");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pcc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug

   public Boolean unlock(String pwd) {
      //#debug
      getLog().consoleLog("PascalClientDummy#unlock");
      return null;
   }

   public SignResult verifySign(String digest, String encPubKey, String signature) {
      //#debug
      getLog().consoleLog("PascalClientDummy#verifySign");
      return null;
   }

}
