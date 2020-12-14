/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.java.key;

import java.util.List;
import java.util.Set;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.task.ListTask;
import pasa.cbentley.jpasc.pcore.task.ListTaskPage;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.ListTaskPublicKeyAbstract;
import pasa.cbentley.jpasc.pcore.tools.KeyNameProvider;
import pasa.cbentley.jpasc.pcore.tools.PkNamesStore;
import pasa.cbentley.jpasc.pcore.utils.PublicKeyJavaCache;

/**
 * Task for listing keys from {@link PkNamesStore}
 * 
 * Extends {@link ListTaskPage} but does not use paging
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskPublicKeyJavaLocal extends ListTaskPage<PublicKeyJava> {

   /**
    * Task that will be used to list keys from wallet
    */
   protected ListTaskPublicKeyAbstract taskPublicKey;

   public ListTaskPublicKeyJavaLocal(PCoreCtx pc, IListListener<PublicKeyJava> listener) {
      super(pc, listener);
   }

   protected PagerAbstract<PublicKeyJava> createPagerDefault() {
      return null;
   }

   protected List<PublicKeyJava> findItems(IPascalCoinClient client, Integer start, Integer pageSize) {
      return null;
   }

   protected List<PublicKeyJava> getFiltered(List<PublicKeyJava> list) {
      return null;
   }

   /**
    * By overrding runAbstract we override the default paging structure
    * 
    * Read keys from the PK stores and public and private
    */
   public void runAbstract() {
      KeyNameProvider prov = pc.getKeyNameProvider();

      //all keys we a custom name
      Set<String> encodedKeys = prov.getEncodedKeysCustom();
      PublicKeyJavaCache publicKeyJavaCache = pc.getPublicKeyJavaCache();
      for (String key : encodedKeys) {
         PublicKeyJava keyJava = publicKeyJavaCache.getKey(key);
         if (keyJava == null) {
            keyJava = new PublicKeyJava(pc);
            keyJava.setEncPubKey(key);
         }
         publish(keyJava);
      }
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskPublicKeyJavaLocal");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskPublicKeyJavaLocal");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
