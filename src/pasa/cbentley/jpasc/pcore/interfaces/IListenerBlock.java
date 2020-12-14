/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import pasa.cbentley.jpasc.pcore.rpc.model.Block;

public interface IListenerBlock {

   public void listenTo(Block block);
}
