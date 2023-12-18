/**
 * Every section is one command (line) of our scenario. Every section can contain list of subsections (if it has any).
 * Parent is the parent Section of this actual Section (Sections without indentation don't contain any parent).
 * Indentation shows how deep this step is.
 */
package com.put.gamma.section;

import com.put.gamma.Element.Element;
import com.put.gamma.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
/**
 * Every section is one command (line) of our scenario. Every section can contain list of subsections (if it has any).
 * Parent is the parent Section of this actual Section (Sections without indentation don't contain any parent).
 * Indentation shows how deep this step is.
 */
public class Section implements Element {
    public int depth;
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
     * @param content a String
     * @return String trimmed without a keyword
     */
    private String ignoreKeywordTrim(String content){
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
     * @param allActors list of all actors, both normal and system ones.
     * @return a boolean statement
     */
    public boolean checkIfBeginsWithActorName(List<String> allActors){
        for(String actor: allActors){
            if(ignoreKeywordTrim(this.content).trim().startsWith(actor)){
                return true;
            }
        }
        return false;
    }
    /**
     * This method returns true if this section's content begins with a keyword.
     * @param keywords list of all keywords
     * @return a boolean statement
     */
    public boolean checkIfBeginsWithKeyword(List<String> keywords){
        for (String keyword: keywords
        ) {
            if(this.content.trim().startsWith(keyword)){
                return true;
            }
        }
        return false;
    }
    /**
     * This method accepts visitors into the object.
     * @param visitor - an object of type inherited from visitor interface
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.elements.add(this);
        //System.out.println(this.depth+" "+this.content);
        for (Section subsection: subsections
        ) {
            visitor.visit(subsection);
        }
    }
    /**
     * This method accepts a visitor into this element without needing to visit its subsections.
     * @param visitor - an object of type inherited from visitor interface
     */
    public void acceptOnlyHere(Visitor visitor){
        visitor.elements.add(this);
    }
}