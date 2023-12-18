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
     * This method accepts visitors into the object.
     * @param visitor - an object of type inherited from visitor interface
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.elements.add(this);
        for (Section section: sections
             ) {
            visitor.visit(section);
        }
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
     * This method accepts a visitor into this element without needing to visit its subsections.
     * @param visitor - an object of type inherited from visitor interface
     */
    public void acceptOnlyHere(Visitor visitor){
        visitor.elements.add(this);
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
}