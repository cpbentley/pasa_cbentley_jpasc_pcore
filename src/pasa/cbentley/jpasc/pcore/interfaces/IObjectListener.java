/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.interfaces;

import pasa.cbentley.jpasc.pcore.filter.predicates.PPredicate;

public interface IObjectListener<T> {

   public void newObjectAvailable(T object, PPredicate predicate);
   
   public void newObjectfail(T object, PPredicate predicate);
}
