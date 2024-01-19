package com.put.gamma.springboot.ApplicationAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.put.gamma.scenario.Scenario;
import com.put.gamma.txtReader.TxtReader;

import java.util.List;

/**
 * Controller class for the Scenario Quality Checker API.
 */
@RestController
public class ScenarioQualityChecker {

    /**
     * Endpoint to count the number of sections in a scenario file.
     *
     * @param file Multipart txt file containing the scenario text.
     * @return ResponseEntity with the JSON object indicating the number of sections.
     */

    private static Logger logger = LoggerFactory.getLogger(ScenarioQualityChecker.class);
    @PostMapping("api/v2/count/sections")
    @ResponseBody
    public ResponseEntity<Object> countSections(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                logger.info("File is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            int numberOfSections = scenario.countAllSections();
            logger.info("Number of Sections: " + numberOfSections);
            // Create and return a JSON response object
            return ResponseEntity.ok().body("{\"numberOfSections\": " + numberOfSections + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Endpoint to count the number of sections containing keywords in a scenario file.
     *
     * @param file Multipart txt file containing the scenario text.
     * @return ResponseEntity with the JSON object indicating the number of sections with keywords.
     */
    @PostMapping("api/v2/count/sections/keywords")
    @ResponseBody
    public ResponseEntity<Object> countSectionsWithKeywords(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                logger.info("File is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            int numberOfSectionsWithKeywords = scenario.countSectionsWithKeywords();
            logger.info("Number of Sections With Keywords: " + numberOfSectionsWithKeywords);
            return ResponseEntity.ok().body("{\"numberOfSectionsWithKeywords\": " + numberOfSectionsWithKeywords + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Endpoint to find sections with errors in a scenario file.
     *
     * @param file Multipart txt file containing the scenario text.
     * @return ResponseEntity with the JSON object containing a list of sections with errors.
     */
    @PostMapping("api/v2/sections/errors")
    @ResponseBody
    public ResponseEntity<Object> findSectionsWithErrors(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                logger.info("File is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            List<String> sectionsWithErrors = scenario.findSectionsWithErrors();
            for (int i = 0; i < sectionsWithErrors.size(); i++) {
                String section = sectionsWithErrors.get(i);
                section = '"' + section + '"';
                sectionsWithErrors.set(i, section);
            }
            logger.info("Sections with error: " + sectionsWithErrors);
            return ResponseEntity.ok().body("{\"sectionsWithErrors\": " + sectionsWithErrors + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Endpoint to display scenario with enumerated steps.
     *
     * @param file Multipart txt file containing the scenario text.
     * @return ResponseEntity with the JSON object containing an enumerated scenario.
     */
    @PostMapping("api/v2/scenario/enumerated")
    @ResponseBody
    public ResponseEntity<Object> showEnumeratedScenario(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                logger.info("File is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            String enumeratedScenario = scenario.enumerateScenario();
            logger.info("Scenario: " + enumeratedScenario);
            return ResponseEntity.ok().body("{\"enumeratedScenario\": " + '"' + enumeratedScenario + '"' + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("api/v2/scenario/mainSteps")
    @ResponseBody
    public ResponseEntity<Object> scenarioMainSteps(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                logger.info("File is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            String mainSteps = scenario.checkMainSteps();
            logger.info("Info about main steps: " + mainSteps);
            return ResponseEntity.ok().body("{\"mainSteps\": " + '"' + mainSteps + '"' + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Endpoint to parse the provided MultipartFile containing scenario data,
     * returns the scenario up to the specified depth,
     *
     * @param file  The MultipartFile containing the scenario data.
     * @param depth The depth up to which the scenario should be displayed.
     * @return ResponseEntity with the JSON object containing scenario information or an error message.
     */
    @PostMapping("api/v2/scenario/scenarioUpToDepth")
    @ResponseBody
    public ResponseEntity<Object> ScenarioUpToDepth(@RequestParam("file") MultipartFile file, @RequestParam("depth") Integer depth) {
        try {
            if (file.isEmpty()) {
                logger.info("File is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            String scenarioUpToDepth = scenario.displayScenarioUpToDepth(depth);
            logger.info("Scenario up to depth: " + depth + "\n" + scenarioUpToDepth);
            return ResponseEntity.ok().body("{\"scenarioUpToDepth\": " + '"' + scenarioUpToDepth + '"' + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
