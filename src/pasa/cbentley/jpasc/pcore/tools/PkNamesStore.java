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
import java.util.Set;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;

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
   private Map<String, String> mapKeyToNameAutomatic;

   /**
    * Maps encoded public key to names. 
    * 
    * Global list that is used by all table models when they want to 
    * match the public key to a user readable name.
    * <br>
    * Those names are not controlled by the pascal reference wallet.
    */
   private Map<String, String> mapKeyToNameUserCustom;

   /**
    * At the start of the wallet, the reference wallet public keys and their names are added to this map.
    * 
    * This is done upon connection
    */
   private Map<String, String> mapKeyToNameWallet;

   /**
    * 
    */
   protected final PCoreCtx    pc;

   private int                 version = 1;

   /**
    * Creates a store for the given {@link PCoreCtx} and its {@link RPCConnection}.
    * 
    * Upon connection, {@link PCoreCtx} and {@link KeyNameProvider} will synchronize the stores.
    * 
    * A {@link PkNamesStore} is not linked to a specific connection.
    * 
    * @param pcx
    */
   public PkNamesStore(PCoreCtx pc, String fileName) {
      this.pc = pc;
      mapKeyToNameWallet = new HashMap<String, String>();
      mapKeyToNameUserCustom = new HashMap<String, String>();
      mapKeyToNameAutomatic = new HashMap<String, String>();

      this.fileName = fileName;
   }

   public String addAutoName(String pub) {
      int ownerCount = mapKeyToNameAutomatic.size() + 1;
      String name = "key#" + String.valueOf(ownerCount);
      mapKeyToNameAutomatic.put(pub, name);
      return name;
   }

   /**
    * Called by the exit and save commands 
    */
   public void cmdExitSave() {
      fileWrite();
   }

   private void fileRead() {
      File f = getFileSettings();

      //on first load, file won't exist
      if(!f.exists()) {
         return;
      }
      
      //#debug
      toDLog().pInit(pc.getC5().toStringFile(f, "state"), this, PkNamesStore.class, "fileRead", LVL_05_FINE, true);

      ObjectInputStream ois = null;
      try {
         FileInputStream fis = new FileInputStream(f);
         ois = new ObjectInputStream(fis);
         version = ois.readInt();
         Object obj = ois.readObject();
         if (obj instanceof Map) {
            mapKeyToNameUserCustom = (Map) obj;
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

   private void fileWrite() {
      File f = getFileSettings();

      //#debug
      toDLog().pInit("Saving to " + f.getAbsolutePath(), this, PkNamesStore.class, "fileWrite", LVL_05_FINE, false);

      ObjectOutputStream oos = null;
      try {
         FileOutputStream fos = new FileOutputStream(f);
         oos = new ObjectOutputStream(fos);
         oos.writeInt(1); //version number
         oos.writeObject(mapKeyToNameUserCustom);
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

   public String getFileName() {
      return fileName;
   }

   /**
    * Makes sure that its exists
    * @return
    */
   public File getFileSettings() {
      File directoryPath = new File(pc.getSettingsPath());
      if(!directoryPath.exists()) {
         boolean success = directoryPath.mkdirs();
         if(!success) {
            pc.getUCtx().getUserLog().consoleLogDateRed("Could not create settings directory "+ directoryPath.getAbsolutePath());
         }
      }
      File f = new File(directoryPath, fileName);
      return f;
   }

   public boolean hasWalletKey(String encPubKey) {
      return mapKeyToNameWallet.containsKey(encPubKey);
   }

   /**
    * 
    * @param pub
    * @return null if none
    */
   public String getKeyName(String pub) {
      String name = mapKeyToNameWallet.get(pub);
      if (name == null) {
         name = mapKeyToNameUserCustom.get(pub);
         if (name == null) {
            name = mapKeyToNameAutomatic.get(pub);
         }
      }
      return name;
   }
   
   public boolean isPrivateWallet(String pub) {
      return mapKeyToNameWallet.containsKey(pub);
   }

   public Set<String> getKeys() {
      return mapKeyToNameUserCustom.keySet();
   }

   public void populateKeys(Set<String> set) {
      Set<String> keys = mapKeyToNameUserCustom.keySet();
      for (String key : keys) {
         set.add(key);
      }
   }

   /**
    * 
    * @param pub
    * @return never null
    */
   public String getKeyNameAdd(String pub) {
      String name = mapKeyToNameWallet.get(pub);
      if (name == null) {
         name = mapKeyToNameUserCustom.get(pub);
         if (name == null) {
            name = mapKeyToNameAutomatic.get(pub);
            if (name == null) {
               //add one
               name = addAutoName(pub);
            }
         }
      }
      return name;
   }

   /**
    * The map of encoded keys to names
    * @return
    */
   public Map<String, String> getMappingChain() {
      return mapKeyToNameAutomatic;
   }

   /**
    * 
    * @return
    */
   public Map<String, String> getMappingWallet() {
      return mapKeyToNameUserCustom;
   }

   public int getVersion() {
      return version;
   }

   /**
    * Reads data from file saved on a previously saved instance
    */
   @SuppressWarnings("unchecked")
   public void initialize() {
      fileRead();
   }

   /**
    * Called 
    * @param encodedPk
    * @param name
    */
   public void setPkName(String encodedPk, String name) {
      //#debug
      toDLog().pFlow("name=" + name + " - " + encodedPk, this, PkNamesStore.class, "setPkName", LVL_05_FINE, true);
      mapKeyToNameUserCustom.put(encodedPk, name);
   }

   /**
    * Called when a mapping from reference wallet has been found
    * @param walletMapping
    */
   public void setWalletMapping(Map<String, String> walletMapping) {
      mapKeyToNameWallet = walletMapping;
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
      dc.appendVarWithSpace("version", version);
      dc.appendVarWithSpace("fileName", fileName);
      pc.getC5().toStringHashMapStringString(dc.nLevel(), mapKeyToNameWallet, "Wallet Key Names", false);
      pc.getC5().toStringHashMapStringString(dc.nLevel(), mapKeyToNameUserCustom, "User Custom Names", false);
      pc.getC5().toStringHashMapStringString(dc.nLevel(), mapKeyToNameAutomatic, "Automatic Generated Names", false);

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
