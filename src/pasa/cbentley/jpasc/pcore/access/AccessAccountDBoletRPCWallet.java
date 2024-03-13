/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.access;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccountT;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessAccountDBolet;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderAccount;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.ListTaskAccountWalletPriceMinMax;

/**
 * Access Account data via RPC on the current {@link RPCConnection} client.
 * 
 * @author Charles Bentley
 *
 */
public class AccessAccountDBoletRPCWallet implements IAccessAccountDBolet {

   protected final PCoreCtx pc;

   public AccessAccountDBoletRPCWallet(PCoreCtx pc) {
      this.pc = pc;
   }

   /**
    * null if not in wallet
    */
   public Account getAccount(Integer account) {
      throw new RuntimeException("not implemented yet");
   }

   public List<Account> getAccounts(PagerAbstract<Account> page, String name, Boolean exact, Integer type, Boolean listed, Double minBalance, Double maxBalance) {
      throw new RuntimeException("not implemented yet");

   }

   public List<Account> getAccountsAll(PagerAbstract<Account> page) {
      throw new RuntimeException("not implemented yet");
   }

   public List<Account> getAccountsFilter(PagerAbstract<Account> page, IFilterAccountT<Account> filter) {
      // TODO Auto-generated method stub
      return null;
   }

   public List<Account> getAccountsKey(PagerAbstract<Account> page, PublicKeyJava pk) {
      throw new RuntimeException("not implemented yet");

   }

   public List<Account> getAccountsRangeAge(PagerAbstract<Account> page, Integer minAge, Integer maxAge) {
      throw new RuntimeException("not implemented yet");
   }

   public List<Account> getAccountsRangeBalance(PagerAbstract<Account> page, Double minBalance, Double maxBalance) {
      throw new RuntimeException("not implemented yet");
   }

   public List<Account> getAccountsRangePrice(PagerAbstract<Account> page, Double minPrice, Double maxPrice) {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      ListTaskAccountWalletPriceMinMax task = new ListTaskAccountWalletPriceMinMax(pc, list, minPrice, maxPrice);
      task.setPager(page);
      task.runAbstract();
      return list.getAccounts();
   }

   public List<Account> getAccountsType(PagerAbstract<Account> page, Integer type) {
      // TODO Auto-generated method stub
      return null;
   }

   public List<Account> getAccountsWithName(PagerAbstract<Account> page, String str) {
      throw new RuntimeException("not implemented yet");
   }

   public Account getAccountWithName(String str) {
      throw new RuntimeException("not implemented yet");
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
