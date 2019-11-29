/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.ctx;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.AccountState;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.ctx.ACtx;
import pasa.cbentley.core.src4.ctx.ICtx;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.event.EventBusArray;
import pasa.cbentley.core.src4.event.IEventBus;
import pasa.cbentley.core.src4.helpers.BasicPrefs;
import pasa.cbentley.core.src4.interfaces.IPrefs;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.logging.IUserLog;
import pasa.cbentley.core.src4.thread.WorkerThread;
import pasa.cbentley.core.src5.bundle.Bundler;
import pasa.cbentley.core.src5.bundle.CombinedResourceBundle;
import pasa.cbentley.core.src5.bundle.UTF8Control;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.core.src5.ctx.ITechPrefsC5;
import pasa.cbentley.jpasc.pcore.access.AccessPascalPrivate;
import pasa.cbentley.jpasc.pcore.dboletbridge.IPascalCoinClient;
import pasa.cbentley.jpasc.pcore.domain.DomainMapper;
import pasa.cbentley.jpasc.pcore.domain.PublicKeyJavaManager;
import pasa.cbentley.jpasc.pcore.domain.bo.AccountBO;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccount;
import pasa.cbentley.jpasc.pcore.domain.ipc.IPCAccountMutable;
import pasa.cbentley.jpasc.pcore.domain.java.AccountJava;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.domain.tech.ITechAccount;
import pasa.cbentley.jpasc.pcore.interfaces.IAccessPascal;
import pasa.cbentley.jpasc.pcore.interfaces.IBOPascalChain;
import pasa.cbentley.jpasc.pcore.network.RPCConnection;
import pasa.cbentley.jpasc.pcore.ping.PingLogger;
import pasa.cbentley.jpasc.pcore.ping.PingParams;
import pasa.cbentley.jpasc.pcore.safebox.BOPascalChainFirstImpl;
import pasa.cbentley.jpasc.pcore.services.PasaServices;
import pasa.cbentley.jpasc.pcore.tools.KeyNameProvider;
import pasa.cbentley.jpasc.pcore.utils.PASCAddressValidation;
import pasa.cbentley.jpasc.pcore.utils.PascalCoinDouble;
import pasa.cbentley.jpasc.pcore.utils.PascalCoinValue;
import pasa.cbentley.jpasc.pcore.utils.PascalUtils;
import pasa.cbentley.jpasc.pcore.utils.PublicKeyJavaCache;

/**
 * All the core services not related to the UI.
 * Services that would be needed even in a non GUI/Swing interface.
 * <li> Thread.
 * <li>
 * 
 * <br>
 * What if you have several {@link RPCConnection} in the same application?
 * Each wanting its own Log
 * 
 * Java Implementation are pure vanilla java
 * Those classes don't have links to 3rd party or annotations. The fields, getters and setters
 * have been renamed, some classes provide extra data fields such as {@link PublicKeyJava#getNumAccounts()}.
 * <li> {@link Account} -> {@link AccountJava}
 * <li> {@link PublicKey} -> {@link PublicKeyJava}
 * 
 * It is also the goal of this implementation to provide a safebox in memory 
 * 
 * We use the {@link ByteObject} framework.
 * 
 * <li> {@link AccountBO} -> {@link AccountJava}
 * 
 * 
 * Javacore introduces the IPC (InterfacePascalCoin) abstraction
 * <li> {@link IPCAccount}
 * <li> {@link IPCAccountMutable}
 * 
 * All account types by pasa.cbentley implements {@link IPCAccount} InterfacePascalCoinAccount
 * 
 * Bridge with JPascalCoin from David Bolet
 * 
 * UI application that use JPascalCoin can 
 * 
 * {@link IDataAccess}. It abstract the source of the account data
 * 
 * 
 * {@link PCoreCtx#getRPCConnection()} returns the {@link RPCConnection} of this context.
 * bijective relation?
 * 
 * @author Charles Bentley
 *
 */
public class PCoreCtx extends ACtx implements IStringable, ICtx {

   private HashMap<Integer, String> accountToKey;

   private PASCAddressValidation    addressValidation;

   private ExecutorService          backgroundExec;

   protected final BOCtx            boc;

   private IBOPascalChain           boPascalChain;

   protected final C5Ctx            c5;

