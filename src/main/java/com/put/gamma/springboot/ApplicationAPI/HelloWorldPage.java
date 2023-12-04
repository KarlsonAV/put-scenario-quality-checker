package com.put.gamma.springboot.ApplicationAPI;

import com.put.gamma.springboot.TestFunctionality.Document;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        try {
            // Get the bytes of the file content
            byte[] bytes = file.getBytes();

            // Convert bytes to String assuming it's a text file (adjust charset if needed)
            String fileContent = new String(bytes);

            // Print or process the file content
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("File content: " + fileContent);

            return file.getOriginalFilename();
        } catch (IOException e) {
            // Handle exception (e.g., log it)
            return "Failed to process the file.";
        }
    }
}
