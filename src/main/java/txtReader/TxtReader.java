/*
This class reads the file under filePath and places it into the scenario.
Every other section (deeper indentation) is placed in list of higher section
 */

package txtReader;

import scenario.Scenario;
import section.Section;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TxtReader {
    private File file;
    public Scenario scenario;

    public TxtReader(File file, Scenario scenario) {
        // Check if the file has a .txt extension
        if (!file.getName().toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException("The provided file is not a .txt file.");
        }
        this.file = file;
        this.scenario = scenario;
    }

    public void readFile() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Section currentSection = new Section("Pusty");
            currentSection.indentation = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (line.startsWith("Tytuł:")) {
                    // Extract title information
                    scenario.title = line.substring("Tytuł:".length()).trim();
                } else if (line.startsWith("Aktorzy:")) {
                    // Extract actors information
                    String actorsLine = line.substring("Aktorzy:".length()).trim();
                    scenario.actors = List.of(actorsLine.split(","));
                } else if (line.startsWith("Aktor systemowy:")) {
                    // Extract system actors information
                    String systemActorsLine = line.substring("Aktor systemowy:".length()).trim();
                    scenario.system_actors = List.of(systemActorsLine.split(","));
                } else {
                    // Create a new section for each indented line
                    int indentation = getIndentation(line);
                    Section newSection = new Section(line.trim());
                    newSection.indentation = indentation;
                    // newSection.content = line;

                    // If the new line has deeper indentation, add it as a subsection
                    if (indentation > currentSection.indentation) {
                        newSection.parent = currentSection;
                        currentSection.subsections.add(newSection);
                    } else if (indentation == 0){
                        scenario.sections.add(newSection);
                    } else {
                        // Move to the parent section based on indentation
                        while (indentation <= currentSection.indentation) {
                            currentSection = currentSection.parent;
                        }
                        // Add the new section as a subsection to the current section
                        newSection.parent = currentSection;
                        currentSection.subsections.add(newSection);
                    }

                    // Update the current section to the newly added subsection
                    currentSection = newSection;
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int getIndentation(String line) {
        // Calculate the number of leading whitespaces to determine indentation
        int indentation = 0;
        while (indentation < line.length() && Character.isWhitespace(line.charAt(indentation))) {
            indentation++;
        }
        return indentation;
    }

    public static void main(String[] args) {
        // Example file
        File file = new File("scenariusz.txt");

        // Sceanario class that will be filled with data
        Scenario scenariusz = new Scenario();

        //TxtReader class that needs a file and scenario where
        TxtReader fileReader = new TxtReader(file, scenariusz);
        fileReader.readFile();
        
        //Example
        System.out.println("Tytuł: " + scenariusz.title);
        System.out.println("Aktorzy: " + scenariusz.actors);
        System.out.println("Aktorzy systemowi: " + scenariusz.system_actors);
        System.out.println(scenariusz.sections.get(3).subsections.get(2).subsections.get(0).content);
    }
}