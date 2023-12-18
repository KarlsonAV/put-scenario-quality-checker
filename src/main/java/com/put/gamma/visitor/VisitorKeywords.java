package com.put.gamma.visitor;

import com.put.gamma.Element.Element;
import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;
/**
 * This class implements Visitor interface and is suited for
 * collectin the keywords from a given secnario and
 * counting all sections within the given scenario that begin with a keyword.
 */
public class VisitorKeywords implements Visitor{
    public List<Element> elements=new ArrayList<Element>();
    public List<String> keywords = new ArrayList<String>();
    int result=0;
    /**
     * This method visits the scenario and makes this visitor
     * let into all of its sections. It collects the keywords from
     * the scenario given
     * @param scenario - object of Scenario type
     */
    @Override
    public void visit(Scenario scenario) {
        keywords=scenario.keywords;
        scenario.accept(this);
    }

    /**
     * This method visits the section and makes this visitor
     * let into all of its subsections. It checks whether the section
     * begins with a keyword, and if yes, increments the result.
     * @param section - object of Section type
     */
    @Override
    public void visit(Section section) {
        section.accept(this);
        if(section.checkIfBeginsWithKeyword(keywords)==true){
            result++;
        }
    }
    public int getResult(){
        return result;
    }
}
