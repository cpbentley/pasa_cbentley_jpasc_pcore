/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.safebox;

import java.util.List;

import pasa.cbentley.byteobjects.src4.core.ByteObjectManaged;
import pasa.cbentley.core.src4.io.BAByteOS;
import pasa.cbentley.core.src4.io.BADataOS;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.task.PCoreTask;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChain;

/**
 * Initial synchronization of the Pascal safebox data from {@link PascalCoinClient} via
 * RPC calls.
 * <br>
 * Once a chain is synched, the local copy is updated by operations from block
 * @author Charles Bentley
 *
 */
public class SynchPascalSafeboxTask extends PCoreTask implements IListListener<Account> {

   private BAByteOS               baos;

   private BAByteOS               baosIndex;

   private BADataOS               byteOut;

   private BADataOS               byteOutbaosIndex;

   private BOPascalChainFirstImpl chain;

   private Mapper                 mapper;

   private IPascalCoinClient       pClient;

   public SynchPascalSafeboxTask(PCoreCtx pc, BOPascalChainFirstImpl chain, IPascalCoinClient pClient) {
      super(pc);
      if (chain == null) {
         throw new NullPointerException();
      }
      this.chain = chain;
      if (pClient == null) {
         throw new NullPointerException();
      }
      this.pClient = pClient;
   }

   public BOPascalChainFirstImpl getChain() {
      return chain;
   }

   public void mapToBO(Account ac) {

      int offsetForIndex = baos.getByteWrittenCount();
      byteOutbaosIndex.writeInt(offsetForIndex);

      mapper.mapToAccountBO(byteOut, ac);
   }

   public void newDataAvailable(List<Account> list) {
      for (Account account : list) {
         mapToBO(account);
      }
   }

   /**
    * Implementation Note:
    * Strategy 
    * Use {@link PascalCoinClient}
    * 
    * Use a single big byte array. write to it
    */
   public void runAbstract() {

      ByteObjectManaged bom = new ByteObjectManaged(pc.getBOC());
      int initSize = pClient.getBlockCount() * 100;
      baos = new BAByteOS(pc.getUCtx(), initSize);
      byteOut = new BADataOS(pc.getUCtx(), baos);

      baosIndex = new BAByteOS(pc.getUCtx(), initSize);
      byteOutbaosIndex = new BADataOS(pc.getUCtx(), baos);

      //strategy: use Pascal
      ListTaskAccountChain accountTask = new ListTaskAccountChain(pc, this);
      accountTask.runAsSubTaskOf(this);

      //at the end, set the data to the chain
   }

}
