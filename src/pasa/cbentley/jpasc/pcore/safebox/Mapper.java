/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.safebox;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.io.BADataOS;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.utils.BitUtils;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;
import pasa.cbentley.jpasc.pcore.domain.tech.ITechAccountVariableFullBO;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.AccountState;
import pasa.cbentley.jpasc.pcore.utils.PascalUtils;

/**
 * 
 * @author Charles Bentley
 *
 */
public class Mapper implements ITechAccountVariableFullBO {

   private HashMap<String, Integer> keyToOffset = new HashMap<String, Integer>();

   private PCoreCtx                 pc;

   private AccountBO                boTemplate;

   private boolean                  keyInternal;

   public Mapper(PCoreCtx pc) {
      this.pc = pc;

      boTemplate = new AccountBO(pc, pc.getBOC());
   }

   /**
    * Maps {@link Account} to the {@link ITechAccountVariableFullBO} spec
    * @param dos
    * @param a
    * @throws IOException
    */
   public void mapToAccountBO(BADataOS dos, Account a) {
      Integer acNum = a.getAccount();
      Double balance = a.getBalance();
      String encPubKey = a.getEncPubkey();
      Integer lockUntilBlock = a.getLockedUntilBlock();
      String name = a.getName();
      String newPubkey = a.getNewEncPubkey();
      Integer nOp = a.getnOperation();
      Double price = a.getPrice();
      Boolean isPrivate = a.getPrivateSale();
      Integer sellerAccount = a.getSellerAccount();
      AccountState as = a.getState();
      Integer type = a.getType();
      Integer updatedBlock = a.getUpdatedB();

      // 
      int v = ACCOUNT_OFFSET_01_FLAG_1;
      int bitFlag = 0; // ACCOUNT_OFFSET_01_FLAG_1
      int bitFlagX = 0; // ACCOUNT_OFFSET_01_FLAG_1

      int balanceMantisse;
      int balanceDecimal;
      //compute the bit flag based on account properties
      if (balance != null && balance.doubleValue() != 0.0) {
         bitFlag = BitUtils.setFlag(bitFlag, ACCOUNT_FLAG_8_HAS_BALANCE, true);
         BigDecimal bigDecimal = BigDecimal.valueOf(balance.doubleValue());
         int mantisse = bigDecimal.intValue();
         int decimal = bigDecimal.remainder(BigDecimal.ONE).unscaledValue().intValue();
         int scale = bigDecimal.scale();

         if (mantisse == 0) {
            if (scale < 3) {
               bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_1_TINY_BALANCE, true);
               bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_2_SMALL_BALANCE, true);
            } else {
               //
               if (decimal <= 255) {
                  bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_1_TINY_BALANCE, true);
               } else {
                  bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_2_SMALL_BALANCE, true);
               }
            }
         } else if (decimal == 0) {
            if (mantisse <= BitUtils.MASK_08_BITS) {
               bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_1_TINY_BALANCE, true);
               bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_3_AVERAGE_BALANCE, true);
            } else if (mantisse <= BitUtils.MASK_16_BITS) {
               bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_2_SMALL_BALANCE, true);
               bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_3_AVERAGE_BALANCE, true);
            } else {
               //code on 4 bytes. 
               bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGX_4_BIG_BALANCE, true);
            }
         } else {
            //both non zero

         }
      }

      if (as == AccountState.LISTED) {
         bitFlag = BitUtils.setFlag(bitFlag, ACCOUNT_FLAG_1_HAS_SALE, true);
         //most prices are 5 digits
         if (price != null) {

         }
         if(isPrivate) {
            bitFlag = BitUtils.setFlag(bitFlag, ACCOUNT_FLAG_2_HAS_SALE_PRIVATE, true);
         }
      } else {
         //normal
      }

      if (lockUntilBlock != null) {
         bitFlag = BitUtils.setFlag(bitFlag, ACCOUNT_FLAG_5_HAS_LOCK, true);
      }
      if (name != null && !name.equals("")) {
         bitFlag = BitUtils.setFlag(bitFlag, ACCOUNT_FLAG_6_HAS_NAME, true);
      }
      if (type != null && type.intValue() != 0) {
         bitFlag = BitUtils.setFlag(bitFlag, ACCOUNT_FLAG_7_HAS_TYPE, true);
      }

      if (nOp < 256) {
         bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGY_2_SMALL_OP_COUNT, true);
      } else if (nOp < 65536) {
         bitFlagX = BitUtils.setFlag(bitFlagX, ACCOUNT_FLAGY_3_AVERAGE_OP_COUNT, true);
      }

      //first write things that are never null
      //TODO write byte object header
      byte[] bs = boTemplate.getByteObjectData();
      dos.write(bs);

      //first write the bitFlag
      dos.write((bitFlag) & 0xFF);
      dos.write((bitFlagX) & 0xFF);
      dos.writeInt(acNum);
      dos.writeInt(updatedBlock);

      if (BitUtils.hasFlag(bitFlag, ACCOUNT_FLAG_8_HAS_BALANCE)) {
         //non zero balance
         //code for balance size

         dos.writeDouble(balance.doubleValue());
      }

      if (BitUtils.hasFlag(bitFlagX, ACCOUNT_FLAGY_2_SMALL_OP_COUNT)) {
         dos.write(nOp); //write it as a byte
      } else if (BitUtils.hasFlag(bitFlagX, ACCOUNT_FLAGY_3_AVERAGE_OP_COUNT)) {
         dos.writeShort(nOp); //write it as a byte
      } else {
         dos.writeInt(nOp);
      }

      PascalUtils pu = pc.getPU();
      //we could implement a pubKey trie here
      //convert pubKey to bytes
      if(keyInternal) {
         //offset is max 2gb?
         //pointer.. what is the accounts are split into different areas?
         //rewrite the value.. its only valid in this area
         Integer offset = keyToOffset.get(encPubKey);
         if(offset == null) {
            int byteOffset = dos.getOut().getByteWrittenCount();
            keyToOffset.put(encPubKey, new Integer(byteOffset));
            byte[] dataKey = pu.hexStringToByteArray(encPubKey);
            dos.writeShort(dataKey.length);
            dos.write(dataKey);
         } else {
            dos.writeInt(v);
         }
      } else {
         byte[] dataKey = pu.hexStringToByteArray(encPubKey);
         dos.writeShort(dataKey.length);
         dos.write(dataKey);
      }

      if (BitUtils.hasFlag(bitFlag, ACCOUNT_FLAG_1_HAS_SALE)) {
         //write all the list elements
         if (BitUtils.hasFlag(bitFlagX, ACCOUNT_FLAGX_6_SMALL_PRICE)) {
            double d = price.doubleValue();
            d = d * 10000;
            //int v = (int) d;
            dos.writeShort(v);
         } else {
            dos.writeDouble(price);
         }

         dos.writeInt(sellerAccount);
         //dos.writeBoolean(isPrivate); //no need to write this. its in the flag
         if (BitUtils.hasFlag(bitFlag, ACCOUNT_FLAG_2_HAS_SALE_PRIVATE)) {
            byte[] dataNewKey = pu.hexStringToByteArray(newPubkey);
            dos.writeShort(dataNewKey.length);
            dos.write(dataNewKey);
         }
      }

      if (BitUtils.hasFlag(bitFlag, ACCOUNT_FLAG_5_HAS_LOCK)) {
         dos.writeInt(lockUntilBlock);
      }
      //no need to private sell data if not sold
      if (BitUtils.hasFlag(bitFlag, ACCOUNT_FLAG_6_HAS_NAME)) {
         dos.write(pc.getPU().pascal64StringEncode(name));
      }
      if (BitUtils.hasFlag(bitFlag, ACCOUNT_FLAG_7_HAS_TYPE)) {
         //max 16 bites
         dos.writeShort(type);
      }

   }


}
