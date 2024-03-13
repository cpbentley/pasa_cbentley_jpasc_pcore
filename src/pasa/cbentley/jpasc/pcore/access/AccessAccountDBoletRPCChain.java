/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.access;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.ITechPascRPC;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccount;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccountT;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessAccountDBolet;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderAccount;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.ListTaskAccountAbstract;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainAge;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainBalanceMinMax;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainFilter;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainFindAccounts4;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainName;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainNameNull;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainPriceMinMax;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainType;

/**
 * Access Account data via RPC on the current {@link RPCConnection} client.
 * 
 * @author Charles Bentley
 *
 */
public class AccessAccountDBoletRPCChain implements IAccessAccountDBolet {

   protected final PCoreCtx pc;

   public AccessAccountDBoletRPCChain(PCoreCtx pc) {
      this.pc = pc;
   }

   public Account getAccount(Integer account) {
      return getClient().getAccount(account);
   }

   public List<Account> getAccountsAll(PagerAbstract<Account> page) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      ListTaskAccountChainFindAccounts4 task = new ListTaskAccountChainFindAccounts4(pc, list);
      task.setPager(page);
      task.runAbstract();
      return list.getAccounts();
   }

   public List<Account> getAccountsKey(PagerAbstract<Account> page, PublicKeyJava pk) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      ListTaskAccountChainKey task = new ListTaskAccountChainKey(pc, list, pk);
      task.setPager(page);
      task.runAbstract();
      return list.getAccounts();
   }

   public List<Account> getAccountsType(PagerAbstract<Account> page, Integer type) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      ListTaskAccountChainType task = new ListTaskAccountChainType(pc, list, type);
      task.setPager(page);
      task.runAbstract();
      return list.getAccounts();
   }

   public List<Account> getAccountsRangeAge(PagerAbstract<Account> page, Integer minAge, Integer maxAge) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      ListTaskAccountChainAge task = new ListTaskAccountChainAge(pc, list, minAge, maxAge);
      task.setPager(page);
      task.runAbstract();
      return list.getAccounts();
   }

   public List<Account> getAccountsRangeBalance(PagerAbstract<Account> page, Double minBalance, Double maxBalance) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      ListTaskAccountChainBalanceMinMax task = new ListTaskAccountChainBalanceMinMax(pc, list, minBalance, maxBalance);
      task.setPager(page);
      task.runAbstract();
      return list.getAccounts();
   }

   public List<Account> getAccountsRangePrice(PagerAbstract<Account> page, Double minPrice, Double maxPrice) {
      return getAccounts(page, new ListTaskAccountChainPriceMinMax(pc, null, minPrice, maxPrice));
   }

   private List<Account> getAccounts(PagerAbstract<Account> page, ListTaskAccountAbstract task) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      task.setListener(list);
      task.setPager(page);
      task.runAbstract();
      return list.getAccounts();
   }

   /**
    * 
    */
   public List<Account> getAccountsWithName(PagerAbstract<Account> page, String str) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      if (str == null || str.equals("")) {
         ListTaskAccountChainNameNull taskNull = new ListTaskAccountChainNameNull(pc, list);
         taskNull.setPager(page);
         taskNull.runAbstract();
      } else {
         ListTaskAccountChainName taskName = new ListTaskAccountChainName(pc, list, str, ITechPascRPC.NAMESEARCHTYPE_CONTAINS);
         taskName.setPager(page);
         taskName.runAbstract();
      }
      return list.getAccounts();
   }

   public List<Account> getAccountsFilter(PagerAbstract<Account> page, IFilterAccountT<Account> filter) {
      return getAccounts(page, new ListTaskAccountChainFilter(pc, null, (IFilterAccount) filter));
   }

   public Account getAccountWithName(String str) {
      Account account = null;
      List<Account> accounts = getClient().findAccounts(str, null, 0, 1);
      if (accounts != null && accounts.size() != 0) {
         account = accounts.get(0);
      }
      return account;
   }

   private IPascalCoinClient getClient() {
      return pc.getPClient();
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "AccessAccountRPC");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AccessAccountRPC");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUC();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
