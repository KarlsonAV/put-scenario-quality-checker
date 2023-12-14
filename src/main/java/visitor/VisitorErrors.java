package visitor;

import scenario.Scenario;
import section.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorErrors implements Visitor{
    List<String> errors= new ArrayList<>();
    List<String> allActors = new ArrayList<>();
    List<String> keywords = new ArrayList<String>();
    @Override
    public void visit(Scenario scenario){
        allActors.addAll(scenario.getSystemActors());
        allActors.addAll(scenario.getActors());
        keywords = scenario.keywords;
        scenario.accept(this);
    }
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
