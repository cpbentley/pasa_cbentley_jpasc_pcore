<pre>
  PIP: *reserved for PIP editor*
  Title: Block Time Miner Reward Accumulator
  Type: Protocol
  Impact: Hard-Fork - Protcol
  Author: Charles Bentley <i>&lt;cbentley@mail.ru&gt;</i>
  Comments-URI: *reserved for PIP editor*
  Status: Draft
  Created: 2019-07-27
</pre>

## Summary

Propose a new mining reward distribution scheme.
 
## Motivation

Fixes the issue of big miners disconnecting to play the DAA, letting other miners mine a difficult chain
and coming back once the difficulty is down.

## Specification

The idea is the introduce time in the equation. Many before have proposed the idea. The idea is to reduce 
the block reward when a block is found faster.

The question remains on how to redistribute the extra reward?

What do we want actually? We want the extra rewards to go to the miners who mine long high difficulty blocks. And we want
to discourage mining strategies that mine fast blocks.

The solution must not touch the block timing goals as it would destroy the timing clock of the chain.

The solution proposed here only adapts the reward.

The new consensus introduces a Mining Reward Accumulator. Its a object at the start of the Safebox.

It has a double field for reward and fees.
It has a Fifo queue of Accounts.

Every block adds the block reward, the accounts and the fees to the Accumulator.

The safebox awards the miner a reward that is proportional to the time deviation. 
Let the block time difference be the miner published timestamp difference.
If block time difference is 5 minutes, the accumulator pays a full reward.
If block time difference is 2.5 minutes, the accumulator pays a half a reward
If block time difference is 6 or more minutes, the accumulator pays the full reward and percentage
of the accumulated reward based on the time difference.

The Dev reward is distributed separately.

How could we use the Reward accumulator to incite miners for including operations?

TODO.

 
## Rationale

CPU mining is a mercenary industry. Miners don't have allegiance to a given chain. They can easily switch their CPUs to the most profitable chain. This is especially true for big farms who will follow the biggest ROI.


## Backwards Compatibility

This change is not backwards compatible and requires a hard-fork activation.
 
## Reference Implementation

The reference implementation must be provided before PIP is Completed.
 
## Links

References and links to relevant material