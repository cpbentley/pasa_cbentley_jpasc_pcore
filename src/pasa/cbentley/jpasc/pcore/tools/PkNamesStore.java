/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Tracks some people
 * 
 * 591357
 * 
 * the following is useful for more temporary file storage
 * System.getProperty("java.io.tmpdir")
 * 
 * @author Charles Bentley
 *
 */
public class PkNamesStore implements IStringable {

   /**
    * When the wallet sees a pub key that is not in name or wallet, it adds it to ownerIDs with an generated name.
    * <br>
    * 
    * we don't want to store IDs
    */
   private Map<String, String> ownerIDs    = new HashMap<String, String>();

   /**
    * Maps encoded PK to names. Set by
    * Its a global list that is used by all table models when they want to 
    * match the public key to a user readable name.
    * <br>
    * Names that are not controlled by the pascal deamon
    */
   private Map<String, String> ownerNames  = new HashMap<String, String>();

   /**
    * At the start of the wallet, the wallet keys are inserted here
    * Built upon connection
    */
   private Map<String, String> ownerWallet = new HashMap<String, String>();

   private PCoreCtx      pc;

   public PkNamesStore(PCoreCtx pcx) {
      this.pc = pcx;
      
      //TODO upon connection we have to update, so we are linked to a connexion ?
      
      //TODO Offline work?
   }

   /**
    * 
    * @param pub
    * @return
    */
   public String getKeyName(String pub) {
      String name = ownerWallet.get(pub);
      if (name == null) {
         name = ownerNames.get(pub);
         if (name == null) {
            name = ownerIDs.get(pub);
            if (name == null) {
               //add one
               int ownerCount = ownerIDs.size() + 1;
               name = String.valueOf(ownerCount);
               ownerIDs.put(pub, name);
            }
         }
      }
      return name;
   }

   public void initialize() {
      //load names
      String path = System.getProperty("user.dir");
      File f = new File(path, "pascjavaswingstate.state");
      ObjectInputStream ois = null;
      try {
         FileInputStream fis = new FileInputStream(f);
         ois = new ObjectInputStream(fis);
         int version = ois.readInt();
         ownerNames = (Map<String, String>) ois.readObject();
         ois.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } finally {
         try {
            ois.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public void exit() {
      String path = System.getProperty("user.dir");
      File f = new File(path, "pascjavaswingstate.state");
      ObjectOutputStream oos = null;
      try {
         FileOutputStream fos = new FileOutputStream(f);
         oos = new ObjectOutputStream(fos);
         oos.writeInt(0); //version number
         oos.writeObject(ownerNames);
         oos.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            oos.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public void setWalletMapping(Map<String, String> walletMapping) {
      ownerNames = walletMapping;
   }

   public void setPkName(String encodedPk, String name) {
      ownerNames.put(encodedPk, name);
   }

   public Map<String, String> getWalletMapping() {
      return ownerNames;
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PkNamesStore");
      dc.append("Wallet Key Names");
      dc.tab();
      for (String key : ownerWallet.keySet()) {
         String encodedKey = ownerWallet.get(key);
         dc.appendVarWithSpace(key, encodedKey);
         dc.nl();
      }
      
      dc.append("Tools Custom Names");
      dc.tab();
      for (String key : ownerNames.keySet()) {
         String encodedKey = ownerNames.get(key);
         dc.appendVarWithSpace(key, encodedKey);
         dc.nl();
      }
      //TODO use dc data flag to switch off this often bloated information
      dc.append("Tool Forced Names");
      dc.tab();
      for (String key : ownerIDs.keySet()) {
         String encodedKey = ownerIDs.get(key);
         dc.appendVarWithSpace(key, encodedKey);
         dc.nl();
      }
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PkNamesStore");
   }
   //#enddebug

}
