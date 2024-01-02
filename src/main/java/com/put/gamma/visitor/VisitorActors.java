package com.put.gamma.visitor;

import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;
/**
 * This class implements Visitor interface and is suited for checking
 * the actors and system actors, and checking if sections begin with
 * an actor name.
 */
public class VisitorActors implements Visitor {

    List<String> allActors = new ArrayList<>();
    boolean doesStartWithActors=false;
    /**
     * This method visits the scenario and is only let into just the scenario object.
     * @param scenario - object of Scenario type
     */
    @Override
    public void visit(Scenario scenario) {
        allActors.addAll(scenario.getSystemActors());
        allActors.addAll(scenario.getActors());
        scenario.accept(this,-1);
    }
    /**
     * This method visits the section and is only let into one section at a time.
     * @param section - object of Section type
     */
    @Override
    public void visit(Section section) {
        section.accept(this,-1);
        doesStartWithActors=section.checkIfBeginsWithActorName(allActors);
    }
    public boolean getDoesStartWithActors(){
        return doesStartWithActors;
    }
}
