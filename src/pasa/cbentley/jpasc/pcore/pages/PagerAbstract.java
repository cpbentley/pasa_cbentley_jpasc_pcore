/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.task.ListTask;

/**
 * 
 * @author Charles Bentley
 *
 * @param <T> 
 */
public abstract class PagerAbstract<T> implements IStringable {
   protected int      countFiltered;

   /**
    * Counts the number of elements in the current exact page
    */
   private int        countPageItemsPublished;

   /**
    * Number of elements process
    */
   protected int      countProcessed;

   /**
    * counter of totally published on the current page
    */
   protected int      countPublished;

   private int        errorCounter  = 0;

   private boolean    isAscending   = true;   //by default true

   //#debug
   private boolean    isBuilt;

   private boolean    isContinuePaging;

   /**
    * Should the list returned for publishing should exceed the range size?
    * 
    */
   private boolean    isFitRange;

   /**
    * in manual exact page size mode, once page has been filled, you have to turn it explicitely and the page is
    * 
    * in non manual mode, the listtask will fill all the pages
    */
   private boolean    isManualExactPageSize;

   private boolean    isManualPageComplete;

   private boolean    isTimingEnabled;

   /**
    * 
    */
   protected List<T>  listLeftOver;

   /**
    * Current lookup starting position
    */
   protected Integer  lookUpPointer;

   /**
    * null if unknown which means the pager doesn't know when to stop.
    * The pageTurn will at one time give a null or empty list
    */
   protected Integer  lookUpRangeEnd;

   /**
    * null if not known. will be set to 0 by build
    */
   protected Integer  lookUpRangeStart;

   /**
    * At each step, its the number of items left to iterate over.
    * Null if totalNumber is unknown
    */
   private Integer    lookUpRemainder;

   protected int      pageSizeActive;

   /**
    * Page Size. Fill the page with maximum number of elements
    * 
    * The page size can be dynamic when timing is enabled
    */
   private Integer    pageSizeRoot;

   /**
    * Valid when timing is enable. Decreases page size so that it is below this value.
    */
   private int        pageTimingMax = 700;

   /**
    * Valid when timing is enable. increase page size so that it is above 100ms
    */
   private int        pageTimingMin = 100;

   protected PCoreCtx pc;

   private long       timing;

   private Integer    pageSizeMax;

   public PagerAbstract(PCoreCtx pc) {
      this.pc = pc;
   }

   public PagerAbstract(PCoreCtx pc, Integer start, Integer max) {
      this.pc = pc;
   }

   /**
    * Don't forget to call this method once the object has been setup
    */
   public void build() {
      //#debug
      isBuilt = true;
      if (lookUpRangeStart == null) {
      }
      if (lookUpPointer == null) {
         lookUpPointer = lookUpRangeStart;
         if (lookUpPointer == null) {
            lookUpPointer = 0;
         }

      }
      if (pageSizeRoot == null) {
         pageSizeRoot = pc.getDefaultPageSizeRoot();
      }

      setPageSizeActive(pageSizeRoot);
      if (isFitRange) {
         if (lookUpRangeStart == null) {
            throw new NullPointerException();
         }
         if (lookUpRangeEnd == null) {
            throw new NullPointerException();
         }
         //no need to have a pageSize bigger
         int rangeSize = lookUpRangeEnd - lookUpRangeStart; //at least one
         if (isRangeInclusive()) {
            rangeSize += 1;
         }
         if (pageSizeActive > rangeSize) {
            setPageSizeActive(rangeSize); //active page size could be zero here..
            //some api calls support this
         }
      }
      if (isDescending()) {
         //lookUpPointer starts at pageSize below end
         if (lookUpRangeEnd == null) {
            throw new NullPointerException();
         }
         lookUpPointer = lookUpRangeEnd - pageSizeActive + 1;
      } else {
         if (lookUpRangeStart == null) {
            //assume 0
            lookUpRangeStart = 0;
         }
         lookUpPointer = lookUpRangeStart;
      }
      if (isTimingEnabled) {
         timing = System.currentTimeMillis();
      }

      //#debug
      toDLog().pInit("Build Finished", this, PagerAbstract.class, "build", LVL_05_FINE, false);
   }

   /**
    * The number of elements filtered as positive in the book by the pager
    * 
    * @return
    */
   public int getCountFiltered() {
      return countFiltered;
   }

