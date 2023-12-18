package com.put.gamma.visitor;

import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;
/**
 * This class implements Visitor interface and is suited for counting
 * all sections within the given scenario.
 */
public class VisitorCountSections implements Visitor{
    int sectionCount = 0;
    /**
     * This method visits the scenario and makes this visitor
     * let into all of its sections
     * @param scenario - object of Scenario type
     */
    @Override
    public void visit(Scenario scenario){
        scenario.accept(this);
    }
    /**
     * This method visits the section given and makes this visitor
     * let into all of its subsections
     * @param section - object of Section type
     */
    @Override
    public void visit(Section section){
        section.accept(this);
        sectionCount++;
    }
    public int getSectionCount(){
        return sectionCount;
    }
}
