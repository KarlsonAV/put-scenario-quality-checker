package com.put.gamma.scenario;
import com.put.gamma.Element.Element;
import com.put.gamma.txtReader.TxtReader;
import com.put.gamma.visitor.*;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;
/**
 * Class scenario that contains title, actors, system actors and list of main sections.
 * To fill the scenario scenarioTxtReader must be used.
 */
public class Scenario implements Element {

    public String title;
    public List<String> actors = new ArrayList<String>();
    public List<String> systemActors = new ArrayList<String>();
    public List<Section> sections = new ArrayList<Section>();
    public List<String> keywords = new ArrayList<String>();
    public TxtReader scenarioTextReader;

    public Scenario() {
        // Initialize keyWords in the constructor
        keywords.add("IF");
        keywords.add("ELSE");
        keywords.add("FOR EACH");
    }

    public List<String> getKeywords(){
        return keywords;
    }

    public List<String> getSystemActors(){
        return this.systemActors;
    }
    public List<String> getActors(){
        return this.actors;
    }
    public void setSystemActors(List<String> systemActors){this.systemActors=systemActors;}
    public void setActors(List<String> actors){this.actors=actors;}
    /**
     * This method asks the visitor to return the number of all sections in the given file.
     * @return the count of all sections within the scenario.
     */
    public int countAllSections() {
        int sectionCount = 0;
        VisitorCountSections countSectionsVisitor = new VisitorCountSections();
        countSectionsVisitor.visit(this);
        sectionCount = countSectionsVisitor.getSectionCount();
        return sectionCount;
    }

    /**
     * This method asks the visitor to count all sections that contain keywords
     * @return number of results
     */
    public int countSectionsWithKeywords(){
        int result = 0;
        VisitorKeywords countKeywordsVisitor = new VisitorKeywords();
        countKeywordsVisitor.visit(this);
        result=countKeywordsVisitor.getResult();
        return result;
    }


    /**
     * This method asks the visitor to check if a certain section starts with an actor name.
     * @param section - an object of type Section that we check for the condition
     */
    public boolean checkIfBeginsWithActorName(Section section){
        VisitorActors checkActorNameVisitor = new VisitorActors();
        checkActorNameVisitor.visit(this);
        checkActorNameVisitor.visit(section);
        return checkActorNameVisitor.getDoesStartWithActors();
    }

    /**
     * This method asks the visitor to find all sections that contain errors.
     * @return list of sections with errors
     */
    public List<String> findSectionsWithErrors(){
        VisitorErrors findErrors = new VisitorErrors();
        findErrors.visit(this);
        return findErrors.getErrors();
    }

    /**
     * This function displays sections with errors.
     */
    public void displayErrorSections(){
        //List<String> sectionsWithErrors = findSectionsWithErrors();
        System.out.println("Sections conataining errors:");
        for(String content : findSectionsWithErrors()){
            System.out.println("line: "+content);
        }
    }

    /**
     * This function displays the scenario up to depth given.
     *
     * @param depth - depth determines how deep the visitor will visit the scenario. 0 will visit the entire scenario.
     */
    public void displayScenarioUpToDepth(int depth){
        System.out.println("Sections up to depth "+depth+":");
        VisitorShowScenario scenarioUpToDepth = new VisitorShowScenario(depth);
        scenarioUpToDepth.visit(this);
    }

    /**
     * This method initiates the enumeration process for the entire scenario.
     * It creates an instance of VisitorEnumerate, accepts the visitor on the scenario,
     * and returns the enumerated scenario as a string.
     * @return the enumerated scenario as a string
     */
    public String enumerateScenario() {
        VisitorEnumerate visitor = new VisitorEnumerate();
        accept(visitor, 0);  // Accepting the visitor with depth 0 to start the enumeration
        return visitor.getEnumeratedScenario();
    }

    /**
     * Counts the number of main steps in the scenario and provides feedback.
     * @return a string indicating the scenario quality based on the main steps count
     */
    public String checkMainSteps() {
        VisitorMainSteps mainStepsVisitor = new VisitorMainSteps();
        accept(mainStepsVisitor, 0);  // Accepting the visitor with depth 0 to count main steps
        int mainStepsCount = mainStepsVisitor.getMainStepsCount();

        if (mainStepsCount < 3) {
            return "Za mało kroków";
        } else if (mainStepsCount > 9) {
            return "Za dużo kroków";
        } else {
            return "Dobry scenariusz";
        }
    }


    /**
     * This method accepts visitors into the object.
     * @param visitor - an object of type inherited from visitor interface
     * @param depth - integer that will let in the visitor to a certain depth. If is -1 then accepts just into this object. If 0, lets in to all of the sections.
     */
    @Override
    public void accept(Visitor visitor, int depth){
        visitor.elements.add(this);
        if(depth == -1 || depth != 0){
            return;
        }
        for (Section section: sections
        ) {
            visitor.visit(section);
        }
    }

}