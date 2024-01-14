/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.tech;

import pasa.cbentley.byteobjects.src4.core.interfaces.IByteObject;

/**
 * We start to list fixed size data
 * <br>
 * <br>
 * We want fast read version for historical node processing lots of queries
 * and
 * fast write version for consensus node
 * <br>
 * In full sequential reads, data must all packed together. TODO test that
 * 
 * For queries on all accounts ages, we shall have a memory space with only accounts numbers
 * Name is variable
 * 
 * For archiving purpose of a snapshot, data can be compressed.
 * 
 * <br>
 * <br>
 * How is coded balance and price data?
 * <br>
 * <li> {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_1_TINY_BALANCE} 
 * <li> {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_2_SMALL_BALANCE}
 * <li> {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_3_AVERAGE_BALANCE}
 * <li> {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_4_BIG_BALANCE}
 * 
 * When balance {@link ITechAccountVariableFullBO#ACCOUNT_FLAG_8_HAS_BALANCE} is not set balance is 0.0
 * 
 * When no X bit are set, the value is coded on 8 bytes. extreme rare case
 * 
 * @author Charles Bentley
 *
 */
public interface ITechAccountVariableFullBO extends IByteObject {

   public final static int ACCOUNT_BASIC_SIZE               = A_OBJECT_BASIC_SIZE + 36;

   /**
    * When true, account is being listed for sale.
    * We have a price and a seller account
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_VAR_07_PRICE_3}
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_VAR_05_SELLER_ACCOUNT_3}
    */
   public final static int ACCOUNT_FLAG_1_HAS_SALE          = 1 << 0;

   /**
    * When flag is true, above flag must be true. sale is private and we have a seller account encoded
    * and a Key for the destination of the sale.
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_VAR_06_NEW_KEY_3}
    */
   public final static int ACCOUNT_FLAG_2_HAS_SALE_PRIVATE  = 1 << 1;

   /**
    * When true, offsets are precomputed in a small table.
    */
   public static final int ACCOUNT_FLAG_3_HAS_PRE_COMPUTED  = 1 << 2;

   /**
    * When true, a public key is defined..
    * <br>
    * In the future. it possibly might be possible.
    */
   public static final int ACCOUNT_FLAG_4_HAS_PUBLIC_KEY    = 1 << 3;

   /**
    * When true, lock is no zero 4 bytes
    */
   public static final int ACCOUNT_FLAG_5_HAS_LOCK          = 1 << 4;

   /**
    * When name is not empty
    */
   public final static int ACCOUNT_FLAG_6_HAS_NAME          = 1 << 5;

   /**
    * When type is not zero
    */
   public final static int ACCOUNT_FLAG_7_HAS_TYPE          = 1 << 6;

   /**
    * When account has a non zero balance
    */
   public final static int ACCOUNT_FLAG_8_HAS_BALANCE       = 1 << 7;

   /**
    * Value is coded on 1 byte. 
    * <li>0.0255 if other flags are zero
    * <li>0.99 if {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_2_SMALL_BALANCE} is set
    * <li>255.0000 if {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_3_AVERAGE_BALANCE} is set
    * 
    * Enable to save dust value efficiently
    */
   public static final int ACCOUNT_FLAGX_1_TINY_BALANCE     = 1 << 0;

   /**
    * Value is coded on 2 bytes
    * <li> 0.9999 if other flags are zero
    * <li> 255.99 if {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_3_AVERAGE_BALANCE} is set
    */
   public static final int ACCOUNT_FLAGX_2_SMALL_BALANCE    = 1 << 1;

   /**
    * Value is coded on 3 bytes
    * <li> 255.9999
    * <li> 65555.99  if {@link ITechAccountVariableFullBO#ACCOUNT_FLAGX_4_BIG_BALANCE} is set
    */
   public static final int ACCOUNT_FLAGX_3_AVERAGE_BALANCE  = 1 << 2;

   /**
    * When balance is coded on 4 bytes.
    * <li>65555.9999
    */
   public static final int ACCOUNT_FLAGX_4_BIG_BALANCE      = 1 << 3;

   /**
    * @see ITechAccountVariableFullBO#ACCOUNT_FLAGX_1_TINY_BALANCE
    */
   public static final int ACCOUNT_FLAGX_5_TINY_PRICE       = 1 << 4;

