/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

public class AddressValidationResult {

   private static AddressValidationResult VALID_ADDRESS = new AddressValidationResult(true, "", "");

   private final boolean                  isValid;

   private final String                   message;

   private final String                   i18nKey;

   private Integer                        account;

   private AddressValidationResult(boolean isValid, String message, String i18nKey) {
      this.isValid = isValid;
      this.message = message;
      this.i18nKey = i18nKey;
   }

   private AddressValidationResult(boolean isValid, Integer account) {
      this.isValid = isValid;
      this.account = account;
      this.message = null;
      this.i18nKey = null;
   }

   public boolean isValid() {
      return isValid;
   }

   public Integer getAccount() {
      return account;
   }

   public String getI18nKey() {
      return i18nKey;
   }

   public String getMessage() {
      return message;
   }

   public static AddressValidationResult validAddress(Integer account) {
      return new AddressValidationResult(true, account);
   }

   public static AddressValidationResult validAddress() {
      return VALID_ADDRESS;
   }

   public static AddressValidationResult invalidAddress(Throwable cause) {
      return invalidAddress(cause.getMessage());
   }

   public static AddressValidationResult invalidAddress(String cause) {
      return invalidAddress(cause, "validation.altcoin.invalidAddress");
   }

   public static AddressValidationResult invalidAddress(String cause, String i18nKey) {
      return new AddressValidationResult(false, cause, i18nKey);
   }

   public static AddressValidationResult invalidStructure() {
      return invalidAddress("", "validation.altcoin.wrongStructure");
   }

}
