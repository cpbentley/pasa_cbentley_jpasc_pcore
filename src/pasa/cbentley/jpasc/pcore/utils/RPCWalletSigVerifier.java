/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.utils;

import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.interfaces.ISignVerifier;
import pasa.cbentley.jpasc.pcore.rpc.model.SignResult;

public class RPCWalletSigVerifier implements ISignVerifier {

   private PCoreCtx pc;

   public RPCWalletSigVerifier(PCoreCtx pc) {
      this.pc = pc;

   }

   public boolean verifySign(String digest, String encPubKey, String signature) {
      try {
         SignResult result = pc.getPClient().verifySign(digest, encPubKey, signature);
         if (result != null && result.getSignature() != null) {
            return result.getSignature().equals(signature);
         } else {
            return false;
         }
      } catch (Exception e) {
         return false;
      }
   }

   public String signMessage(String digest, String encPubKey, String b58PubKey) {
      SignResult result = pc.getPClient().signMessage(digest, encPubKey, b58PubKey);
      return result.getSignature();
   }

}