   private SimpleDateFormat         df;

   private DomainMapper             domainMapper;

   private EventBusArray            eventBusPCore;

   private HashMap<String, Integer> mapNamesToAccount;

   private PasaServices             pasaService;

   private AccessPascalPrivate      pascalAccessPrivate;

   private String                   payloadEncoding   = "UTF-8";

   private PCoreDebug               pd;

   private PingParams               pingParams;

   private IPrefs                   prefsUser;

   private PascalCoinValue          psvZero;

   private PascalUtils              pu;

   private PublicKeyJavaCache       publicKeyJavaCache;

   private PublicKeyJavaManager     publicKeyJavaManager;

   private RPCConnection            rpcConnection;

   private String                   settingsPathCustom;

   private DecimalFormat            pascalCoinsFormat = new DecimalFormat("#.####");

   private PascalCoinDouble         ZERO;

   private CoreOperations coreOps;

   private WorkerThread workerThread;

   public DecimalFormat getPascalCoinsFormat() {
      return pascalCoinsFormat;
   }

   /**
    * You must explicitely try to connect after creating this instance.
    * 
    * Dummy {@link RPCConnection} is active until then.
    * @param uc
    * @param c5
    */
   public PCoreCtx(UCtx uc, C5Ctx c5) {
      super(uc);
      this.c5 = c5;
      ZERO = new PascalCoinDouble(this, 0, 0);
      boc = new BOCtx(uc);
      backgroundExec = Executors.newCachedThreadPool();
      psvZero = new PascalCoinValue(this, BigDecimal.ZERO);
      rpcConnection = new RPCConnection(this);
      addressValidation = new PASCAddressValidation();
      pingParams = new PingParams();
      pu = new PascalUtils(this);
      //base structure of events
      int[] events = new int[5];
      eventBusPCore = new EventBusArray(uc, this, events);

      coreOps = new CoreOperations(this);
      
      //#debug
      pd = new PCoreDebug(this);
   }

   public void addI18NKey(List<String> list) {
      list.add("i18nPascalJavaCore");
   }

   /**
   * It this state subject to change or should it be static/inlined?
   * @param account
   * @return
   */
   public Integer calculateChecksum(Integer account) {
      return (((account) * 101) % 89) + 10;
   }

   public PascalCoinValue create(Double d) {
      return new PascalCoinValue(this, d);
   }

   public void cmdExit() {
      //make sure db of pk names is saved
      if (keyNameProvider != null) {
         keyNameProvider.cmdSave();
      }
   }

   public PascalCoinDouble getZERO() {
      return ZERO;
   }

   /**
    * Pascal access only to private accounts.. blocks will be live from RPC
    * @return
    */
   public IAccessPascal getAccessPascalPrivate() {
      if (pascalAccessPrivate == null) {
         pascalAccessPrivate = new AccessPascalPrivate(this);
      }
      return pascalAccessPrivate;
   }

   public PascalCoinDouble getAccountBalance(Account ac) {
      return new PascalCoinDouble(this, ac.getBalance());
   }

   public AccountJava getAccountJavaFrom(Account ac) {
      AccountJava accountJava = new AccountJava(this);
      accountJava.setAccount(ac.getAccount());
      accountJava.setBalance(getAccountBalance(ac));
      accountJava.setEncPubkey(ac.getNewEncPubkey());
      accountJava.setName(ac.getName());
      accountJava.setLockedUntilBlock(ac.getLockedUntilBlock());
      accountJava.setNOperation(ac.getnOperation());
      accountJava.setLastUpdatedBlock(ac.getUpdatedB());
      accountJava.setType(ac.getType());

      AccountState state = ac.getState();
      if (state != null) {
         String stateStr = state.getValue();
         if (stateStr.equals(AccountState.LISTED.getValue())) {
            accountJava.setState(ITechAccount.ACCOUNT_STATE_1_LISTED);
         }
      }
      //sell data
      accountJava.setSalePrivateNewEncPubkey(ac.getNewEncPubkey());
      accountJava.setSalePrice(getAccountPrice(ac));
      accountJava.setSaleSellerAccount(ac.getSellerAccount());
      accountJava.setSaleIsPrivate(ac.getPrivateSale());
      return accountJava;
   }

