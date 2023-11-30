package com.put.gamma.springboot.TestFunctionality;

public class Document {
    private String title;
    private String author;

    Document() {
        this.title = "";
        this.author = "";
    }

    public Document(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }
}
