/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import com.github.davidbolet.jpascalcoin.api.model.OperationSubType;
import com.github.davidbolet.jpascalcoin.api.model.OperationType;

import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.utils.BitUtils;
import pasa.cbentley.core.src4.utils.IntUtils;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class PascalUtils {

   public static final int     BLOCKS_3_YEAR           = 290304;

   public static final int     BLOCKS_3_YEARS_11_MONTH = 379188;

   public static final int     BLOCKS_3_YEARS_9_MONTH  = 363060;

   public static final int     BLOCKS_DAY              = 288;

   public static final int     BLOCKS_MONTH            = 8064;

   public static final int     BLOCKS_WEEK             = 2016;

   public static final int     BLOCKS_YEAR             = 96768;

   private final static char[] hexArray                = "0123456789ABCDEF".toCharArray();

   /**
    * double \\ escape.. be careful in copy pasting 
    * 
    */
   public static final String  PASCAL64_CHARS          = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-+{}[]_:\\\"|<>,.?/~";

   /**
    * 
    * @param c
    * @return -1 if none
    */
   public static int getIndexValue(char c) {
      return PASCAL64_CHARS.indexOf(c);
   }

   public static int getBitValue(char c) {
      switch (c) {
         case 'a':
            return 1;
         case 'b':
            return 2;
         case 'c':
            return 3;
         case 'd':
            return 4;
         case 'e':
            return 5;
         case 'f':
            return 6;
         case 'g':
            return 7;
         case 'h':
            return 8;
         case 'i':
            return 9;
         case 'j':
            return 10;
         case 'k':
            return 10;
         case 'l':
            return 10;
         case 'm':
            return 10;
         case 'n':
            return 10;
         case 'o':
            return 10;
         case 'p':
            return 10;
         case 'q':
            return 10;
         case 'r':
            return 10;
         case 's':
            return 10;
         case 't':
            return 10;
         case 'u':
            return 10;
         case 'v':
            return 10;
         case 'w':
            return 10;
         case 'x':
            return 10;
         case 'y':
            return 10;
         case 'z':
            return 10;

         default:
            throw new IllegalArgumentException(String.valueOf(c));
      }
   }

   public static char getCharValue(int value) {
      switch (value) {
         case 1:
            return 'a';
         case 2:
            return 'b';
         case 3:
            return 'c';
         case 4:
            return 'd';
         case 5:
            return 'e';
         case 6:
            return 'f';

         default:
            throw new IllegalArgumentException(String.valueOf(value));
      }
   }

   public static String getOperationSubTypeUserString(OperationSubType ot) {
      if (ot == OperationSubType.BUYACCONT_TARGET) {
         return "BuyAccountTarget";
      } else if (ot == OperationSubType.BUYACCOUNT_BUYER) {
         return "BuyAccountBuyer";
      } else if (ot == OperationSubType.BUYACCOUNT_SELLER) {
         return "BuyAccountSeller";
      } else if (ot == OperationSubType.BUYTRANSACTION_BUYER) {
         return "BuyTransactionBuyer";
      } else if (ot == OperationSubType.BUYTRANSACTION_SELLER) {
         return "BuyTransactionSeller";
      } else if (ot == OperationSubType.BUYTRANSACTION_TARGET) {
         return "BuyTransactionTarget";
      } else if (ot == OperationSubType.CHANGE_ACCOUNT_INFO) {
         return "ChangeAccountInfo";
      } else if (ot == OperationSubType.CHANGE_KEY) {
         return "ChangeKey";
      } else if (ot == OperationSubType.CHANGE_KEY_SIGNED) {
         return "ChangeKeySigned";
      } else if (ot == OperationSubType.DELIST_ACCOUNT) {
         return "DelistAccount";
      } else if (ot == OperationSubType.LIST_ACCOUNT_FOR_PRIVATE_SALE) {
         return "ListAccountPrivateSale";
      } else if (ot == OperationSubType.LIST_ACCOUNT_FOR_PUBLIC_SALE) {
         return "ListAccountPublicSale";
      } else if (ot == OperationSubType.RECOVER) {
         return "Recover";
      } else if (ot == OperationSubType.TRANSACTION_RECEIVER) {
         return "TransactionReceiver";
      } else if (ot == OperationSubType.TRANSACTION_SENDER) {
         return "TransactionSender";
      } else {
         return "UnknownType";
      }
   }

   public static String getOperationTypeUserString(OperationType ot) {
      if (ot == OperationType.BLOCKCHAINREWARD) {
         return "Reward";
      } else if (ot == OperationType.CHANGEKEY) {
         return "ChangeKey";
      } else if (ot == OperationType.RECOVERFUNDS) {
         return "RecoveryFunds";
      } else if (ot == OperationType.TRANSACTION) {
         return "Transaction";
      } else {
         if (ot == null) {
            return "Type is NULL";
         } else {
            return "UnknownType " + ot.getValue();
         }
      }
   }

   /**
    * -1 if all chars in str respect pascal 64 encoding.
    * 
    * @param str
    * @return -1 if str is valid.. otherwise the index of the first invalid char
    */
   public static int hasInvalidIndex(String str) {
      for (int i = 0; i < str.length(); i++) {
         char c = str.charAt(i);
         if (PASCAL64_CHARS.indexOf(c) == -1) {
            return i;
         }
      }
      return -1;
   }

   public static String hexTo(String arg) {
      try {
         return String.format("%x", new BigInteger(1, arg.getBytes("UTF-8")));
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
         return null;
      }
   }

   private PCoreCtx pc;

   public PascalUtils(PCoreCtx pc) {
      this.pc = pc;
   }

   /**
    * Reads an encoded Pascal64 string at offset
    * Will bomb is invalid data
    * @param data
    * @param offset
    * @param numchars
    * @return
    * @throws ArrayIndexOutOfBoundsException if data is corrupted or offset invalid
    */
   public String pascal64StringDecode(byte[] data, int offset) {
      int numChars = data[offset++];
      char[] array = new char[numChars];
      for (int i = 0; i < array.length;) {
         //read minLeftBytes. at least one
         int minLeftBytes = Math.min(3, array.length - i);
         //let built the 24 bits template
         int template = 0;
         if (minLeftBytes == 1) {
            template = (data[offset++] & 0xFF) << 0;
         } else if (minLeftBytes == 3) {
            template = (data[offset++] & 0xFF) << 16;
            template |= (data[offset++] & 0xFF) << 8;
            template |= (data[offset++] & 0xFF) << 0;
         } else if (minLeftBytes == 2) {
            template |= (data[offset++] & 0xFF) << 8;
            template |= (data[offset++] & 0xFF) << 0;
         } 
         //iterate 
         int numCharIteration = Math.min(4, array.length - i);
         for (int j = 0; j < numCharIteration; j++) {
            int bitShift = j * 6;
            int charCodedValue = (template >> bitShift) & BitUtils.MASK_06_BITS;
            char c = PASCAL64_CHARS.charAt(charCodedValue);
            array[i++] = c;
         }
      }
      return new String(array);
   }

   public byte[] pascal64StringEncode(String string, int offset, int len) {
      return pascal64StringEncode(string.substring(offset, len));
   }
   /**
    * 
    * @param string
    * @return non null byte array, a PascalCoin64 encoded byte representation 
    * @throws IllegalArgumentException if length of string is bigger than 63
    * @throws IllegalArgumentException if string contains invalida characters
    */
   public byte[] pascal64StringEncode(String string) {
      if (string.length() > 63) {
         throw new IllegalArgumentException(string);
      }
      int lengthBits = 8 + string.length() * 6;
      int lengthBytes = IntUtils.divideCeil(lengthBits, 8);
      byte[] data = new byte[lengthBytes];
      //first byte is for the number of characters
      data[0] = (byte) string.length();

      //for speed, we will write by chunks of 4. that's 24 bits. and in * 
      //we write 4 letters, we write 3 bytes
      int indexData = 1;
      for (int i = 0; i < string.length();) {
         int template = 0;
         int templateSize = Math.min(3, string.length() - i);
         int charIteration = Math.min(4, string.length() - i);
         for (int j = 0; j < charIteration; j++) {
            char c = string.charAt(i++);
            int v = getIndexValue(c);
            if(v == -1) {
               throw new IllegalArgumentException(string);
            }
            int shiftSize = j * 6;
            int shiftedValue = v << shiftSize;
            template = (template + shiftedValue);
         }
         //go in reverse so as to write first the byte data of the first char
         for (int j = templateSize - 1; j >= 0; j--) {
            int shiftSize = j * 8;
            data[indexData++] = (byte) ((template >> shiftSize) & 0xFF);
         }
      }
      return data;
   }

   /**
    * Returns a balance always padded with 4 decimals, and given string between thoushands
    * @param d
    * @param sep the separator for 1 999 999.00 thoushands
    * @return
    */
   public String getPrettyPascBalance(double d, String sep) {
      String str = String.valueOf(d);
      int len = str.length();
      int index = str.indexOf('.'); //by construction never -1 
      StringBBuilder sb = new StringBBuilder(pc.getUCtx());
      if (str != null && !str.equals("")) {
         String sub = str.substring(0, index);
         if (sub.length() <= 3) {
            sb.append(sub);
         } else {
            int firstNumChars = sub.length() % 3;
            if (firstNumChars == 0) {
               firstNumChars = 3;
            }
            sb.append(sub.substring(0, firstNumChars));
            int count = firstNumChars;
            //now do it for all 3 
            while (count + 3 <= sub.length()) {
               sb.append(sep);
               sb.append(sub.substring(count, count + 3));
               count += 3;
            }
         }
      }
      sb.append(str.substring(index, str.length()));
      int numZero = len - index - 1;
      //pad with zeros
      for (int i = numZero; i < 4; i++) {
         sb.append('0');
      }

      return sb.toString();
   }

   public String hexStringFromByteArray(byte[] bytes) {
      char[] hexChars = new char[bytes.length * 2];
      for (int j = 0; j < bytes.length; j++) {
         int v = bytes[j] & 0xFF;
         hexChars[j * 2] = hexArray[v >>> 4];
         hexChars[j * 2 + 1] = hexArray[v & 0x0F];
      }
      return new String(hexChars);
   }

   public String hexStringFromByteArray(byte[] bytes, int offset, int len) {
      char[] hexChars = new char[len * 2];
      for (int j = 0; j < len; j++) {
         int joffset = offset + j;
         int v = bytes[joffset] & 0xFF;
         hexChars[joffset * 2] = hexArray[v >>> 4];
         hexChars[joffset * 2 + 1] = hexArray[v & 0x0F];
      }
      return new String(hexChars);
   }

   /**
    * Must be a valid HEX String, i.e. even number, with characters from 0123456789ABCDEF
    * @param hex
    * @return
    */
   public byte[] hexStringToByteArray(String hex) {
      int len = hex.length();
      byte[] data = new byte[len / 2];
      for (int i = 0; i < len; i += 2) {
         data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
      }
      return data;
   }

   public boolean isValidChar(char c) {
      return false;
   }

}
