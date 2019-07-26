package pasa.cbentley.jpasc.pcore.ctx;

import pasa.cbentley.core.src4.ctx.IEventsCore;

/**
 * Events for {@link PCoreCtx} module.
 * 
 * @author Charles Bentley
 *
 */
public interface IEventsPCore extends IEventsCore {

   /**
    * Reserved
    * Events will of instances must register a dynamic PID
    * outside the static range.
    */
   public static final int PID_0_ANY                    = 0;

   /**
    * 
    */
   public static final int PID_1_BLOCK_OPS              = 1;

   public static final int EID_1_BLOCK_OPS_0_ANY        = 0;

   /**
    * Event generated when a new blocks comes in with at least one pasa being
    * put to sale, or bought
    */
   public static final int EID_1_BLOCK_OPS_1_PASA_TRADE = 1;

   
   /**
    * Pid only valid on {@link PCoreCtx#getEventBusPCore()}
    */
   public static final int PID_2_PENDING_OPS              = 1;

   /**
    * Generated when any pending op is received
    */
   public static final int EID_2_PENDING_0_ANY        = 0;

   /**
    * Generated when a pending pasa trade is received
    */
   public static final int EID_2_PENDING_1_PASA_TRADE        = 1;

}
