package com.put.gamma.Element;

import com.put.gamma.visitor.Visitor;

public interface Element {
    public void accept(Visitor visitor);
}
