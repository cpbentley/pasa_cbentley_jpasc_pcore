/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.tools;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.AccountJava;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessAccountDBolet;
import pasa.cbentley.jpasc.pcore.interfaces.IAccountValidator;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;

/**
 * 
 * @author Charles Bentley
 *
 */
public class MyValidator implements IAccountValidator {

   private PCoreCtx pc;

   public MyValidator(PCoreCtx pc) {
      this.pc = pc;
   }
   
   
   public boolean isValidNumber(Integer account) {
      Account ac = getAccounts().getAccount(account);
      return ac != null;
   }

   public boolean isValidName(String name) {
      Account ac = getAccounts().getAccountWithName(name);
      return ac != null;
   }

   /**
    * Blocking call
    */
   public String[] getNamesFrom(String prefix) {
      getAccounts().getAccountWithName(prefix);
      return null;
   }

   public Integer[] getNumberFrom(String prefix) {
      // TODO Auto-generated method stub
      return null;
   }


   public AccountJava getAccount(String name) {
      Account ac = getAccounts().getAccountWithName(name);
      
      return pc.getAccountJavaFrom(ac);
   }


   private IAccessAccountDBolet getAccounts() {
      return pc.getAccessPascalPrivate().getAccessAccountDBolet();
   }


   public AccountJava getAccount(Integer account) {
      Account ac = getAccounts().getAccount(account);
      return pc.getAccountJavaFrom(ac);
   }

}
