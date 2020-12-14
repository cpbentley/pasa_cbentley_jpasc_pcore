/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.java.key;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKeyJava;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChain;
import pasa.cbentley.jpasc.pcore.utils.PublicKeyJavaCache;

/**
 * Task that iterates over all accounts and create a list of Keys seen.
 * 
 * The number of accounts owned by a key is not known until at thend
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskPublicKeyJavaChainAll extends ListTaskPublicKeyJavaAbstract implements IListListener<Account> {

   private PublicKeyJavaCache           cache;

   private LinkedHashSet<PublicKeyJava> keys = new LinkedHashSet<PublicKeyJava>();

   private PagerAbstract<Account> pagerAccountSub;

   private ListTaskAccountChain taskAccount;

   public ListTaskPublicKeyJavaChainAll(PCoreCtx pc, IListListener<PublicKeyJava> listener) {
      super(pc, listener);
      cache = new PublicKeyJavaCache(pc);
   }
   
   /**
    * Cache that will hold the keys
    * @param pc
    * @param listener
    * @param cache
    */
   public ListTaskPublicKeyJavaChainAll(PCoreCtx pc, IListListener<PublicKeyJava> listener, PublicKeyJavaCache cache) {
      super(pc, listener);
      if(cache == null) {
         throw new NullPointerException();
      }
      this.cache = cache;
   }
   /**
    * We want to return new found keys and keys that are updates with new accounts number.
    * listener will have to check the flag on 
    * {@link PublicKeyJava#isNew()} and it uses the cache as model for populating
    */
   protected List<PublicKeyJava> findItems(IPascalCoinClient client, Integer start, Integer pageSize) {
      if(keys.size() < pageSize) {
         //debug the thread
         //#debug
         toDLog().pFlow(pc.getC5().toStringThreadCurrent(), null, ListTaskPublicKeyJavaChainAll.class, "findItems", LVL_05_FINE, true);
         //if not enough keys.. get some
         taskAccount.runAbstract();
      }
      
      int size = Math.min(pageSize, keys.size());
      List<PublicKeyJava> listReturn = new ArrayList<>(size);
      Iterator<PublicKeyJava> it = keys.iterator();
      int count = size;
      while (count >= 0 && it.hasNext()) {
         PublicKeyJava key = it.next();
         listReturn.add(key);
         it.remove();
         count -= 1;
      }
      return listReturn;
   }

   public PublicKeyJavaCache getCachePublicKeyJava() {
      return cache;
   }

   protected PagerAbstract<PublicKeyJava> createPagerDefault() {
      PagerPublicKeyJava pager = new PagerPublicKeyJava(pc);
      pager.setPageSize(pc.getDefaultPageSizeRootPublicKey());
      pager.build();
      return pager;
   }

   public void newDataAvailable(List<Account> list) {
      //#debug
      toDLog().pData(list.size() + " new accounts ", cache, ListTaskPublicKeyJavaChainAll.class, "newDataAvailable", LVL_05_FINE, true);
      for (Account ac : list) {
         
         //#debug
         //toDLog().pData(pc.toPD().d1(ac), null, ListTaskPublicKeyJavaChainAll.class, "newDataAvailable", LVL_05_FINE, true);
         //we want to know
         String encKey = ac.getEncPubkey();
         PublicKeyJava pk = cache.updateKey(encKey, ac);
         keys.add(pk);
      }
      
   }
   
   /**
    * Defines the pages of accounts to look over for the keys
    * @param pagerAccount
    */
   public void setPagerAccount(PagerAbstract<Account> pagerAccount) {
      this.pagerAccountSub = pagerAccount;
   }

   public void runAbstract() {
      taskAccount = new ListTaskAccountChain(pc, this);
      if(pagerAccountSub == null) {
         //this pager will time for the user. 
         Integer numAccounts = pc.getPClient().getBlockCount() * 5;
         PagerAccount pagerAccount = new PagerAccount(pc);
         //this class will operate the pager "manually"
         pagerAccount.setLookUpRangeStart(0);
         pagerAccount.setLookUpRangeEnd(numAccounts);
         pagerAccount.setManualExactPageSize(true);
         pagerAccount.setTimingEnabled(true);
         pagerAccount.setPageTimingMin(250);
         pagerAccount.setPageSize(1);
         pagerAccount.build();
         pagerAccountSub = pagerAccount;
      }
      taskAccount.setPager(pagerAccountSub);
    
      //now run the default runner
      super.runAbstract();
      //how to stop account task when enough 
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskPublicKeyJavaChainAll");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskPublicKeyJavaChainAll");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
