/*
Class scenario that contains title, actors, system actors and list of main sections.
To fill the scenario scenarioTxtReader must be used.
 */

package scenario;
import Element.Element;
import section.Section;
import txtReader.TxtReader;
import visitor.Visitor;
import visitor.VisitorCountSections;
import visitor.VisitorKeywords;
import visitor.VisitorActors;

import java.util.ArrayList;
import java.util.List;

public class Scenario implements Element {

    public String title;
    public List<String> actors = new ArrayList<String>();
    public List<String> systemActors = new ArrayList<String>();
    public List<Section> sections = new ArrayList<Section>();
    public List<String> keywords = new ArrayList<String>();
    public TxtReader scenarioTextReader;

    public Scenario() {
        // Initialize clueWords in the constructor
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
    /**This method returns the number of all sections in the given file.
     * It works recursively (DFS) by traversing the graph-like structure of
     * Scenario.sections and Section.subsections. When it reaches the leaf node
     * it adds one to the section count.
     */
    public int countAllSections() {
        int sectionCount = 0;
        VisitorCountSections countSectionsVisitor = new VisitorCountSections();
        countSectionsVisitor.visit(this);
        sectionCount = countSectionsVisitor.getSectionCount();
        return sectionCount;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.elements.add(this);
        for (Section section: sections
             ) {
            visitor.visit(section);
        }
    }

    public int countStepsWithKeywords(){
        int result = 0;
        VisitorKeywords countKeywordsVisitor = new VisitorKeywords();
        countKeywordsVisitor.visit(this);
        result=countKeywordsVisitor.getResult();
        return result;
    }
    /**
     * This method accepts a visitor into this element without needing to visit its subsections.
     */
    public void acceptOnlyHere(Visitor visitor){
        visitor.elements.add(this);
    }

    /**
     * This method returns if the given section begins with an actor name.
     */
    public boolean checkIfBeginsWithActorName(Section section){
        VisitorActors checkActorNameVisitor = new VisitorActors();
        checkActorNameVisitor.visit(this);
        checkActorNameVisitor.visit(section);
        return checkActorNameVisitor.getDoesStartWithActors();
    }
}