/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter.account;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class FilterAccountEncPubKey extends FilterAccountAbstract {

   //not null
   private String encodedPublicKey;

   public FilterAccountEncPubKey(PCoreCtx pc, String encodedPublicKey) {
      super(pc);
      if (encodedPublicKey == null) {
         throw new NullPointerException();
      }
      this.encodedPublicKey = encodedPublicKey;
   }

   public boolean filterAccount(Account account) {
      String accountEncodedPublicKey = account.getEncPubkey();
      if (accountEncodedPublicKey.equals(this.encodedPublicKey)) {
         return true;
      } else {
         return false;
      }
   }
}
