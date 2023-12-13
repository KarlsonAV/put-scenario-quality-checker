package visitor;

import scenario.Scenario;
import section.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorErrors implements Visitor{
    List<String> errors= new ArrayList<>();
    List<String> allActors = new ArrayList<>();
    @Override
    public void visit(Scenario scenario){
        allActors.addAll(scenario.getSystemActors());
        allActors.addAll(scenario.getActors());
        scenario.accept(this);
    }
    @Override
    public void visit(Section section){
        if(!section.checkIfBeginsWithActorName(allActors)){
            errors.add(section.content);
        }
        section.accept(this);
    }
    public List<String>getErrors(){
        return errors;
    }
}
