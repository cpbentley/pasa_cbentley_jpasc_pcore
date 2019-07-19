/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.interfaces.IStrAcceptor;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Filter accounts based on name String.
 * If name is null, then accounts without a name will be filtered as true.
 * @author Charles Bentley
 *
 */
public class FilterAccountNameAcceptor extends FilterAccountAbstract {

   private IStrAcceptor acceptor;

   public FilterAccountNameAcceptor(PCoreCtx pc, IStrAcceptor acceptor) {
      super(pc);
      this.acceptor = acceptor;
   }

   public boolean filterAccount(Account account) {
      String nameAccount = account.getName();
      return acceptor.isStringAccepted(nameAccount);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "FilterAccountNameAcceptor");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.nlLvl(acceptor, "IStrAcceptor");
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "FilterAccountNameAcceptor");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug
}
