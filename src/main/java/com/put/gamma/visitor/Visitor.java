package com.put.gamma.visitor;

import com.put.gamma.Element.Element;
import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;
/**
 * This interface declares visit method for each type of objects visited.
 * It collects them all into elements list.
 */
public interface Visitor {
    List<Element> elements=new ArrayList<Element>();
    /**
     * This method visits the object given.
     * @param scenario - object of Scenario type
     */
    void visit(Scenario scenario);
    /**
     * This method visits the object given.
     * @param section - object of Section type
     */
    void visit(Section section);
}
