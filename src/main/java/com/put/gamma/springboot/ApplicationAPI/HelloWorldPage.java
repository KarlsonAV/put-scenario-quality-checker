package com.put.gamma.springboot.ApplicationAPI;

import com.put.gamma.springboot.TestFunctionality.Document;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import scenario.Scenario;
import txtReader.TxtReader;



import java.io.File;
import java.io.IOException;

@RestController
public class HelloWorldPage {
    @GetMapping("/")
    public String index() {
        Document document = new Document("The Lord of the Rings", "John Ronald Reuel Tolkien");
        return "Book: " + document.getTitle() + "\nAuthor: " + document.getAuthor();
    }

    @PostMapping("/submit")
    public String addDocument(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty.";
        }
        Scenario scenario = new Scenario();
        scenario.scenarioTextReader = new TxtReader(file, scenario);
        scenario.scenarioTextReader.readFile();

        //Usage example
        System.out.println("Tytuł: " + scenario.title);
        System.out.println("Aktorzy: " + scenario.actors);
        System.out.println("Aktorzy systemowi: " + scenario.systemActors);
        System.out.println(scenario.sections.get(3).subsections.get(2).subsections.get(0).content);
        System.out.println("Liczba kroków: "+ scenario.countAllSections(scenario.sections));
        System.out.println("Krok: "+ scenario.sections.get(3).content +" zawiera aktorów: "+scenario.sections.get(3).checkIfBeginsWithActorName(scenario.getActors(),scenario.getSystemActors()));
        System.out.println("Krok: "+ scenario.sections.get(2).content +" zawiera aktorów: "+scenario.sections.get(2).checkIfBeginsWithActorName(scenario.getActors(),scenario.getSystemActors()));
        return scenario.title;
    }
}
