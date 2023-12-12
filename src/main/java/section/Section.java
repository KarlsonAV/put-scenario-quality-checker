/*
Every section is one command (line) of our scenario. Every section can contain list of subsections (if it has any).
Parent is the parent Section of this actual Section (Sections without indentation don't contain any parent).
Indentation shows how deep this step is.
 */
package section;

import java.util.ArrayList;
import java.util.List;

public class Section {
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
     * This method returns true if the section content begins with an actor name or system actor name.
     */
    public boolean checkIfBeginsWithActorName(List<String> actors, List<String> systemActors){
        for(String actor: actors){
            if(this.content.trim().startsWith(actor)){
                return true;
            }
        }
        for(String actor: systemActors){
            if(this.content.trim().startsWith(actor)){
                return true;
            }
        }
        return false;
    }
}