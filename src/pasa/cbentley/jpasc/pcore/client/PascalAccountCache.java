/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClient;
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

import pasa.cbentley.core.src4.ctx.IEventsCore;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.event.BusEvent;
import pasa.cbentley.core.src4.event.IEventConsumer;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;

/**
 * Cache between {@link IDataAccess} and the {@link PascalCoinClient}.
 * 
 * Account queries .
 * 
 * DataAccess may clear the cache or request an update on a specific account.
 * <br>
 * 
 * Safebox caching:
 * <li> Account 
 * 
 * Upon starting, download blocks from the last checkpoint.
 * Update Account that were involved in the the block txs.
 * Request data with RPC (or update yourself)
 * Snapshot becomes the real live.
 * 
 * Cache uses 
 * @author Charles Bentley
 *
 */
public class PascalAccountCache implements IPascalCoinClient, IEventConsumer, IStringable {

   private IPascalCoinClient  client;

   private PCoreCtx           pc;

   private boolean            isCaching;

   private ArrayList<Account> accounts          = new ArrayList<>();

   /**
    * Do not return this client. always return a copy
    */
   private ArrayList<Account> walletAccounts    = null;

   private LinkedList<Block>  blocks            = new LinkedList<>();

   private ArrayList<Block>   lonelyBlocks      = new ArrayList<>();

   private boolean            isWalletCacheFull = false;

   public PascalAccountCache(PCoreCtx pc, IPascalCoinClient client) {
      this.pc = pc;
      this.client = client;

      //register for memory events..so as to clear cache automatically
      pc.getUCtx().getEventBusRoot().addConsumer(this, IEventsCore.PID_3_MEMORY, IEventsCore.EID_MEMORY_0_ANY);

   }

   public void cacheEnable(boolean b) {
      isCaching = b;
   }

   public Integer addNode(String nodes) {
      return client.addNode(nodes);
   }

   public void cacheClear() {
      for (int i = 0; i < accounts.size(); i++) {
         accounts.set(i, null);
      }
   }

   /**
    * Force the cache to update
    */
   public void cacheForceUpdate(boolean v) {
      this.isCaching = v;
   }

   private List<Account> cacheGetWalletAccounts(String encPubKey, String b58PubKey, Integer start, Integer max) {
      List<Account> accounts = null;
      Integer block = pc.getRPCConnection().getLastBlockMined();
      if (isCaching) {
         accounts = client.getWalletAccounts(encPubKey, b58PubKey, start, max);
      }

      accounts = client.getWalletAccounts(encPubKey, b58PubKey, start, max);

      return accounts;
   }

   private Account cacheGetAccount(Integer account) {
      if (isCaching) {
         Account ac = accounts.get(account);
         if (ac != null) {
            return ac;
         }
      }
      Account ac = client.getAccount(account);
      ac.setCacheBlock(pc.getRPCConnection().getLastBlockMined());
      accounts.set(account, ac);
      return ac;
   }

   public Account getAccount(Integer account) {
      return cacheGetAccount(account);
   }

   public List<Account> getWalletAccounts(String encPubKey, String b58PubKey, Integer start, Integer max) {
      return client.getWalletAccounts(encPubKey, b58PubKey, start, max);
   }

   public Integer getWalletAccountsCount(String encPubKey, String b58PubKey) {
      return client.getWalletAccountsCount(encPubKey, b58PubKey);
   }

   public List<PublicKey> getWalletPubKeys(Integer start, Integer max) {
      return client.getWalletPubKeys(start, max);
   }

   public PublicKey getWalletPubKey(String encPubKey, String b58PubKey) {
      return client.getWalletPubKey(encPubKey, b58PubKey);
   }

   public Double getWalletCoins(String encPubKey, String b58PubKey) {
      return client.getWalletCoins(encPubKey, b58PubKey);
   }

   public Block getBlock(Integer block) {
      //first look up in the cache. blocks are never stable
      if (isCaching) {
         int first = blocks.getFirst().getBlock(); //highest
         int last = blocks.getLast().getBlock();
         if (block <= first && block >= last) {
            //we have it in the cache.
            int index = first - block;
            return blocks.get(index);
         } else {
            for (Block blockAlone : lonelyBlocks) {
               if (blockAlone.getBlock().intValue() == block.intValue()) {
                  return blockAlone;
               }
            }
            //not found
            Block found = client.getBlock(block);
            if (found != null) {
               if (found.getBlock() == first + 1) {
                  blocks.addFirst(found);
               } else if (found.getBlock() == last - 1) {
                  blocks.addLast(found);
               } else {
                  lonelyBlocks.add(found);
               }
            }
            return found;
         }
      } else {
         return client.getBlock(block);
      }
   }

