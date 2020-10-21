/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.ctx;

import java.util.Date;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.Block;
import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.utils.PascalUtils;

public class PCoreDebug {

   //#mdebug
   
   protected final PCoreCtx pc;

   public PCoreDebug(PCoreCtx pc) {
      this.pc = pc;
   }

   public String dAccounts(List<Account> e) {
      Dctx dc = new Dctx(pc.getUCtx());
      d(e, dc);
      return dc.toString();
   }

   public void d(List<Account> e, Dctx dc) {
      dc.append("size=" + e.size());
      for (Account ac : e) {
         dc.nl();
         d1(ac, dc);
      }
   }

   public String d1(Account acc) {
      Dctx dc = new Dctx(pc.getUCtx());
      d1(acc, dc);
      return dc.toString();
   }

   public void d1(Account acc, Dctx dc) {
      dc.root1Line(acc, "Account");
      dc.append(' ');
      dc.append(acc.getAccount());
      dc.append('\t');
      dc.append(acc.getBalance());
      dc.append('\t');
      dc.append(acc.getUpdatedB());
      dc.append('\t');
      dc.append(acc.getName());
      dc.append('\t');
      dc.append(acc.getPrice());
   }

   public void d1(Operation op, Dctx dc) {
      dc.root1Line(op, "Operation");
      dc.append(' ');
      dc.append(op.getBlock());
      dc.append('#');
      dc.append(op.getOperationBlock());
      dc.append('\t');
      dc.append(op.getNOperation());
      dc.append('\t');
      long unixTime = op.getTime();
      Date timeDate = new Date(unixTime * 1000);
      dc.append(pc.getFormatDateTime().format(timeDate));
      dc.append('\t');
      dc.append(op.getAmount());
      dc.appendVarWithSpace("account", op.getAccount());
      dc.append('\t');
      dc.append(PascalUtils.getOperationTypeUserString(op.getType()));
      dc.append(' ');
      dc.append(PascalUtils.getOperationSubTypeUserString(op.getSubType()));

   }

   public void d1(PublicKeyJava pk, Dctx dc) {
      dc.root1Line(pk, "PublicKeyJava");
      dc.append(" #");
      dc.append(pk.getNumAccounts());
      dc.append(' ');
      dc.append(pk.getName());
      dc.append('\t');
      dc.append(pk.getCanUse());
      dc.append('\t');
      dc.append(pk.getBase58PubKey());
      dc.append('\t');
      dc.append(pk.getEncPubKey());
   }

   public void d1(PublicKey pk, Dctx dc) {
      dc.root1Line(pk, "PublicKey");
      dc.append(' ');
      dc.append(pk.getName());
      dc.append('\t');
      dc.append(pk.getCanUse());
      dc.append('\t');
      dc.append(pk.getBase58PubKey());
      dc.append('\t');
   }

   public void d1(Block b, Dctx dc) {
      dc.root1Line(b, "Block");
      dc.append(' ');
      dc.append(b.getBlock());
      dc.append(' ');
      dc.append("#ops=" + b.getOperationCount());
      long unixTime = b.getTimestamp();
      Date timeDate = new Date(unixTime * 1000);
      dc.append("\t" + pc.getFormatDateTime().format(timeDate));
      dc.append(" fee=" + b.getFee());
      dc.append(" " + b.getPayload());
   }

   public void toStringListT(Dctx dc, List list, String title) {
      if (list == null) {
         dc.append("List " + title + " is null");
      } else {
         dc.append(title);
         dc.appendVarWithSpace("size", list.size());
         dc.tab();
         int counter = 0;
         for (Object is : list) {
            dc.append("#");
            dc.append(counter);
            dc.append("\t");
            if (is instanceof IStringable) {
               dc.nlLvl1Line((IStringable) is);
            } else if (is instanceof Account) {
               d1((Account) is, dc);
            } else {
               dc.append(is.toString());
            }
            counter++;
         }
         dc.tabRemove();
      }
   }

   public String dPublicKeyJavas(List<PublicKeyJava> ops) {
      Dctx dc = new Dctx(pc.getUCtx());
      dPublicKeyJavas(ops, dc);
      return dc.toString();
   }

   public String dPublicKeys(List<PublicKey> ops) {
      Dctx dc = new Dctx(pc.getUCtx());
      dPublicKeys(ops, dc);
      return dc.toString();
   }

   public void dPublicKeyJavas(List<PublicKeyJava> e, Dctx dc) {
      dc.append("size=" + e.size());
      for (PublicKeyJava ac : e) {
         dc.nl();
         d1(ac, dc);
      }
   }

   public void dPublicKeys(List<PublicKey> e, Dctx dc) {
      dc.append("size=" + e.size());
      for (PublicKey ac : e) {
         dc.nl();
         d1(ac, dc);
      }
   }

   public String dBlocks(List<Block> ops) {
      Dctx dc = new Dctx(pc.getUCtx());
      dBlocks(ops, dc);
      return dc.toString();
   }

   public void dBlocks(List<Block> e, Dctx dc) {
      dc.append("size=" + e.size());
      for (Block ac : e) {
         dc.nl();
         d1(ac, dc);
      }
   }

   public String dOperations(List<Operation> ops) {
      Dctx dc = new Dctx(pc.getUCtx());
      dOperations(ops, dc);
      return dc.toString();
   }

   public void dOperations(List<Operation> e, Dctx dc) {
      dc.append("size=" + e.size());
      for (Operation ac : e) {
         dc.nl();
         d1(ac, dc);
      }
   }
   
   //#enddebug
}
