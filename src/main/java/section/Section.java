/*
Every section is one command (line) of our scenario. Every section can contain list of subsections (if it has any).
Parent is the parent Section of this actual Section (Sections without indentation don't contain any parent).
Indentation shows how deep this step is.
 */
package section;

import Element.Element;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
    public String content;
    public int indentation;
    public List<Section> subsections = new ArrayList<Section>();
    public Section parent;

    public Section(String content) {
        this.content = content;
    }

    public Section(String content, Section parent) {
        this.content = content;
        this.parent = parent;
    }

    /**
     * This method trims the given string so it will ignore all characters before colon.
     */
    public String ignoreKeywordTrim(String content){
        int colonIndex = content.indexOf(":");
        if (colonIndex != -1) {
            String trimmedString = content.substring(colonIndex + 1).trim();
            trimmedString = trimmedString.replaceAll("\\s+", "");
            return trimmedString;
        } else {

            return content;
        }
    }
    /**
     * This method returns true if the section content begins with an actor name or system actor name,
     * all of which are contained in allActors list.
     * This method is called in Scenario class.
     */
    public boolean checkIfBeginsWithActorName(List<String> allActors){
        for(String actor: allActors){
            if(ignoreKeywordTrim(this.content).startsWith(actor)){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfBeginsWithKeyword(List<String> keywords){
        for (String keyword: keywords
        ) {
            if(this.content.startsWith(keyword)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.elements.add(this);
        for (Section subsection: subsections
        ) {
            visitor.visit(subsection);
        }
    }
    /**
     * This method accepts a visitor into this element without needing to visit its subsections.
     */
    public void acceptOnlyHere(Visitor visitor){
        visitor.elements.add(this);
    }
}