   /**
    * @see ITechAccountVariableFullBO#ACCOUNT_FLAGX_2_SMALL_BALANCE
    */
   public static final int ACCOUNT_FLAGX_6_SMALL_PRICE      = 1 << 5;

   /**
    * @see ITechAccountVariableFullBO#ACCOUNT_FLAGX_3_AVERAGE_BALANCE
    */
   public static final int ACCOUNT_FLAGX_7_AVERAGE_PRICE    = 1 << 6;

   /**
    * Price is coded on 8 bytes. Price does not have a max.
    */
   public static final int ACCOUNT_FLAGX_8_MAX_PRICE        = 1 << 7;

   /**
    * When op count is 255 or less
    */
   public static final int ACCOUNT_FLAGY_2_SMALL_OP_COUNT   = 1 << 1;

   /**
    * When op count is 65000 or less. If both false, op count is coded on 4 bytes
    */
   public static final int ACCOUNT_FLAGY_3_AVERAGE_OP_COUNT = 1 << 2;

   /**
    * When pubkey is a pointer to the same array
    */
   public static final int ACCOUNT_FLAGY_6_INTERNAL_PUBKEY  = 1 << 7;

   /**
    * When name is a pointer to an external list
    */
   public static final int ACCOUNT_FLAGY_7_EXTERNAL_NAME    = 1 << 7;

   /**
    * When pubkey is a pointer to an external list (Compression purposes).
    * <br>
    * When reading a value, external reader must check and know about this list
    */
   public static final int ACCOUNT_FLAGY_8_EXTERNAL_PUBKEY  = 1 << 7;

   /**
    * Flags
    */
   public final static int ACCOUNT_OFFSET_01_FLAG_1         = A_OBJECT_BASIC_SIZE;

   /**
    * 
    */
   public final static int ACCOUNT_OFFSET_02_FLAGX_1        = A_OBJECT_BASIC_SIZE;

   /**
    * Codes dust balances. Ctrl byte..
    * <li>1 byte molinas 0.0001 to 0.0255
    * <li>2 bytes molineas 0.0001 to 0.9999
    */
   public final static int ACCOUNT_OFFSET_03_FLAGY_1        = A_OBJECT_BASIC_SIZE;

   public final static int ACCOUNT_OFFSET_04_FLAGZ_1        = A_OBJECT_BASIC_SIZE;

   /**
    * Fixed Size.Unsigned 3 bytes
    * <br>
    * 2 billions made value. Its ok for PASAs {@link Integer#MAX_VALUE}
    * 
    */
   public final static int ACCOUNT_OFFSET_00_ACCOUNT_3      = A_OBJECT_BASIC_SIZE;

   /**
    * Treat as unsigned 3 bytes.. if extra variable
    */
   public final static int ACCOUNT_OFFSET_06_AGE_3          = A_OBJECT_BASIC_SIZE;

   /**
    * Pointer to the key. So the actual key data (heavy) is stored only once.
    * A minim
    */
   public final static int ACCOUNT_OFFSET_07_PUBKEY_3       = A_OBJECT_BASIC_SIZE;

   public final static int ACCOUNT_VAR_01_NUMOP_1           = A_OBJECT_BASIC_SIZE;

   public final static int ACCOUNT_VAR_02_TYPE_2            = A_OBJECT_BASIC_SIZE;

   /**
    * Mostly 0 for most accounts. treat it as variable
    */
   public final static int ACCOUNT_VAR_03_BALANCE_3         = A_OBJECT_BASIC_SIZE;

   /**
    * 3 bytes
    */
   public final static int ACCOUNT_VAR_04_LOCKBLOCK_3       = A_OBJECT_BASIC_SIZE;

   public final static int ACCOUNT_VAR_05_SELLER_ACCOUNT_3  = A_OBJECT_BASIC_SIZE;

   public final static int ACCOUNT_VAR_06_NEW_KEY_3         = A_OBJECT_BASIC_SIZE;

   public final static int ACCOUNT_VAR_07_PRICE_3           = A_OBJECT_BASIC_SIZE;

   public static final int ACCOUNT_VAR_08_NAME              = 0;

}
