package com.put.gamma.visitor;

import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorActors implements Visitor {

    List<String> allActors = new ArrayList<>();
    boolean doesStartWithActors=false;
    @Override
    public void visit(Scenario scenario) {
        allActors.addAll(scenario.getSystemActors());
        allActors.addAll(scenario.getActors());
        scenario.acceptOnlyHere(this);
    }
    @Override
    public void visit(Section section) {
        section.acceptOnlyHere(this);
        doesStartWithActors=section.checkIfBeginsWithActorName(allActors);
    }
    public boolean getDoesStartWithActors(){
        return doesStartWithActors;
    }
}