   /**
    * The number of list items processed by the pager. Equal or bigger than {@link PagerAbstract#getCountPublished()} 
    * @return
    */
   public int getCountProcessed() {
      return countProcessed;
   }

   /**
    * The number of elements published in the book by the pager
    * @return
    */
   public int getCountPublished() {
      return countPublished;
   }

   /**
    * Total items expected to be found.
    * -1 if unknown
    * @return
    */
   public int getCountTotalItems() {
      if (lookUpRangeEnd == null) {
         return -1;
      }
      if (lookUpRangeStart == null) {
         return lookUpRangeEnd;
      } else {
         return lookUpRangeEnd - lookUpRangeStart;
      }
   }

   /**
    * When AP
    * @return
    */
   public Integer getEnd() {
      return pageSizeRoot;
   }

   /**
    * true if we have more data to process for this pager
    * @return
    */
   private boolean getListIsContinue() {
      if (isManualExactPageSize) {
         //check if we have reach the page size requested at build time
         return !isManualPageComplete;
      }

      if (lookUpRangeEnd != null) {
         if (isAscending()) {
            lookUpRemainder = lookUpRangeEnd - lookUpPointer;
         } else {
            lookUpRemainder = lookUpPointer - lookUpRangeStart;
         }
         if (lookUpRemainder <= 0) {
            return false;
         } else {
            setPageSizeActive(Math.min(lookUpRemainder, pageSizeActive));
            return true;
         }
      } else {
         //since we don't know the total number... we will stop
         //once the list is empty or null
         return true;
      }
   }

   public boolean isLookUpRemaining() {
      if (lookUpRangeEnd != null) {
         if (isAscending()) {
            lookUpRemainder = lookUpRangeEnd - lookUpPointer;
         } else {
            lookUpRemainder = lookUpPointer - lookUpRangeStart;
         }
         return lookUpRemainder > 0;
      } else {
         return true;
      }
   }

   public List<T> getListLeftOver() {
      return listLeftOver;
   }

   /**
    * Return a list of 
    * Compute next parameters for fetching the next page of results.
    * 
    * Called by {@link ListTask} in a while loop. Should not be called elswhere. 
    * 
    * null list items are considered items and will increase the <code>processedItemsCount</code>.
    * 
    * If timing is enabled, the max value is adjusted so that the next turn occurs at around a given interval
    * 
    * When mode is manual, return true only until the page is filled or no more data.
    * When mode is automatic, return true only until the range is filled or no more data.
    * 
    * Advances the lookUpPointer for the next page
    * Also decides if paging is still needed for the pager.
    * @param listProcessed the current list of results.
    * @param listPublished listProcessed filtered
    * @return
    */
   public List<T> getListToPublish(List<T> listProcessed, List<T> listFiltered) {
      //#mdebug
      if (!isBuilt) {
         throw new RuntimeException("Object build() method was not called");
      }
      //#enddebug
      if (listProcessed == null || listProcessed.isEmpty()) {
         countPageItemsPublished = 0;
         isContinuePaging = false;
         return null;
      }

      countProcessed += listProcessed.size();
      countFiltered += listFiltered.size();

      //next time a page request is done
      lookUpPointer = getNextStart(listProcessed, listFiltered);

      List<T> listReturn = listFiltered;
      if (isManualExactPageSize && listFiltered != null) {
         //possible leftover

         //take it all and if 
         List<T> listItemsUnPublished = new ArrayList<>();
         if (listLeftOver != null) {
            listItemsUnPublished.addAll(listLeftOver);
         }
         listItemsUnPublished.addAll(listFiltered);

         if (countPageItemsPublished + listItemsUnPublished.size() < pageSizeActive) {
            listLeftOver = null;
            isManualPageComplete = false;
            countPageItemsPublished += listItemsUnPublished.size();
            listReturn = listItemsUnPublished;
         } else {
            isManualPageComplete = true;
            int itemSpotCountLeftInPage = pageSizeActive - countPageItemsPublished;
            int sizeItemsUnpublished = listItemsUnPublished.size();
            int index = Math.min(itemSpotCountLeftInPage, sizeItemsUnpublished);
            if (index < 0) {
               System.out.println(index);
            }
            listReturn = listItemsUnPublished.subList(0, index);
            listLeftOver = listItemsUnPublished.subList(index, listItemsUnPublished.size());
            //we need to reset this counter here
            countPageItemsPublished = 0;
         }
      }
      timingChecks();
      isContinuePaging = getListIsContinue();
      countPublished += listReturn.size();
      return listReturn;
   }

