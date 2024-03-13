/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.domain.java;

import java.io.UnsupportedEncodingException;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.domain.operations.OperationJavaAbstract;

/**
 * Used by UI to create a payload.
 * 
 * This class is then set to an {@link OperationJavaAbstract}
 * 
 * @author Charles Bentley
 *
 */
public class PayloadJava implements IStringable {

   private String           password;

   private byte[]           payload;

   private int              payloadEncryption;

   protected final PCoreCtx pc;

   private String           stringData;

   public PayloadJava(PCoreCtx pc) {
      this.pc = pc;

   }

   public String getPassword() {
      return password;
   }

   public byte[] getPayload() {
      return payload;
   }

   public int getPayloadEncryption() {
      return payloadEncryption;
   }

   public String getStringData() {
      return stringData;
   }

   /**
    * Sets the payload directly
    * @param data
    */
   public void setByteData(byte[] data) {
      this.payload = data;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setPayloadEncryption(int payloadEncryption) {
      this.payloadEncryption = payloadEncryption;
   }

   /**
    * Sets the payload data by encoding the string to a byte array using
    * {@link PCoreCtx#getPayloadEncoding()} 
    * @param stringData
    * @throws UnsupportedEncodingException If custom encoding used is not supported. 
    */
   public void setStringData(String stringData) throws UnsupportedEncodingException {
      //should never happen since UTF-8 is always supported
      byte[] data = stringData.getBytes(pc.getPayloadEncoding());
      this.payload = data;
      this.stringData = stringData;
   }
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PayloadJava");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PayloadJava");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUC();
   }

   //#enddebug
   

}