   public List<Block> getBlocks(Integer last, Integer start, Integer end) {
      if (isCaching) {
         //do we update last? or trust our current block?
         if (last != null) {
            //we want last blocks from current block
            //assume we are listening to new block from rpc connection
            int currentBlock = pc.getRPCConnection().getLastBlockMined();
            int firstCachedBlock = blocks.getFirst().getBlock(); //highest
            int lastCachedBlock = blocks.getLast().getBlock();
            int lastBlock = currentBlock - last;
            if (currentBlock <= firstCachedBlock && lastBlock >= lastCachedBlock) {
               //fully cached
               ArrayList<Block> myBlocks = new ArrayList<>(last);
               for (int i = 0; i < lastBlock; i++) {
                  myBlocks.add(blocks.get(i));
               }
               return myBlocks;
            } else {
               //fill the cache with blocks
            }

         }
         List<Block> foundBlocks = client.getBlocks(last, start, end);

      }
      return client.getBlocks(last, start, end);
   }

   public Integer getBlockCount() {
      return client.getBlockCount();
   }

   public Operation getBlockOperation(Integer block, Integer opblock) {
      return client.getBlockOperation(block, opblock);
   }

   public List<Operation> getBlockOperations(Integer block, Integer start, Integer max) {
      return client.getBlockOperations(block, start, max);
   }

   public List<Operation> getAccountOperations(Integer account, Integer depth, Integer start, Integer max) {
      return client.getAccountOperations(account, depth, start, max);
   }

   public List<Operation> getAccountOperations(Integer account, Integer startblock, Integer depth, Integer start, Integer max) {
      return client.getAccountOperations(account, startblock, depth, start, max);
   }

   public List<Operation> getPendings() {
      return client.getPendings();
   }

   public Integer getPendingsCount() {
      return client.getPendingsCount();
   }

   public Operation findOperation(String ophash) {
      return client.findOperation(ophash);
   }

   public Operation findNOperation(Integer account, Integer nOperation) {
      return client.findNOperation(account, nOperation);
   }

   public List<Operation> findNOperations(Integer account, Integer nOperationMin, Integer nOperationMax, Integer startBlock) {
      return client.findNOperations(account, nOperationMin, nOperationMax, startBlock);
   }

   public List<Account> findAccounts(String name, Integer type, Integer start, Integer max) {
      return client.findAccounts(name, type, start, max);
   }

   public List<Account> findAccounts(String name, Boolean exact, Integer type, Boolean listed, Double minBalance, Double maxBalance, Integer start, Integer max) {
      if (isCaching) {

      }
      return client.findAccounts(name, exact, type, listed, minBalance, maxBalance, start, max);
   }

   public Operation sendTo(Integer sender, Integer target, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      return client.sendTo(sender, target, amount, fee, payload, payloadMethod, pwd);
   }

   public Operation changeKey(Integer account, Integer accountSigner, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      return client.changeKey(account, accountSigner, newEncPubKey, newB58PubKey, fee, payload, payloadMethod, pwd);
   }

   public List<Operation> changeKeys(String accounts, String newEncPubKey, String newB58PubKey, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      return client.changeKeys(accounts, newEncPubKey, newB58PubKey, fee, payload, payloadMethod, pwd);
   }

   public Operation listAccountForSale(Integer accountTarget, Integer accountSigner, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Integer lockedUntilBlock, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      return client.listAccountForSale(accountTarget, accountSigner, price, sellerAccount, newB58PubKey, newEncPubKey, lockedUntilBlock, fee, payload, payloadMethod, pwd);
   }

