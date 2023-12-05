/*
This class reads the multipartFile and places it into the scenario.
Every other section (deeper indentation) is placed in list of higher section.
Arguments: multipartFile (from Postman API), scenario (scenario where later we want to put file conent to)
 */

package txtReader;

import org.springframework.web.multipart.MultipartFile;
import scenario.Scenario;
import section.Section;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TxtReader {
    private MultipartFile multipartFile;
    public Scenario scenario;

    public TxtReader(MultipartFile multipartFile, Scenario scenario) {
        // Check if the file has a .txt extension
        if (!multipartFile.getOriginalFilename().toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException("The provided file is not a .txt file.");
        }
        this.multipartFile = multipartFile;
        this.scenario = scenario;
    }

    /*
    Reads file line by line and puts correct values to 'title', 'actors', ... of scenario
     */
    public void readFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {

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
                    scenario.systemActors = List.of(systemActorsLine.split(","));
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

    /*
    Gets the indentation of one line (number of white spaces before first sign)
     */
    private int getIndentation(String line) {
        // Calculate the number of leading whitespaces to determine indentation
        int indentation = 0;
        while (indentation < line.length() && Character.isWhitespace(line.charAt(indentation))) {
            indentation++;
        }
        return indentation;
    }
}