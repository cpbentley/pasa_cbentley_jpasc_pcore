package pasa.cbentley.jpasc.pcore.ctx;

import pasa.cbentley.core.src4.interfaces.ITech;

/**
 * RPC specific tech constants
 *  from https://github.com/PascalCoin/PascalCoin/blob/master/src/core/UPCRPCFindAccounts.pas
 *  
   - `exact` : `name` must match (DEFAULT OPTION)
      - `startswith` :
      - `not-startswith` :
      - `contains` :
      - `not-contains` :
      - `endswith` :
      - `not-endswith` :"
 * @author Charles Bentley
 *
 */
public interface ITechPascRPC extends ITech {

   public static final String NAMESEARCHTYPE_EXACT = "exact";
   public static final String NAMESEARCHTYPE_ANY = "any";
   public static final String NAMESEARCHTYPE_NONE = "none";
   public static final String NAMESEARCHTYPE_CONTAINS = "contains";
   public static final String NAMESEARCHTYPE_STARTSWITH = "startswith";
   public static final String NAMESEARCHTYPE_NOT_STARTSWITH = "not-startswith";
   public static final String NAMESEARCHTYPE_NOT_CONTAINS = "not-contains";
   public static final String NAMESEARCHTYPE_ENDS_WITH = "endswith";
   public static final String NAMESEARCHTYPE_NOT_ENDS_WITH = "not-endswith";
   
  
   public static final String STATUS_TYPE_ALL = "all";
   public static final String STATUS_TYPE_FOR_SWAP = "for-sale";
   public static final String STATUS_TYPE_FOR_SWAP_ACCOUNT = "for-account-swap";
   public static final String STATUS_TYPE_FOR_SWAP_COIN = "for-coin-swap";
   public static final String STATUS_TYPE_FOR_SWAP_SALE = "for-sale-swap";
   public static final String STATUS_TYPE_NOT_FOR_SWAP_SALE = "not-for-sale-swap";
   public static final String STATUS_TYPE_FOR_SALE = "for-sale";
   public static final String STATUS_TYPE_FOR_SALE_PUBLIC = "for-public-sale";
   public static final String STATUS_TYPE_FOR_SALE_PRIVATE = "for-private-sale";
   
   
}
