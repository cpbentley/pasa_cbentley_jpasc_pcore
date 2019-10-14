/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.listlisteners;

import java.util.List;

import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.jpasc.pcore.domain.operations.OperationJavaAbstract;

public interface IListenerOperation extends IStringable {

   public void operationFinished(OperationJavaAbstract task);
}
