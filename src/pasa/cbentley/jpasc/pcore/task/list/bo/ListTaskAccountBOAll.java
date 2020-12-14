/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.bo;

import java.util.List;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;
import pasa.cbentley.jpasc.pcore.interfaces.IBOPascalChain;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAccountBO;
import pasa.cbentley.jpasc.pcore.rpc.exception.RPCApiException;

/**
 * Task for listing {@link AccountBO}.
 * <br>
 * This task is optimized for reading the whole safebox.
 * @author Charles Bentley
 *
 */
public class ListTaskAccountBOAll extends ListTaskAccountBOAbstract {

   public ListTaskAccountBOAll(PCoreCtx pc, IListListener<AccountBO> listener) {
      super(pc, listener);
   }

   public void runAbstract() {
      IBOPascalChain client = pc.getBOPascalChain();
      PagerAccountBO pageAccount = new PagerAccountBO(pc);
      List<AccountBO> list = null;
      List<AccountBO> listFiltered = null;
   }

}
