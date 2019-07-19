/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import java.util.List;

import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.filter.IFilterAccountT;
import pasa.cbentley.jpasc.pcore.pages.PagerAbstract;

/**
 * 
 * Provides direct access to some Account data. Account representation is generic here and must be specified
 * by a specializing interface.
 * 
 * Accounts might come from 
 * <li> RPC blockchain
 * <li> Local Cache
 * <li> Snapshot
 * <li> Wallet accounts
 * 
 * @see IAccessAccountDBolet
 * @see IAccessAccountJava
 * @author Charles Bentley
 *
 */
public interface IAccessAccount<T> extends IStringable {

   /**
    * Returns a list of accounts within the range of min and max balance.
    * 
    * @param page
    * @param minBalance
    * @param maxBalance
    * @return
    */
   public List<T> getAccountsRangeBalance(PagerAbstract<T> page, Double minBalance, Double maxBalance);

   public List<T> getAccountsRangeAge(PagerAbstract<T> page, Integer minAge, Integer maxAge);

   public List<T> getAccountsRangePrice(PagerAbstract<T> page, Double minPrice, Double maxPrice);

   public List<T> getAccountsType(PagerAbstract<T> page, Integer type);
   
   /**
    * Returns accounts with a non null String name.
    * <br><br>
    * If name is null or "", returns all accounts with no name
    * @param str
    * @return
    */
   public List<T> getAccountsWithName(PagerAbstract<T> page, String str);

   
   public List<T> getAccountsFilter(PagerAbstract<T> page, IFilterAccountT<T> filter);
   
   /**
    * Find an exact match, null if none
    * @param str
    * @return
    */
   public T getAccountWithName(String str);

   /**
    * null if account is not available in Access
    * @param account
    * @return
    */
   public T getAccount(Integer account);


   /**
    * List all accounts of that key.
    * 
    * If page is null, returns all
    * 
    * @param pk
    * @return
    */
   public List<T> getAccountsKey(PagerAbstract<T> page, PublicKeyJava pk);

   public List<T> getAccountsAll(PagerAbstract<T> page);

}
