/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

public class PASCAddressValidation {

   public static final char checksSumSeparator               = '-';

   /**
    * We don't have access to the current last valid PASA. Assume user knows but
    * check anyways for obvious mistakes.
    */
   public static final int  maxAccountNumber                 = 10000000;

   
   /**
    * Localize this
    */
   public static String     ERROR_ONLY_NUMERICAL             = "Only numerical numbers optionally followed by a - and the checksum";

   public static String     ERROR_CHECKSUM_SIZE              = "Invalid checksum size. Must be 2 characters";

   public static String     ERROR_INVALID_RANGE              = "Account Number invalid. Out of Range. 0 - 10 000 000";

   public static String     ERROR_INVALID_CHECKSUM_STRUCTURE = "Invalid checksum. Only numerical numbers";

   public static String     ERROR_INVALID_CHECKSUM           = "Checksum invalid. Please double check PASA for typing errors";

   public AddressValidationResult validate(String address) {
      int indexSeparator = address.indexOf(checksSumSeparator);
      if (indexSeparator == -1) {
         //we don't have a checksum.
         //check we have a real number
         try {
            int accountInt = Integer.valueOf(address);
            // check if not too big
            return validateInteger(accountInt);
         } catch (NumberFormatException e) {
            return AddressValidationResult.invalidAddress(ERROR_ONLY_NUMERICAL);
         }
      } else {
         String account = "";
         String checkSum = "";

         //we must avoid situations like "-" or "10-" or "" to bomb the code.
         try {
            account = address.substring(0, indexSeparator);
            checkSum = address.substring(indexSeparator + 1, address.length());
         } catch (StringIndexOutOfBoundsException e) {
            return AddressValidationResult.invalidAddress(ERROR_INVALID_CHECKSUM_STRUCTURE);
         }
         int accountInt = 0;
         int checkSumInt = 0;
         try {
            accountInt = Integer.valueOf(account);
         } catch (NumberFormatException e) {
            return AddressValidationResult.invalidAddress(ERROR_ONLY_NUMERICAL);
         }
         if (checkSum.length() != 2) {
            return AddressValidationResult.invalidAddress(ERROR_CHECKSUM_SIZE);
         } else {
            try {
               checkSumInt = Integer.valueOf(checkSum);
            } catch (NumberFormatException e) {
               return AddressValidationResult.invalidAddress(ERROR_INVALID_CHECKSUM_STRUCTURE);
            }
         }
         //if you reach this stage. we have a correctly formed pasa with checksum
         //now check validity
         int computedChecksum = calculateChecksum(accountInt);
         if (computedChecksum == checkSumInt) {
            // check if not too big
            return validateInteger(accountInt);
         } else {
            return AddressValidationResult.invalidAddress(ERROR_INVALID_CHECKSUM);
         }
      }
   }

   /**
   * Currently hardcoded with an upper bound.
   * @return
   */
   private int getCurrentMaxAccountNumber() {
      return maxAccountNumber;
   }

   /**
   * Pascal accounts start at 0 and increase up to last valid PASA mined by miners.
   * @param accountInt
   * @return
   */
   private AddressValidationResult validateInteger(int accountInt) {
      if (accountInt > getCurrentMaxAccountNumber() || accountInt < 0) {
         return AddressValidationResult.invalidAddress(ERROR_INVALID_RANGE);
      } else {
         return AddressValidationResult.validAddress(accountInt);
      }
   }

   /**
   * Compute the checksum of a Pascal account.
   * Example: 3532-30. If you input 3532, method returns 30.
   * @param account
   * @return
   */
   public Integer calculateChecksum(Integer account) {
      return (((account) * 101) % 89) + 10;
   }
}
