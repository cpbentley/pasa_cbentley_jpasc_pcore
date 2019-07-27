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
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

/**
 * Class that associates public key to readable names chosen by the user
 * 
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

   private String              fileName;

   /**
    * When the wallet sees a pub key that is not known.
    * 
    * we don't want to store IDs
    */
   private Map<String, String> mapKeyToChainID;

   /**
    * Maps encoded public key to names. 
    * 
    * Global list that is used by all table models when they want to 
    * match the public key to a user readable name.
    * <br>
    * Those names are not controlled by the pascal reference wallet.
    */
   private Map<String, String> mapKeyToNameChain;

   /**
    * At the start of the wallet, the reference wallet public keys and their names are added to this map.
    * 
    * This is done upon connection
    */
   private Map<String, String> mapKeyToNameWallet;

   private int                 version;

   /**
    * 
    */
   protected final PCoreCtx    pc;

   /**
    * 
    * @param pcx
    */
   public PkNamesStore(PCoreCtx pcx) {
      this.pc = pcx;
      mapKeyToNameWallet = new HashMap<String, String>();
      mapKeyToNameChain = new HashMap<String, String>();
      mapKeyToChainID = new HashMap<String, String>();
      //TODO upon connection we have to update, so we are linked to a connexion ?

      //TODO Offline work?
   }

   /**
    * Called by the exit and save commands 
    */
   public void cmdExitSave() {
      //#debug
      toDLog().pInit("Saving", this, PkNamesStore.class, "cmdExitSave", LVL_05_FINE, false);

      File f = getFileSettings();
      ObjectOutputStream oos = null;
      try {
         FileOutputStream fos = new FileOutputStream(f);
         oos = new ObjectOutputStream(fos);
         oos.writeInt(1); //version number
         oos.writeObject(mapKeyToNameChain);
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

   public int getVersion() {
      return version;
   }

   public String getFileName() {
      return fileName;
   }

   public File getFileSettings() {
      File f = new File(pc.getSettingsPath(), fileName);
      return f;
   }

   /**
    * 
    * @param pub
    * @return
    */
   public String getKeyName(String pub) {
      String name = mapKeyToNameWallet.get(pub);
      if (name == null) {
         name = mapKeyToNameChain.get(pub);
         if (name == null) {
            name = mapKeyToChainID.get(pub);
            if (name == null) {
               //add one
               int ownerCount = mapKeyToChainID.size() + 1;
               name = String.valueOf(ownerCount);
               mapKeyToChainID.put(pub, name);
            }
         }
      }
      return name;
   }

   public Map<String, String> getWalletMapping() {
      return mapKeyToNameChain;
   }

   @SuppressWarnings("unchecked")
   public void initialize() {

      //#debug
      toDLog().pInit("msg", this, PkNamesStore.class, "initialize", LVL_05_FINE, true);

      File f = getFileSettings();
      ObjectInputStream ois = null;
      try {
         FileInputStream fis = new FileInputStream(f);
         ois = new ObjectInputStream(fis);
         version = ois.readInt();
         Object obj = ois.readObject();
         if (obj instanceof Map) {
            mapKeyToNameChain = (Map) obj;
         }
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

   /**
    * Called 
    * @param encodedPk
    * @param name
    */
   public void setPkName(String encodedPk, String name) {
      mapKeyToNameChain.put(encodedPk, name);
   }

   /**
    * Called when a mapping from reference wallet has been found
    * @param walletMapping
    */
   public void setWalletMapping(Map<String, String> walletMapping) {
      mapKeyToNameChain = walletMapping;
   }

   //#mdebug
   public IDLog toDLog() {
      return pc.toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PkNamesStore");
      dc.append("Wallet Key Names");
      dc.tab();
      for (String key : mapKeyToNameWallet.keySet()) {
         String encodedKey = mapKeyToNameWallet.get(key);
         dc.appendVarWithSpace(key, encodedKey);
         dc.nl();
      }

      dc.append("Tools Custom Names");
      dc.tab();
      for (String key : mapKeyToNameChain.keySet()) {
         String encodedKey = mapKeyToNameChain.get(key);
         dc.appendVarWithSpace(key, encodedKey);
         dc.nl();
      }
      //TODO use dc data flag to switch off this often bloated information
      dc.append("Tool Forced Names");
      dc.tab();
      for (String key : mapKeyToChainID.keySet()) {
         String encodedKey = mapKeyToChainID.get(key);
         dc.appendVarWithSpace(key, encodedKey);
         dc.nl();
      }
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PkNamesStore");
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }
   //#enddebug

}
