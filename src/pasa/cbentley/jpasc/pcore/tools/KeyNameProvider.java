package pasa.cbentley.jpasc.pcore.tools;

import java.util.HashSet;
import java.util.Set;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;

public class KeyNameProvider implements IStringable {

   private boolean          isPrivateMode = true;

   private PkNamesStore     namesStorePrivate;

   private PkNamesStore     namesStorePublic;

   protected final PCoreCtx pc;

   public KeyNameProvider(PCoreCtx pc) {
      this.pc = pc;
      namesStorePublic = new PkNamesStore(pc, "jpasc_nameskey_public.state");
      namesStorePrivate = new PkNamesStore(pc, "jpasc_nameskey_private.state");
   }

   public KeyNameProvider(PCoreCtx pc, String fileNamePrefix) {
      this.pc = pc;
      namesStorePublic = new PkNamesStore(pc, fileNamePrefix + "_public.state");
      namesStorePrivate = new PkNamesStore(pc, fileNamePrefix + "_private.state");
   }

   public void cmdSave() {
      namesStorePrivate.cmdExitSave();
      namesStorePublic.cmdExitSave();
   }
   
   /**
    * List of keys with a custom user set name
    * @return
    */
   public Set<String> getEncodedKeysCustom() {
      Set<String> keys = new HashSet<String>(100);
      namesStorePrivate.populateKeys(keys);
      namesStorePublic.populateKeys(keys);
      return keys;
   }

   public void cmdInitialize() {
      namesStorePrivate.initialize();
      namesStorePublic.initialize();
   }

   /**
    * Service that provides a name for the pubKey.
    * 
    * When private mode, it will read the private key store first
    * and if none is found, query the public store.
    * 
    * In Public mode, it will only query the public store and add
    * an automatic name if none is found
    * 
    * @param pubKey
    * @return the name will not be null
    */
   public String getKeyName(String pubKey) {
      if (isPrivateMode) {
         String name = namesStorePrivate.getKeyName(pubKey);
         if (name == null) {
            name = namesStorePublic.getKeyNameAdd(pubKey);
         }
         return name;
      } else {
         return namesStorePublic.getKeyNameAdd(pubKey);
      }
   }

   public PkNamesStore getPkNameStorePrivate() {
      return namesStorePrivate;
   }

   /**
    * 
    * @return
    */
   public PkNamesStore getPkNameStorePublic() {
      return namesStorePublic;
   }

   /**
    * True by default. Must be set with {@link KeyNameProvider#setPrivateMode(boolean)}
    * 
    * @return
    */
   public boolean isPrivateMode() {
      return isPrivateMode;
   }

   public void setPkName(String encodedPk, String name) {
      setPkName(encodedPk, name, isPrivateMode);
   }

   public void setPkName(String encodedPk, String name, boolean isPrivateMode) {
      if (isPrivateMode) {
         namesStorePrivate.setPkName(encodedPk, name);
      } else {
         namesStorePublic.setPkName(encodedPk, name);
      }
   }

   public void setPrivate() {
      this.isPrivateMode = true;
   }

   public void setPrivateMode(boolean isPrivateMode) {
      this.isPrivateMode = isPrivateMode;
   }

   public void setPublic() {
      this.isPrivateMode = false;
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "KeyNameProvider");
      toStringPrivate(dc);

      dc.nlLvl("PrivateStore", namesStorePrivate);
      dc.nlLvl("PublicStore", namesStorePublic);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "KeyNameProvider");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return pc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("isPrivateMode", isPrivateMode);
   }

   //#enddebug

}
