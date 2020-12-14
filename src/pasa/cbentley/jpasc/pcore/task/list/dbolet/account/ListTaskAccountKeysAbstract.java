/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.account;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccount;
import pasa.cbentley.jpasc.pcore.filter.SetFilterAccount;
import pasa.cbentley.jpasc.pcore.filter.SetFilterKey;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.ListTask;
import pasa.cbentley.jpasc.pcore.task.ListTaskPage;

/**
 * Stub task for listing accounts, chain or wallet or anything.
 * 
 * <br>
 * <br>
 * Merely provides a template with filters sets
 * @author Charles Bentley
 *
 */
public abstract class ListTaskAccountKeysAbstract extends ListTaskAccountAbstract {

   protected List<PublicKey> keys;
   
   public ListTaskAccountKeysAbstract(PCoreCtx pc, IListListener<Account> listener, List<PublicKey> keys) {
      super(pc, listener);
      this.keys = keys;
   }



}
