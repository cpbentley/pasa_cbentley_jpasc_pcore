/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.services;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Provides services when operating on pasas.
 * 
 * <li> {@link PasaServices#isAccountGreenForNewFreeOperation(Integer)}
 * @author Charles Bentley
 *
 */
public class PasaServices {

   private PCoreCtx pc;

   public PasaServices(PCoreCtx pc) {
      this.pc = pc;
   }

   /**
    * Can the account make a free operation 
    * 
    * 
    * @param account
    * @return
    */
   public boolean isAccountGreenForNewFreeOperation(Integer account) {
      return true;
   }

   /**
    * Register the start account as being use
    * @param op
    */
   public void registerAccountInPendingOperations(Operation op) {

   }

   /**
    * Look up pending operations. This spawns a worker that build the list.
    * <br>
    * Provdes a nice service to know which accounts have currently pending operations.
    * <br>
    * <br>
    * 
    */
   public void buildPendingAccountList() {

      List<Operation> list = pc.getPClient().getPendings();

   }
}
