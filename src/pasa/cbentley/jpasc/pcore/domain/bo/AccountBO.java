/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.bo;

import pasa.cbentley.byteobjects.core.ByteObject;
import pasa.cbentley.byteobjects.ctx.BOCtx;
import pasa.cbentley.byteobjects.ctx.IBOTypesBOC;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccount;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccountMutable;
import pasa.cbentley.jpasc.pcore.domain.java.AccountJava;
import pasa.cbentley.jpasc.pcore.domain.tech.ITechAccount;
import pasa.cbentley.jpasc.pcore.domain.tech.ITechAccountVariableFullBO;
import pasa.cbentley.jpasc.pcore.utils.PascalCoinDouble;

/**
 * Provides methods to read data without unwrapping into a 
 * {@link AccountJava} which forces the read of all fields.
 * <br>
 * Reading variable length fields require computing their offsets.
 * <br>
 * 
 * This implementation does not try any fancy compression. It has a full header and the account number
 * is taking space along with publickey
 * @author Charles Bentley
 *
 */
public class AccountBO extends ByteObject implements ITechAccountVariableFullBO, IPCAccount {

   private static final int BASIC_SIZE = 0;

   /**
    * once computed, use it
    */
   private int[]            offsetMapping;

   private PCoreCtx         pc;

   /**
    * Empty basic.
    * 
    * {@link ByteObject#getType()} will return {@link IBOTypesBOC#TYPE_001_EXTENSION}
    * <br>
    * @param pc
    * @param boc
    */
   public AccountBO(PCoreCtx pc, BOCtx boc) {
      super(boc, new byte[ITechAccountVariableFullBO.ACCOUNT_BASIC_SIZE], 0);
      this.pc = pc;
   }

   public AccountBO(PCoreCtx pc, BOCtx boc, byte[] data, int offset) {
      super(boc, data, offset);
      this.pc = pc;
   }

   private void computeMapping() {
      int flag = get1(ACCOUNT_OFFSET_01_FLAG_1);
      int flagx = get1(ACCOUNT_OFFSET_02_FLAGX_1);

   }

