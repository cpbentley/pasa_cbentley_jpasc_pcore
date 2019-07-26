<pre>
  PIP: *reserved for PIP editor*
  Title: Block Time Reward Accumulator
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
What do we want actually? We want the extra rewards to go to the miners who mine long high difficulty blocks.

The solution must not touch the block timing goals as it would destroy the timing clock of the chain.

The solution proposed here only adapts the reward.

The new consensus introduces a Reward Accumulator. Its a simple object at the start of the Safebox.
It has a double field for reward and fees.
It has a Fifo queue of Accounts.

We can also reward 

Every block founds add the block reward, the accounts and the fees to the Accumulator.

Reward accumulator also have the power to reward miners for including operations?

Naive solution. 

When a miner finds a valid hash. He creates a block and puts the timestamp in it. And continue mining on it.


When the timestamp is put into the block, the miner pays the previous block miner his due.
So the miner 

When a block is found by a miner, The Accumulator pays the miners

 
## Rationale

CPU mining is a mercenary industry. Miners don't have allegiance to a given chain. They can easily switch their CPUs to the most profitable chain. This is especially true for big farms who will follow the biggest ROI.


Discussion why was the specification was chosen over alternate designs. Evidence supporting the specification should be provided here, as well as community concerns and consensus.

## Backwards Compatibility

This change is not backwards compatible and requires a hard-fork activation.
 
## Reference Implementation

The reference implementation must be provided before PIP is Completed.
 
## Links

References and links to relevant material