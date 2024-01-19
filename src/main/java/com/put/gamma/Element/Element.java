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
     * @param depth - depth parameter that allows visitors visit objects until certain depth
     */
    public void accept(Visitor visitor, int depth);
}
