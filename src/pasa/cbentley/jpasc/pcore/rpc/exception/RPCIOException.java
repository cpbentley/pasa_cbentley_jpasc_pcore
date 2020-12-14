package pasa.cbentley.jpasc.pcore.rpc.exception;

import java.io.IOException;

/**
 * Wrapper around an {@link IOException} when doing RPC calls
 * 
 * @author Charles Bentley
 *
 */
public class RPCIOException extends RuntimeException {

   private IOException ioe;

   public RPCIOException(IOException ioe) {
      super(ioe);
      this.ioe = ioe;
      
   }

   public IOException getIOException() {
      return ioe;
   }
}
