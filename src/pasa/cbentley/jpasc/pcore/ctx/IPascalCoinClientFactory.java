package pasa.cbentley.jpasc.pcore.ctx;

import pasa.cbentley.jpasc.pcore.client.IPascalCoinClient;

/**
 * 
 * @author Charles Bentley
 *
 */
public interface IPascalCoinClientFactory {

   public IPascalCoinClient createClient(PCoreCtx pc, String address, int port);
}