   public Operation delistAccountForSale(Integer accountTarget, Integer accountSigner, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {
      return client.delistAccountForSale(accountTarget, accountSigner, fee, payload, payloadMethod, pwd);
   }

   public Operation buyAccount(Integer buyerAccount, Integer accountToPurchase, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {

      return client.buyAccount(buyerAccount, accountToPurchase, price, sellerAccount, newB58PubKey, newEncPubKey, amount, fee, payload, payloadMethod, pwd);
   }

   public Operation changeAccountInfo(Integer accountTarget, Integer accountSigner, String newEncPubKey, String newB58PubKey, String newName, Short newType, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd) {

      return client.changeAccountInfo(accountTarget, accountSigner, newEncPubKey, newB58PubKey, newName, newType, fee, payload, payloadMethod, pwd);
   }

   public RawOperation signSendTo(Integer senderAccount, Integer targetAccount, String senderEncPubKey, String senderB58PubKey, String targetEncPubKey, String targetB58PubKey, Integer lastNOperation, Double amount, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String rawoperations) {

      return client.signSendTo(senderAccount, targetAccount, senderEncPubKey, senderB58PubKey, targetEncPubKey, targetB58PubKey, lastNOperation, amount, fee, payload, payloadMethod, pwd, rawoperations);
   }

   public RawOperation signChangeKey(Integer account, Integer accountSigner, String oldEncPubKey, String oldB58PubKey, String newEncPubKey, String newB58PubKey, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String rawOperations) {

      return client.signChangeKey(account, accountSigner, oldEncPubKey, oldB58PubKey, newEncPubKey, newB58PubKey, lastNOperation, fee, payload, payloadMethod, pwd, rawOperations);
   }

   public RawOperation signListAccountForSale(Integer accountTarget, Integer accountSigner, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Integer lockedUntilBlock, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String signerB58PubKey, String signerEncPubKey, String rawOperations) {

      return client.signListAccountForSale(accountTarget, accountSigner, price, sellerAccount, newB58PubKey, newEncPubKey, lockedUntilBlock, lastNOperation, fee, payload, payloadMethod, pwd, signerB58PubKey, signerEncPubKey, rawOperations);
   }

   public RawOperation signDelistAccountForSale(Integer accountTarget, Integer accountSigner, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String signerB58PubKey, String signerEncPubKey, String rawOperations) {

      return client.signDelistAccountForSale(accountTarget, accountSigner, lastNOperation, fee, payload, payloadMethod, pwd, signerB58PubKey, signerEncPubKey, rawOperations);
   }

   public RawOperation signBuyAccount(Integer buyerAccount, Integer accountToPurchase, Double price, Integer sellerAccount, String newB58PubKey, String newEncPubKey, Double amount, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd,
         String signerB58PubKey, String signerEncPubKey, String rawOperations) {

      return client.signBuyAccount(buyerAccount, accountToPurchase, price, sellerAccount, newB58PubKey, newEncPubKey, amount, lastNOperation, fee, payload, payloadMethod, pwd, signerB58PubKey, signerEncPubKey, rawOperations);
   }

   public Operation signChangeAccountInfo(Integer accountTarget, Integer accountSigner, String newEncPubkey, String newB58PubKey, String newName, Short newType, Integer lastNOperation, Double fee, byte[] payload, PayLoadEncryptionMethod payloadMethod, String pwd, String signerB58PubKey,
         String signerEncPubKey, String rawOperations) {

      return client.signChangeAccountInfo(accountTarget, accountSigner, newEncPubkey, newB58PubKey, newName, newType, lastNOperation, fee, payload, payloadMethod, pwd, signerB58PubKey, signerEncPubKey, rawOperations);
   }

   public List<Operation> operationsInfo(String rawOperations) {
      return client.operationsInfo(rawOperations);
   }

   public List<Operation> executeOperations(String rawOperations) {
      return client.executeOperations(rawOperations);
   }

   public NodeStatus getNodeStatus() {
      return client.getNodeStatus();
   }

   public String encodePubKey(KeyType ecNid, String x, String y) {
      return client.encodePubKey(ecNid, x, y);
   }

   public PublicKey decodePubKey(String encPubKey, String b58PubKey) {
      return client.decodePubKey(encPubKey, b58PubKey);
   }

   public String payloadEncrypt(String payload, String payloadMethod, String pwd, String encPubKey, String b58PubKey) {
      return client.payloadEncrypt(payload, payloadMethod, pwd, encPubKey, b58PubKey);
   }

   public DecryptedPayload payloadDecrypt(String payload, String[] pwds) {
      return client.payloadDecrypt(payload, pwds);
   }

   public List<Connection> getConnections() {
      return client.getConnections();
   }

   public PublicKey addNewKey(KeyType ecNid, String name) {
      return client.addNewKey(ecNid, name);
   }

   public Boolean lock() {
      return client.lock();
   }

   public Boolean unlock(String pwd) {
      return client.unlock(pwd);
   }

   public Boolean setWalletPassword(String pwd) {
      return client.setWalletPassword(pwd);
   }

   public Boolean stopNode() {
      return client.stopNode();
   }

   public Boolean startNode() {
      return client.startNode();
   }

   public DecodeOpHashResult decodeOpHash(String ophash) {
      return client.decodeOpHash(ophash);
   }

   public SignResult signMessage(String digest, String encPubKey, String b58PubKey) {
      return client.signMessage(digest, encPubKey, b58PubKey);
   }

   public SignResult verifySign(String digest, String encPubKey, String signature) {
      return client.verifySign(digest, encPubKey, signature);
   }

   public Integer calculateChecksum(Integer account) {
      return client.calculateChecksum(account);
   }

   public MultiOperation multiOperationAddOperation(String rawoperations, Boolean autoNOperation, List<OpSender> senders, List<OpReceiver> receivers, List<OpChanger> changers) {
      return client.multiOperationAddOperation(rawoperations, autoNOperation, senders, receivers, changers);
   }

   public MultiOperation multiOperationSignOffline(String rawoperations, List<AccountKey> signers) {
      return client.multiOperationSignOffline(rawoperations, signers);
   }

   public MultiOperation multiOperationSignOnline(String rawoperations) {
      return client.multiOperationSignOnline(rawoperations);
   }

   public MultiOperation multiOperationDeleteOperation(String rawoperations, Integer index) {
      return client.multiOperationDeleteOperation(rawoperations, index);
   }

   public void consumeEvent(BusEvent e) {
      e.checkSanity(pc.getUCtx(), IEventsCore.PID_3_MEMORY, IEventsCore.EID_MEMORY_0_ANY);

      cacheClear();
      accounts = new ArrayList<>();

      e.setActed();
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PascalAccountCache");
      dc.appendVarWithSpace("isCaching", isCaching);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PascalAccountCache");
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }
   //#enddebug

}
