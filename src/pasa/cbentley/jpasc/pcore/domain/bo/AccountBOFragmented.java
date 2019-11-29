/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.bo;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.core.ByteObjectArray;
import pasa.cbentley.byteobjects.src4.core.ByteObjectFragmented;
import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccount;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccountMutable;
import pasa.cbentley.jpasc.pcore.domain.java.AccountJava;
import pasa.cbentley.jpasc.pcore.domain.tech.ITechAccountVariableFullBO;
import pasa.cbentley.jpasc.pcore.utils.PascalCoinDouble;

/**
 * Object read from {@link ByteObjectArray} with fragmented objects.
 * <br>
 * Header is implicit.
 * Account Number is implicit by its position and thus given as a paramter in the constructor.
 * <br>
 * This setup saves 4 + 3 = 7 bytes on the regular setup.. However 4 bytes of the account is
 * consumed by the offset table.
 * 
 * Provides methods to read data without unwrapping into a 
 * {@link AccountJava} which forces the read of all fields.
 * <br>
 * Reading variable length fields require computing their offsets.
 * <br>
 * 
 * @author Charles Bentley
 *
 */
public class AccountBOFragmented extends ByteObjectFragmented implements ITechAccountVariableFullBO, IPCAccount {

   private static final int BASIC_SIZE = 0;

   /**
    * to be inlined. adapt the {@link ITechAccountVariableFullBO} tech def to the fact the Account data
    * is fragmented. Our byte array does not contains the header and the account 
    * @param offset
    * @return
    */
   private static final int getOffset(int offset) {
      return offset - ACCOUNT_BASIC_SIZE - 4;
   }

   private int              accountValue;

   /**
    * once computed, use it
    */
   private int[]            offsetMapping;

   private PCoreCtx         pc;

   
   /**
    * @param pc
    * @param boc
    * @param data bytes to read from
    * @param offset starting offset of first value {@link ITechAccountVariableFullBO#ACCOUNT_OFFSET_01_FLAG_1}
    * @param len length in bytes (cannot be read from data since its fragmented)
    * @param header the header fragment
    * @param account the value of the account, fragment not seen in {@link ITechAccountVariableFullBO#ACCOUNT_OFFSET_00_ACCOUNT_3} because implicitely computed
    */
   public AccountBOFragmented(PCoreCtx pc, BOCtx boc, byte[] data, int offset, int len, ByteObject header, int account) {
      super(boc, data, offset,len, header);
      this.pc = pc;
      this.accountValue = account;
   }

   private void computeMapping() {
      int flag = get1(ACCOUNT_OFFSET_01_FLAG_1);
      int flagx = get1(ACCOUNT_OFFSET_02_FLAGX_1);

   }

   public Integer getAccountValue() {
      return accountValue;
   }

   public Double getBalance() {
      if (hasFlag(ACCOUNT_OFFSET_01_FLAG_1, ACCOUNT_FLAG_8_HAS_BALANCE)) {
         //
         if (offsetMapping == null) {
            computeMapping();
         }
         //how to code a double here? 0000 digits for molinas
         //possibly 4 bytes for biiiiig balances
         int offset = getVarOffset(ACCOUNT_OFFSET_00_ACCOUNT_3);
         int coins = get4(offset);
         int molinas = get2(offset + 4);
         return Double.parseDouble(coins + "." + molinas);
      } else {
         return 0.0;
      }
   }

   private int getVarOffset(int accountOffset00Account3) {
      // TODO Auto-generated method stub
      return 0;
   }

   /**
    * Returns a real {@link AccountBO} with a fresh full new byte array
    * @return
    */
   public AccountBO getCopyFull() {

      AccountBO accountBO = new AccountBO(pc, boc);

      return accountBO;
   }

   public Integer getAccountBlockLastUpdated() {
      return get3(ACCOUNT_OFFSET_06_AGE_3); //fixed size
   }

   public PascalCoinDouble getAccountBalance() {
      // TODO Auto-generated method stub
      return null;
   }

   public String getAccountName() {
      // TODO Auto-generated method stub
      return null;
   }

   public int getAccountState() {
      // TODO Auto-generated method stub
      return 0;
   }

   public Integer getAccountType() {
      // TODO Auto-generated method stub
      return null;
   }

   public String getAccountEncodedPublicKey() {
      // TODO Auto-generated method stub
      return null;
   }

   public Integer getAccountBlockLockedUntil() {
      // TODO Auto-generated method stub
      return null;
   }

   public IPCAccountMutable getMutableAccount() {
      // TODO Auto-generated method stub
      return null;
   }

   public Integer getAccountOperationCounter() {
      // TODO Auto-generated method stub
      return null;
   }

   public Boolean getSaleIsPrivate() {
      // TODO Auto-generated method stub
      return null;
   }

   public PascalCoinDouble getSalePrice() {
      // TODO Auto-generated method stub
      return null;
   }

   public String getSalePrivateNewEncPubkey() {
      // TODO Auto-generated method stub
      return null;
   }

   public Integer getSaleSellerAccount() {
      // TODO Auto-generated method stub
      return null;
   }

}
