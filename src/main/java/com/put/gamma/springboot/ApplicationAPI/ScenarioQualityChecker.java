package com.put.gamma.springboot.ApplicationAPI;

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
    @PostMapping("api/v1/count/sections")
    @ResponseBody
    public ResponseEntity<Object> countSections(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            int numberOfSections = scenario.countAllSections();

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
    @PostMapping("api/v1/count/sections/keywords")
    @ResponseBody
    public ResponseEntity<Object> countSectionsWithKeywords(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            int numberOfSectionsWithKeywords = scenario.countSectionsWithKeywords();
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
    @PostMapping("api/v1/sections/errors")
    @ResponseBody
    public ResponseEntity<Object> findSectionsWithErrors(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            Scenario scenario = new Scenario();
            scenario.scenarioTextReader = new TxtReader(file, scenario);
            scenario.scenarioTextReader.readFile();

            List<String> sectionsWithErrors = scenario.findSectionsWithErrors();

            return ResponseEntity.ok().body("{\"sectionsWithErrors\": " + sectionsWithErrors + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
