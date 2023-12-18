package com.put.gamma.visitor;

import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;
/**
 * This class implements Visitor interface and is suited for collecting
 * all actors and keywords, and checking whether any sections contain
 * errors.
 */
public class VisitorErrors implements Visitor{
    List<String> errors= new ArrayList<>();
    List<String> allActors = new ArrayList<>();
    List<String> keywords = new ArrayList<String>();
    /**
     * This method visits the scenario and makes this visitor
     * let into all of its sections. It collects the actors and keywords
     * into this object.
     * @param scenario - object of Scenario type
     */
    @Override
    public void visit(Scenario scenario){
        allActors.addAll(scenario.getSystemActors());
        allActors.addAll(scenario.getActors());
        keywords = scenario.keywords;
        scenario.accept(this);
    }
    /**
     * This method visits the section given and makes this visitor
     * let into all of its subsections. It checks if the section begins
     * with a keyword, and if not, it checks if the section doesn't begin
     * with an actor name, and if it does, adds the section's content to
     * errors list.
     * @param section - object of Section type
     */
    @Override
    public void visit(Section section){
        if(!section.checkIfBeginsWithKeyword(this.keywords)){
            if(!section.checkIfBeginsWithActorName(this.allActors)){
                errors.add(section.content);
            }
        }
        section.accept(this);
    }
    public List<String>getErrors(){
        return errors;
    }
}
