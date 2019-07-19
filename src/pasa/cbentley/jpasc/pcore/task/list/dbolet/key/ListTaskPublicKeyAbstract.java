/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.key;

import java.util.ArrayList;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.filter.IFilterPublicKey;
import pasa.cbentley.jpasc.pcore.listlisteners.IListListener;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKey;
import pasa.cbentley.jpasc.pcore.task.ListTaskPage;

/**
 * 
 * @author Charles Bentley
 *
 */
public abstract class ListTaskPublicKeyAbstract extends ListTaskPage<PublicKey> {

   private IFilterPublicKey publicKeyFilter;

   public ListTaskPublicKeyAbstract(PCoreCtx pc, IListListener<PublicKey> listener) {
      super(pc, listener);
   }

   protected List<PublicKey> getFiltered(List<PublicKey> list) {
      if (publicKeyFilter != null) {
         List<PublicKey> filteredList = new ArrayList<PublicKey>(list.size());
         for (PublicKey acc : list) {
            if (publicKeyFilter.filterPublicKey(acc)) {
               filteredList.add(acc);
            }
         }
         return filteredList;
      }
      return list;
   }

   /**
    * Default pager returns all keys
    */
   protected PagerAbstract<PublicKey> createPagerDefault() {
      PagerPublicKey pager = new PagerPublicKey(pc);
      pager.setPageSize(pc.getDefaultPageSizeRootPublicKey());
      pager.build();
      return pager;
   }

   public IFilterPublicKey getPublicKeyFilter() {
      return publicKeyFilter;
   }

   public void setPublicKeyFilter(IFilterPublicKey publicKeyFilter) {
      this.publicKeyFilter = publicKeyFilter;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "ListTaskPublicKeyAbstract");
      toStringPrivate(dc);
      super.toString(dc.sup());

      dc.nlLvl(publicKeyFilter, "IFilterPublicKey");
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "ListTaskPublicKeyAbstract");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