   public PascalCoinDouble getAccountBalance() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_8_HAS_BALANCE)) {
         //how to code a double here? 0000 digits for molinas
         //possibly 4 bytes for biiiiig balances
         int offset = getVarOffset(ACCOUNT_VAR_03_BALANCE_3);
         int coins = get4(offset);
         int molinas = get2(offset + 4);
         return new PascalCoinDouble(pc, coins, molinas);
      } else {
         return pc.getZERO();
      }
   }

   public void addBalanceTo(PascalCoinDouble pascalCoinDouble) {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_8_HAS_BALANCE)) {
         //how to code a double here? 0000 digits for molinas
         //possibly 4 bytes for biiiiig balances
         int offset = getVarOffset(ACCOUNT_VAR_03_BALANCE_3);
         int coins = get4(offset);
         int molinas = get2(offset + 4);
         pascalCoinDouble.addCoinMolinas(coins, molinas);
      }
   }

   public Integer getAccountBlockLastUpdated() {
      return get3(ACCOUNT_OFFSET_06_AGE_3); //fixed size
   }

   public Integer getAccountBlockLockedUntil() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_5_HAS_LOCK)) {
         int offset = getVarOffset(ACCOUNT_VAR_04_LOCKBLOCK_3);
         return get3(offset);
      }
      return null;
   }

   public String getAccountEncodedPublicKey() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_4_HAS_PUBLIC_KEY)) {
         //how to code a double here? 0000 digits for molinas
         //possibly 4 bytes for biiiiig balances
         int offset = getVarOffset(ACCOUNT_OFFSET_07_PUBKEY_3);
         byte[] keyData = null;
         int keyOffset = 0;
         int keyLen = 0;
         return pc.getPU().hexStringFromByteArray(keyData, keyOffset, keyLen);
      } else {
         return null;
      }
   }

   /**
    * 
    * @throws ArrayIndexOutOfBoundsException if problem
    */
   public String getAccountName() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_6_HAS_NAME)) {
         int offsetName = getVarOffset(ACCOUNT_VAR_08_NAME);
         //64chars max
         //num char is coded in the first byte
         //bytes take more probably.. design a special class for Pascal64 encoding
         return pc.getPU().pascal64StringDecode(this.data, offsetName);
      }
      return null;
   }

   public Integer getAccountOperationCounter() {
      int offsetNumOp = getVarOffset(ACCOUNT_VAR_01_NUMOP_1);
      if (hasFlag(ACCOUNT_OFFSET_03_FLAGY_1, ACCOUNT_FLAGY_2_SMALL_OP_COUNT)) {
         return get1(offsetNumOp);
      } else if (hasFlag(ACCOUNT_OFFSET_03_FLAGY_1, ACCOUNT_FLAGY_2_SMALL_OP_COUNT)) {
         return getShortIntUnSigned(offsetNumOp);
      } else {
         return get4(offsetNumOp);
      }
   }

   public int getAccountState() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_1_HAS_SALE)) {
         return ITechAccount.ACCOUNT_STATE_1_LISTED;
      } else {
         return ITechAccount.ACCOUNT_STATE_0_NORMAL;
      }
   }

   public Integer getAccountType() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_7_HAS_TYPE)) {
         int offsetLock = getVarOffset(ACCOUNT_VAR_02_TYPE_2);
         //always 2 bytes unsigned
         return getShortIntUnSigned(offsetLock);
      }
      return 0;
   }

   public Integer getAccountValue() {
      return get3(ACCOUNT_OFFSET_00_ACCOUNT_3);
   }

   public IPCAccountMutable getMutableAccount() {
      // TODO Auto-generated method stub
      return null;
   }

   public Boolean getSaleIsPrivate() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_2_HAS_SALE_PRIVATE)) {
         return true;
      }
      return false;
   }

   public PascalCoinDouble getSalePrice() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_1_HAS_SALE)) {
         int offsetPrice = getVarOffset(ACCOUNT_VAR_07_PRICE_3);
         if (hasFlag(ACCOUNT_OFFSET_02_FLAGX_1, ACCOUNT_FLAGX_1_TINY_BALANCE)) {
            int coins = get4(offsetPrice);
            int molinas = get2(offsetPrice + 4);
            return new PascalCoinDouble(pc,coins, molinas);
         }
      }
      return pc.getZERO();
   }

   public String getSalePrivateNewEncPubkey() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_2_HAS_SALE_PRIVATE)) {
         int offset = getVarOffset(ACCOUNT_VAR_06_NEW_KEY_3);
         byte[] keyData = null;
         int keyOffset = 0;
         int keyLen = 0;
         return pc.getPU().hexStringFromByteArray(keyData, keyOffset, keyLen);
      }
      return null;
   }

   public Integer getSaleSellerAccount() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_1_HAS_SALE)) {
         int offset = getVarOffset(ACCOUNT_VAR_05_SELLER_ACCOUNT_3);
         return get3(offset);
      }
      return null;
   }

   /**
    * Compute an offset based on flags.
    * <br>
    * Fixed sized data is located in front.
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_OFFSET_00_ACCOUNT_3}
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_OFFSET_01_FLAG_1}
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_OFFSET_02_FLAGX_1}
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_OFFSET_03_FLAGY_1}
    * <li> {@link ITechAccountVariableFullBO#ACCOUNT_OFFSET_04_FLAGZ_1}
    * @return
    */
   private int getVarOffset(int offset) {
      int flag = get1(ACCOUNT_OFFSET_01_FLAG_1);
      int flagx = get1(ACCOUNT_OFFSET_02_FLAGX_1);

      if (offset == ACCOUNT_VAR_02_TYPE_2) {
         //read
      }
      if (offset == ACCOUNT_VAR_04_LOCKBLOCK_3) {
         //read
      }

      return 0;
   }
}
