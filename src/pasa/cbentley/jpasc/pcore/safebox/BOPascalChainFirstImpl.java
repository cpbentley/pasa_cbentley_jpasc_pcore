/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.safebox;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;
import pasa.cbentley.jpasc.pcore.interfaces.IBOPascalChain;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * 
 * @author Charles Bentley
 *
 */
public class BOPascalChainFirstImpl implements IBOPascalChain, IListListener<Account> {

   private PCoreCtx pc;

   public BOPascalChainFirstImpl(PCoreCtx pc) {
      this.pc = pc;
   }
   
   
   public List<AccountBO> getAccount(Integer start, Integer max) {
      // TODO Auto-generated method stub
      return null;
   }

   public AccountBO getAccount(Integer account) {
      // TODO Auto-generated method stub
      return null;
   }


   public void syncWith(IPascalCoinClient pClient) {
      
   }


   /**
    * New accounts
    */
   public void newDataAvailable(List<Account> list) {
      // TODO Auto-generated method stub
      
   }


   public String toString1Line() {
      // TODO Auto-generated method stub
      return null;
   }


   public void toString(Dctx dc) {
      // TODO Auto-generated method stub
      
   }


   public void toString1Line(Dctx dc) {
      // TODO Auto-generated method stub
      
   }


   public UCtx toStringGetUCtx() {
      // TODO Auto-generated method stub
      return null;
   }

}