   /**
    * When value is unknown
    * @return
    */
   public Integer getMax() {
      return pageSizeActive;
   }

   /**
    * This is a very important method.
    * It must give the next start when using the start/max pattern for listing
    * <br>
    * Often times, the start is the last start incremented by the last number of fetched results.
    * 
    * Next start must avoid loops. Consecutive calls to {@link PagerAbstract#getNextStart(List, List)}
    * will continuously increae start value.
    * 
    * Therefore {@link PagerAbstract#getCountProcessed()} cannot be relied upon.
    * @param listProcessed all the elements before being filtered.
    * @param listFiltered the filtered elements that will be published
    * 
    * @return
    */
   protected abstract Integer getNextStart(List<T> listProcessed, List<T> listFiltered);

   public Integer getNextStartDefautAscending() {
      return getCountProcessed() + 1;
   }

   public Integer getPageSizeBase() {
      return pageSizeRoot;
   }

   public int getPageTimingMax() {
      return pageTimingMax;
   }

   public int getPageTimingMin() {
      return pageTimingMin;
   }

   /**
    * 
    * @return
    */
   public Integer getStart() {
      return lookUpPointer;
   }

   public boolean isAscending() {
      return isAscending;
   }

   public boolean isContinuePaging() {
      return isContinuePaging;
   }

   /**
    * True if list task should try again with the same parameters
    * @return
    */
   public boolean isContinuePagingAfterException() {
      errorCounter += 1;
      if (errorCounter > pc.getPagerExceptionCount()) {
         return false;
      }
      return true;
   }

   public boolean isDescending() {
      return !isAscending;
   }

   /**
    * True when page publish must not overflow when more are found.
    * @return
    */
   public boolean isExactPageSize() {
      return isManualExactPageSize;
   }

   public boolean isFitRange() {
      return isFitRange;
   }

   /**
    * Override if not
    * @return
    */
   public boolean isRangeInclusive() {
      return true;
   }

   public boolean isTimingEnabled() {
      return isTimingEnabled;
   }

   /**
    * true, pointer will go up
    * false, pointer will iterate down.
    * 
    * Some items don't have IDs on which they are naturally ordered.
    * PublicKey for instance.
    * But anothe kind of key could have a unique ID where 0 is the most important key
    * 
    * @param b
    */
   public void setAscending(boolean b) {
      isAscending = b;
   }

   public void setFitRange(boolean isFitRange) {
      this.isFitRange = isFitRange;
   }

   /**
    * Sets the last value to be looked up by the book. (inclusive)
    * 
    * For Account, default is the last mined account.
    * 
    * @param lookUpRangeEnd
    */
   public void setLookUpRangeEnd(Integer lookUpRangeEnd) {
      this.lookUpRangeEnd = lookUpRangeEnd;
   }

   public int getLookUpRangeStart() {
      return lookUpRangeStart;
   }

   public int getLookUpRangeEnd() {
      return lookUpRangeEnd;
   }

   /**
    * Sets the starting position of the book. (inclusive)
    * 
    * For Account, default is 0, the first account
    * 
    * @param lookUpRangeStart
    */
   public void setLookUpRangeStart(Integer lookUpRangeStart) {
      this.lookUpRangeStart = lookUpRangeStart;
   }

   /**
    * @see PagerAbstract#isManualExactPageSize
    * @param isExactPageSize
    */
   public void setManualExactPageSize(boolean isExactPageSize) {
      this.isManualExactPageSize = isExactPageSize;
   }

   /**
    * Set the page size. The maximum number of elements returned per page.
    * 
    * This page size might increase or decrease when timing is enable because then
    * we want constant time for page requests.
    * @param pageSize
    */
   public void setPageSize(Integer pageSize) {
      if (pageSize < 0) {
         throw new IllegalArgumentException();
      }
      if (pageSizeMax != null) {
         this.pageSizeRoot = Math.min(pageSizeMax, pageSize);
      } else {
         this.pageSizeRoot = pageSize;
      }
   }

   public void setPageSizeMax(Integer pageSizeMax) {
      this.pageSizeMax = pageSizeMax;
   }

   public void setPageTimingMax(int pageTimingMax) {
      this.pageTimingMax = pageTimingMax;
   }

