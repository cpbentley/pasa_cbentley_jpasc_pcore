/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.java.key;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.ListTaskPublicKeyWalletCanUse;

/**
 * Task
 * @author Charles Bentley
 *
 */
public class ListTaskPublicKeyJavaWalletCanUse extends ListTaskPublicKeyJavaWallet {

   public ListTaskPublicKeyJavaWalletCanUse(PCoreCtx pc, IListListener<PublicKeyJava> listener) {
      super(pc, listener);
      //paging occurs here
      taskPublicKey = new ListTaskPublicKeyWalletCanUse(pc, this);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, ListTaskPublicKeyJavaWalletCanUse.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ListTaskPublicKeyJavaWalletCanUse.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
