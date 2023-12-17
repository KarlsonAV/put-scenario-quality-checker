package com.put.gamma.visitor;

import com.put.gamma.Element.Element;
import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;

public interface Visitor {
    List<Element> elements=new ArrayList<Element>();
    void visit(Scenario scenario);
    void visit(Section section);
}
