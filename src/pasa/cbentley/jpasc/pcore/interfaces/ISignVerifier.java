/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;


public interface ISignVerifier {

   /**
    * 
    * @param hexDigest hex encoded String data
    * @param encPubKey 
    * @param signature
    * @return
    */
   public boolean verifySign(String hexDigest, String encPubKey, String signature);

   /**
    * 
    * @param hexDigest hex encoded String data. 
    * @param encPubKey
    * @param b58PubKey
    * @return
    */
   public String signMessage(String hexDigest, String encPubKey, String b58PubKey);
}
