/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.filter;

import java.util.ArrayList;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.publickeyjava.FilterKeyJavaPositive;

public class SetFilterKey implements IFilterPublicKeyJava {

   protected final PCoreCtx                pc;

   private FilterKeyJavaPositive           filterKeysNegative;

   private FilterKeyJavaPositive           filterKeysPositive;

   private ArrayList<IFilterPublicKeyJava> filters;

   /**
    * An empty set will not filter any key
    * @param pc
    */
   public SetFilterKey(PCoreCtx pc) {
      this.pc = pc;

   }

   public void addFilter(IFilterPublicKeyJava filter) {
      if (filter == null) {
         return;
      }
      if (filters == null) {
         filters = new ArrayList<IFilterPublicKeyJava>(1);
      }
      filters.add(filter);
   }

   /**
    * Add {@link PublicKeyJava} to the list of keys  No effect if null
    * @param pl
    */
   public void addKey(PublicKeyJava pl) {
      if (pl == null) {
         return;
      }
      if (filterKeysPositive == null) {
         filterKeysPositive = new FilterKeyJavaPositive(pc);
      }
      filterKeysPositive.addKey(pl);
   }

   public void addKeyNegative(PublicKeyJava pl) {
      if (pl == null) {
         return;
      }
      if (filterKeysNegative == null) {
         filterKeysNegative = new FilterKeyJavaPositive(pc);
      }
      filterKeysNegative.addKey(pl);
   }

   /**
    * true if key is accepted 
    */
   public boolean filterPublicKey(PublicKeyJava publicKey) {
      if (filterKeysPositive != null) {
         //only accept keys in this list
         boolean isInList = filterKeysPositive.filterPublicKey(publicKey);
         if (!isInList) {
            return false;
         }
      }
      if (filterKeysNegative != null) {
         //only accept keys in this list
         boolean isInList = filterKeysNegative.filterPublicKey(publicKey);
         if (isInList) {
            return false;
         }
      }
      if (filters != null) {
         for (IFilterPublicKeyJava filter : filters) {
            if (!filter.filterPublicKey(publicKey)) {
               return false;
            }
         }
      }
      //none of the filters invalidated the key. so accept it
      return true;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "SetFilterKey");
      toStringPrivate(dc);

      C5Ctx c5 = pc.getC5();
      dc.nlLvl("Excluded Keys", filterKeysNegative);
      dc.nlLvl("Included Keys", filterKeysPositive);
      dc.nl();
      c5.toStringListStringable(dc, filters, "Extra Filters");

   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "SetFilterKey");
      toStringPrivate(dc);

   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   //#enddebug

}