   public PascalCoinDouble getAccountPrice(Account ac) {
      return new PascalCoinDouble(this, ac.getPrice());
   }

   public PASCAddressValidation getAddressValidator() {
      return addressValidation;
   }

   /**
    * {@link ByteObject} context used by Pascal Core.
    * 
    * @return
    */
   public BOCtx getBOC() {
      return boc;
   }

   public IBOPascalChain getBOPascalChain() {
      if (boPascalChain == null) {
         //initialize in its own thread by reading
         BOPascalChainFirstImpl boPascalChain = new BOPascalChainFirstImpl(this);
         boPascalChain.syncWith(getPClient());
         this.boPascalChain = boPascalChain;
      }
      return boPascalChain;
   }

   public C5Ctx getC5() {
      return c5;
   }

   /**
    * Build it if needed by scanning the blockchain.
    * Maps is updated by operations seen on the chain
    * @return
    */
   public HashMap<String, Integer> getCurrentNames() {
      mapNamesToAccount = new HashMap<String, Integer>();
      return mapNamesToAccount;
   }

   public int getDecimalSize() {
      return 4;
   }

   public Integer getDefaultPageSizeRoot() {
      return 10;
   }

   public Integer getDefaultPageSizeRootAccount() {
      return 100;
   }

   public Integer getDefaultPageSizeRootAccountOperations() {
      return 100;
   }

   public Integer getDefaultPageSizeRootBlock() {
      return 50;
   }

   public Integer getDefaultPageSizeRootBlockOperations() {
      return 100;
   }

   public Integer getDefaultPageSizeRootPublicKey() {
      return 50;
   }

   public DomainMapper getDomainMapper() {
      if (domainMapper == null) {
         domainMapper = new DomainMapper(this);
      }
      return domainMapper;
   }

   public IEventBus getEventBusPCore() {
      return eventBusPCore;
   }

   /**
    * Returns the Utility executor for non GUI tasks.
    * @return
    */
   public ExecutorService getExecutorService() {
      return backgroundExec;
   }

   /**
    * The thread used for queue operations when you want seriality
    * over a given set of operations
    * @return
    */
   public WorkerThread getWorkerThreadOperation() {
      if(workerThread == null) {
         workerThread = new WorkerThread(uc);
      }
      return workerThread;
   }
   /**
    * "YYYY/MM/dd - HH:mm:ss" for formating timestamps
    * @return
    */
   public DateFormat getFormatDateTime() {
      if (df == null) {
         df = new SimpleDateFormat("YYYY/MM/dd - HH:mm:ss");
      }
      return df;
   }

   /**
    * Cannot return null
    * @return
    */
   public IUserLog getLog() {
      return uc.getUserLog();
   }

   /**
    * Mapping between Account and encoded public key
    * @return
    */
   public HashMap<Integer, String> getMapWalletAccountToKey() {
      if (accountToKey == null) {
         accountToKey = new HashMap<Integer, String>();
         List<PublicKey> walletPubKeys = getRPCConnection().getPClient().getWalletPubKeys(0, 100);
         for (PublicKey key : walletPubKeys) {
            String name = key.getName();
            List<Account> walletAccounts = getRPCConnection().getPClient().getWalletAccounts(key.getEncPubKey(), null, 0, 5000);
            for (Account ac : walletAccounts) {
               accountToKey.put(ac.getAccount(), name);
            }
         }
      }
      return accountToKey;
   }

   /**
    * TODO make it configurable in 
    * @return
    */
   public int getPagerExceptionCount() {
      return 10;
   }

   public PasaServices getPasaServices() {
      if (pasaService == null) {
         pasaService = new PasaServices(this);
      }
      return pasaService;
   }

   public PascalCoinValue getPascValue(String val) {
      if (val == null || val.equals("")) {
         return getZero();
      }
      Double num = Double.valueOf(val);
      return new PascalCoinValue(this, num.doubleValue());
   }

   public String getPayloadEncoding() {
      return payloadEncoding;
   }

   /**
    * Never null. Returns a thread local object
    * @return
    */
   public IPascalCoinClient getPClient() {
      return rpcConnection.getPClient();
   }

   public PingParams getPingParams() {
      return pingParams;
   }

   private KeyNameProvider keyNameProvider;