   /**
    * Pager returns 1 result and if faster, it will increment page size
    * for a 250 ms second interval
    */
   public void setPagerToDefaultAdaptive() {
      this.setTimingEnabled(true);
      this.setPageTimingMin(250);
      this.setManualExactPageSize(false);
      this.setPageSize(1);
   }

   public void setPageTimingMin(int pageTimingMin) {
      this.pageTimingMin = pageTimingMin;
   }

   /**
    * When false, pager does not adapt the page size to provide constant timed output
    * @param isTimingEnable
    */
   public void setTimingEnabled(boolean isTimingEnable) {
      this.isTimingEnabled = isTimingEnable;
   }

   protected void setPageSizeActive(int pageSizeActive) {
      if (pageSizeActive < 0) {
         throw new IllegalArgumentException();
      }
      if(pageSizeMax != null) {
         this.pageSizeActive = Math.min(pageSizeMax, pageSizeActive);
      } else {
         this.pageSizeActive = pageSizeActive;
      }
   }

   /**
    * If timing is enabled, updates the page size based on the time it took to complete the previous page.
    * 
    * Increase is currently *2
    * Decrease is 2/3
    */
   private void timingChecks() {
      if (isTimingEnabled) {
         //compute timing needed for the last batch. if it was fast, increase number of objects per batch
         long now = System.currentTimeMillis();
         long diff = now - timing;
         if (diff < pageTimingMin) { //goal is to have a refresh at the level of human brain ability, i.e 200ms
            //#debug
            String msg = "Increasing PageSize from " + pageSizeActive + " to " + (pageSizeActive * 2) + " time=" + diff;
            //#debug
            toDLog().pWork(msg, this, PagerAbstract.class, "timingChecks", LVL_05_FINE, true);
            setPageSizeActive(pageSizeActive * 2); //it can overflow

         } else if (diff > pageTimingMax) {
            //#debug
            String msg = "Decreasing PageSize from " + pageSizeActive + " to " + ((pageSizeActive * 2 / 3) + 1) + " time=" + diff;
            //#debug
            toDLog().pWork(msg, this, PagerAbstract.class, "timingChecks", LVL_05_FINE, true);
            setPageSizeActive((pageSizeActive * 2 / 3) + 1); //+1 to avoid zero
         }
         timing = now;
      }
   }

   //#mdebug
   public IDLog toDLog() {
      return pc.toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PagerAbstract");
      toStringPrivate(dc);
      dc.nl();
      dc.appendVar("lookUpPointer", lookUpPointer);
      dc.appendVarWithSpace("lookUpRemainder", lookUpRemainder);
      dc.append("[");
      dc.appendVar("lookUpRangeStart", lookUpRangeStart);
      dc.appendVarWithSpace("lookUpRangeEnd", lookUpRangeEnd);
      dc.append("]");
      dc.nl();
      dc.appendVar("pageSizeRoot", pageSizeRoot);
      dc.appendVarWithSpace("pageSizeActive", pageSizeActive);
      dc.appendVarWithSpace("timing", timing);
      dc.nl();
      dc.appendVar("countProcessed", countProcessed);
      dc.appendVarWithSpace("countFiltered", countFiltered);
      dc.appendVarWithSpace("countPublished", countPublished);
      dc.nl();

      pc.toPD().toStringListT(dc, listLeftOver, "listLeftOver");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PagerAbstract");
      toStringPrivate(dc);
      dc.appendVarWithSpace("lookUpPointer", lookUpPointer);
      dc.appendVarWithSpace("pageSizeRoot", pageSizeRoot);
      dc.appendVarWithSpace("pageSizeActive", pageSizeActive);
      dc.appendVarWithSpace("lookUpRemainder", lookUpRemainder);
      dc.appendVarWithSpace("timing", timing);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("isContinuePaging", isContinuePaging);
      dc.appendVarWithSpace("isAscending", isAscending);
      dc.appendVarWithSpace("isFitRange", isFitRange);
      dc.appendVarWithSpace("isManualExactPageSize", isManualExactPageSize);
      dc.appendVarWithSpace("isTimingEnabled", isTimingEnabled);
      dc.appendVarWithSpace("isBuilt", isBuilt);
      dc.appendVarWithSpace("pageTimingMin", pageTimingMin);
      dc.appendVarWithSpace("pageTimingMax", pageTimingMax);
   }
   //#enddebug

}
