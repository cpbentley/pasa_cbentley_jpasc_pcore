/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.key;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.filter.publickey.FilterKeyCanUse;
import pasa.cbentley.jpasc.pcore.filter.publickey.FilterKeyCannotUse;
import pasa.cbentley.jpasc.pcore.filter.publickeyjava.FilterKeyJavaCannotUse;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;

/**
 * Task for listing PublicKey that can be operated on by the Wallet.
 * 
 * @author Charles Bentley
 *
 */
public class ListTaskPublicKeyWalletCannotUse extends ListTaskPublicKeyAbstract  {


   public ListTaskPublicKeyWalletCannotUse(PCoreCtx pc, IListListener<PublicKey> listener) {
      super(pc, listener);
      this.setPublicKeyFilter(new FilterKeyCannotUse(pc));
   }

   protected List<PublicKey> findItems(IPascalCoinClient client, Integer start, Integer max) {
      return client.getWalletPubKeys(start, max);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskPublicKeyWalletCannotUse");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskPublicKeyWalletCannotUse");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }
   //#enddebug
   



}
