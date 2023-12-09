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
}