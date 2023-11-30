package com.put.gamma.springboot.ApplicationAPI;

import com.put.gamma.springboot.TestFunctionality.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldPage {
    @GetMapping("/")
    public String index() {
        Document document = new Document("The Lord of the Rings", "John Ronald Reuel Tolkien");
        return "Book: " + document.getTitle() + "\nAuthor: " + document.getAuthor();
    }

}
