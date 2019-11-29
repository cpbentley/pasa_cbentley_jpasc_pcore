/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.ipc;

import pasa.cbentley.byteobjects.src4.tech.ITechAcceptor;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.domain.tech.ITechAccount;
import pasa.cbentley.jpasc.pcore.utils.PascalCoinDouble;

/**
 * IPC InterfacePascalCoin  Read Only to a PASA
 * 
 * For operations, Gui deals with {@link IPCAccount}.
 * 
 * Unlink from the underlying implementation (dbolet,java or bo)
 * 
 * 
 * @author Charles Bentley
 *
 */
public interface IPCAccount extends ITechAccount, IStringable {
   /**
    * 
    * @return
    */
   public PascalCoinDouble getAccountBalance();

   /**
    * 
    * @return
    */
   public String getAccountName();

   /**
    * 
    * @return
    */
   public int getAccountState();

   /**
    * Name is renamed to avoid collision with popular getType()
    * @return
    */
   public Integer getAccountType();

   /**
    * 
    * @return
    */
   public Integer getAccountValue();

   /**
    * 
    * @return
    */
   public String getAccountEncodedPublicKey();

   /**
    * 
    * @return
    */
   public Integer getAccountBlockLastUpdated();

   /**
    * 
    * @return
    */
   public Integer getAccountBlockLockedUntil();

   /**
    * Tries to build a mutable versioni of this account.
    * null if mutation is not supported by this implementation.
    * @return
    */
   public IPCAccountMutable getMutableAccount();

   /**
    * 
    * @return
    */
   public Integer getAccountOperationCounter();

   /**
    * 
    * @return
    */
   public Boolean getSaleIsPrivate();

   /**
    * 
    * @return
    */
   public PascalCoinDouble getSalePrice();

   /**
    * 
    * @return
    */
   public String getSalePrivateNewEncPubkey();

   /**
    * 
    * @return
    */
   public Integer getSaleSellerAccount();

}
