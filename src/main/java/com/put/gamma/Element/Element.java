package com.put.gamma.Element;

import com.put.gamma.visitor.Visitor;
/**
 * This interface is an element of the document structure.
 *
 */
public interface Element {
    /**
     * This method accepts visitors into the object.
     * @param visitor - an object of type inherited from visitor interface
     */
    public void accept(Visitor visitor);
}
