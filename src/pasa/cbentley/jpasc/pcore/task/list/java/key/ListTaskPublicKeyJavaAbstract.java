/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.java.key;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.thread.AbstractBRunnable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKey;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.ListTask;
import pasa.cbentley.jpasc.pcore.task.ListTaskPage;
import pasa.cbentley.jpasc.pcore.task.PCoreTask;

/**
 * 
 * Stub for listing {@link PublicKeyJava} as a {@link ListTaskPage} <- {@link ListTask} <- {@link PCoreTask} <- {@link AbstractBRunnable}
 * 
 * <li> Provides the flag for computing accounts {@link ListTaskPublicKeyJavaAbstract#setComputeNumAccounts(boolean)}
 * 
 * <br>
 * <br>
 * 
 * Implementations can focus on either getting keys on chain, keys in wallet with various combination of parameters.
 * 
 * @see IFilterPublicKeyJava
 * 
 * @author Charles Bentley
 *
 */
public abstract class ListTaskPublicKeyJavaAbstract extends ListTaskPage<PublicKeyJava> {

   private boolean              isComputeNumAccounts;

   private IFilterPublicKeyJava publicKeyFilterJava;

   public ListTaskPublicKeyJavaAbstract(PCoreCtx pc, IListListener<PublicKeyJava> listener) {
      super(pc, listener);
   }

   protected List<PublicKeyJava> getFiltered(List<PublicKeyJava> list) {
      if (publicKeyFilterJava != null) {
         List<PublicKeyJava> filteredList = new ArrayList<PublicKeyJava>(list.size());
         for (PublicKeyJava acc : list) {
            if (publicKeyFilterJava.filterPublicKey(acc)) {
               filteredList.add(acc);
            }
         }
         return filteredList;
      }
      return list;
   }
   
   public IFilterPublicKeyJava getPublicKeyFilterJava() {
      return publicKeyFilterJava;
   }

   public boolean isComputeNumAccounts() {
      return isComputeNumAccounts;
   }

   public void setComputeNumAccounts(boolean b) {
      isComputeNumAccounts = b;
   }


   public void setPublicKeyFilterJava(IFilterPublicKeyJava publicKeyFilterJava) {
      this.publicKeyFilterJava = publicKeyFilterJava;
   }


}