   private PingLogger      pingerLogger;

   private Bundler         bundler;

   public KeyNameProvider getKeyNameProvider() {
      if (keyNameProvider == null) {
         keyNameProvider = new KeyNameProvider(this);
         keyNameProvider.cmdInitialize();
      }
      return keyNameProvider;
   }

   public void setKeyNameProvider(KeyNameProvider keyNameProvider) {
      if (keyNameProvider == null) {
         throw new NullPointerException();
      }
      this.keyNameProvider = keyNameProvider;
   }

   public IPrefs getPrefs() {
      if (prefsUser == null) {
         prefsUser = new BasicPrefs(uc);
         //warning 
         uc.getUserLog().consoleLogError("Warning: PCore User Preferences file not initialized.");
      }
      return prefsUser;
   }

   public Bundler getBundler() {
      if (bundler == null) {
         bundler = new Bundler(c5, getPrefs());

         List<String> bundleNames = new ArrayList<String>(1);
         this.addI18NKey(bundleNames);
         bundler.setBundleList(bundleNames);
         String language = "en";
         String country = "US";

         country = getPrefs().get(ITechPrefsC5.PREF_LOCALE_COUNTRY, "US");
         language = getPrefs().get(ITechPrefsC5.PREF_LOCALE_LANG, "en");
         Locale currentLocale = new Locale(language, country);
         bundler.setLocale(currentLocale);
      }
      return bundler;
   }

   public PascalUtils getPU() {
      return pu;
   }

   public int getLastValidAccount() {
      int block = getRPCConnection().getLastBlockMinedValue();
      int account = (block * 5) - 1;
      return account;
   }

   public PublicKeyJavaCache getPublicKeyJavaCache() {
      if (publicKeyJavaCache == null) {
         publicKeyJavaCache = new PublicKeyJavaCache(this);
      }
      return publicKeyJavaCache;
   }

   public PublicKeyJavaManager getPublicKeyJavaManager() {
      if (publicKeyJavaManager == null) {
         publicKeyJavaManager = new PublicKeyJavaManager(this);
      }
      return publicKeyJavaManager;
   }

   /**
    * 
    * @param string
    * @return
    */
   public String getResString(String string) {
      return getBundler().getResString(string);
   }

   /**
    * Never null.
    * When program starts, a dummy connection object is created
    * @return
    */
   public RPCConnection getRPCConnection() {
      return rpcConnection;
   }

   public void setRPCConnection(RPCConnection rpcConnection) {
      if (rpcConnection == null) {
         //#debug
         throw new NullPointerException();
      }
      this.rpcConnection = rpcConnection;
      startPingLogger();
   }

   public void startPingLogger() {
      pingerLogger = new PingLogger(this);
   }

   /**
    * The default path is home.dir
    * 
    * If the user has setup a specific path with {@link PCoreCtx#setSettingsPathCustom(String)}
    * 
    * This value is read?
    * 
    * @return
    */
   public String getSettingsPath() {
      if (settingsPathCustom == null) {
         String path = System.getProperty("user.home");
         path += System.getProperty("file.separator");
         path += ".pasa_cbentley_pcore";
         settingsPathCustom = path;
      }
      return settingsPathCustom;
   }

   public String getSettingsPathCustom() {
      return settingsPathCustom;
   }

   public UCtx getUCtx() {
      return uc;
   }

   /**
    * null if not owned by a wallet key
    * @param account
    * @return
    */
   public String getWalletKeyOwner(Integer account) {
      return getMapWalletAccountToKey().get(account);
   }

   public PascalCoinValue getZero() {
      return psvZero;
   }

   public void setPingParams(PingParams pingParams) {
      this.pingParams = pingParams;
   }

   public void setPrefs(IPrefs prefs) {
      this.prefsUser = prefs;
   }

   public void setSettingsPathCustom(String settingsPathCustom) {
      this.settingsPathCustom = settingsPathCustom;
   }

   //#mdebug
   public IDLog toDLog() {
      return uc.toDLog();
   }

   public PCoreDebug toPD() {
      return pd;
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "PCoreCtx");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "PCoreCtx");
   }

   public UCtx toStringGetUCtx() {
      return uc;
   }
   //#enddebug

   public CoreOperations getOps() {
      return coreOps;
   }

}
