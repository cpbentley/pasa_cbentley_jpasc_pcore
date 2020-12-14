/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.java.key;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKeyJava;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.ListTaskPublicKeyAbstract;

/**
 * Task
 * @author Charles Bentley
 *
 */
public abstract class ListTaskPublicKeyJavaWallet extends ListTaskPublicKeyJavaAbstract implements IListListener<PublicKey> {

   private List<PublicKeyJava>         keysJava = new ArrayList<PublicKeyJava>();

   /**
    * Task that will be used to list keys from wallet
    */
   protected ListTaskPublicKeyAbstract taskPublicKey;

   public ListTaskPublicKeyJavaWallet(PCoreCtx pc, IListListener<PublicKeyJava> listener) {
      super(pc, listener);
   }

   protected List<PublicKeyJava> findItems(IPascalCoinClient client, Integer start, Integer pageSize) {
      int toIndex = Math.min(pageSize, keysJava.size());
      List<PublicKeyJava> listReturn = new ArrayList<>(keysJava.subList(0, toIndex));
      if (listReturn.size() == keysJava.size()) {
         keysJava.clear();
      } else {
         keysJava = new ArrayList<>(keysJava.subList(toIndex, keysJava.size()));
      }
      return listReturn;
   }

   protected PagerAbstract<PublicKeyJava> createPagerDefault() {
      PagerPublicKeyJava pager = new PagerPublicKeyJava(pc);
      pager.setPageSize(pc.getDefaultPageSizeRootPublicKey());
      pager.build();
      return pager;
   }

   public void newDataAvailable(List<PublicKey> list) {
      //publickey were already filtered by the task
      for (PublicKey pk : list) {
         PublicKeyJava pka = pc.getDomainMapper().mapPublicKey(pk);
         if (isComputeNumAccounts()) {
            int numAccounts = pc.getPClient().getWalletAccountsCount(pk.getEncPubKey(), null);
            pka.setNumAccounts(numAccounts);
         }
         keysJava.add(pka);
      }
   }

   public void runAbstract(PagerAbstract<PublicKeyJava> pager) {
      taskPublicKey.runAbstract(); //get the
      super.runAbstract(pager);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskPublicKeyJavaWallet");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskPublicKeyJavaWallet");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
