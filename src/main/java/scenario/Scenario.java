/*
Class scenario that contains title, actors, system actors and list of main sections.
To fill the scenario scenarioTxtReader must be used.
 */

package scenario;
import Element.Element;
import section.Section;
import txtReader.TxtReader;
import visitor.Visitor;
import visitor.VisitorKeywords;

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
}