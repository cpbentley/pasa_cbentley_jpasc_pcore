package pasa.cbentley.jpasc.pcore.rpc.model;

public enum AccountState {

   NORMAL("normal"), COINSWAP("coin_swap"),ACCOUNTSWAP("account_swap"), LISTED("listed");

   private final String value;

   public String getValue() {
      return value;
   }

   private AccountState(String value) {
      this.value = value;
   }
}
