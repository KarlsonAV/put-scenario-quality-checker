/*
Class scenario that contains title, actors, system actors and list of main sections.
To fill the scenario scenarioTxtReader must be used.
 */

package scenario;
import section.Section;
import txtReader.TxtReader;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    public String title;
    public List<String> actors = new ArrayList<String>();
    public List<String> systemActors = new ArrayList<String>();
    public List<Section> sections = new ArrayList<Section>();
    List<String> keywords = new ArrayList<String>();
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
    public void printActors(){
        for(String actor : this.actors){
            System.out.println(actor);
        }
    }
    /**This method returns the number of all sections in the given file.
     * It works recursively by traversing the graph-like structure of
     * Scenario.sections and Section.subsections. When it reaches the leaf node
     * it adds one to the section count.
     */
    public int countAllSections(List<Section> sections){
        int sectionCount = 0;
        for(Section section : sections){
            if(section.subsections.size()==0){
                sectionCount+=1;
            }
            else{
                sectionCount+=countAllSections(section.subsections);
            }
        }
        return sectionCount;
    }
}