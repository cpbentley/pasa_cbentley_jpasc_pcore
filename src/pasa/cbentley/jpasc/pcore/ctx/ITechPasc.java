package pasa.cbentley.jpasc.pcore.ctx;

import pasa.cbentley.core.src4.interfaces.ITech;

/**
 * TODO contextualize this
 * @author Charles Bentley
 *
 */
public interface ITechPasc extends ITech {
   
   public static final Short  DEFAULT_MAINNET_PORT       = 4004;

   public static final Short  DEFAULT_MAINNET_RPC_PORT   = 4003;

   public static final Short  DEFAULT_MAINNET_MINER_PORT = 4009;

   public static final Short  DEFAULT_TEST_PORT          = 4104;

   public static final Short  DEFAULT_TEST_RPC_PORT      = 4103;

   public static final Short  DEFAULT_TEST_MINER_PORT    = 4109;

   public static final String DEFAULT_URL                = "http://127.0.0.1";

   public static final int    BLOCKS_3_YEAR              = 315360;

   /**
    * 
    */
   public static final int    BLOCKS_2_YEARS             = 210240;

   /**
    * November of 3rd year
    */
   public static final int    BLOCKS_3_YEARS_11_MONTH    = 411720;

   public static final int    BLOCKS_3_YEARS_9_MONTH     = 394200;

   /**
    * A Pascal 4 Year is 420480 blocks
    */
   public static final int    BLOCKS_4_YEARS             = 420480;

   public static final int    BLOCKS_4_YEARS_WEEK        = 422496;

   /**
    * 
    */
   public static final int    BLOCKS_DAY                 = 288;

   public static final int    BLOCKS_HOUR                = 12;

   /**
    * A pascal year divided by 12
    */
   public static final int    BLOCKS_MONTH               = 8760;

   /**
    * A Pascal year devided 52.1429
    */
   public static final int    BLOCKS_WEEK                = 2016;

   /**
    * A pascal year 
    */
   public static final int    BLOCKS_YEAR                = 105120;

   /**
    * double \\ escape.. be careful in copy pasting 
    * 
    */
   public static final String PASCAL64_CHARS             = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-+{}[]_:\\\"|<>,.?/~";
}
