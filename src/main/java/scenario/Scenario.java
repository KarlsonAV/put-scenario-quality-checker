/*
Class scenario that contains title, actors, system actors and list of main sections.
To fill the scenario txtReader with its argument must be used.
 */

package scenario;
import section.Section;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    public String title;
    public List<String> actors = new ArrayList<String>();
    public List<String> system_actors = new ArrayList<String>();
    public List<Section> sections = new ArrayList<Section>();
    List<String> clueWords = new ArrayList<String>();

    public Scenario() {
        // Initialize clueWords in the constructor
        clueWords.add("IF");
        clueWords.add("ELSE");
        clueWords.add("FOR EACH");
    }
}