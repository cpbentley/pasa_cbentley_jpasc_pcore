
package pasa.cbentley.jpasc.pcore.rpc.exception;

public class RPCException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   //("code")
   Integer                   code;

   //("message")
   String                    message;

   public RPCException(Throwable t,String message) {
      super(t);
      this.message = message;
   }

   public RPCException(Integer code, String message) {
      super(message);
      this.message = message;
      this.code = code;
   }

   public Integer getCode() {
      return code;
   }

   public void setCode(Integer code) {
      this.code = code;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

}
