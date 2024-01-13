package com.put.gamma.visitor;

import com.put.gamma.Element.Element;
import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;
/**
 * This class implements Visitor interface and is suited for displaying scenarios.
 */
public class VisitorShowScenario implements Visitor{
    public List<Element> elements=new ArrayList<Element>();
    /**
     * This argument determines the maximum depth of displayed sections.
     */
    public int depth;
    public VisitorShowScenario(int depth){
        this.depth = depth;
    }
    /**
     * This method visits the scenario and makes this visitor
     * let into all of its sections.
     * @param scenario - object of Scenario type
     */
    @Override
    public void visit(Scenario scenario) {
        scenario.accept(this,0);
    }
    /**
     * This method visits the section and accepts it with depth parameter, which
     * depending on its value it will let it or not into its subsections.
     * @param section - object of Section type
     */
    @Override
    public void visit(Section section) {
        if(section.depth<=this.depth||this.depth==0){
            System.out.println("Depth "+section.depth+": "+section.content);
        }
        section.accept(this,this.depth);
    }
}
