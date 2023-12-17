package com.put.gamma.springboot.ApplicationAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ScenarioQualityCheckerTest {
    private final String exampleScenario =
"""
Tytuł: Dodanie książki
Aktorzy:  Bibliotekarz, Ja
Aktor systemowy: System

Bibliotekarz wybiera opcje dodania nowej pozycji książkowej
Wyświetla się formularz.
Bibliotekarz podaje dane książki.
IF: Bibliotekarz pragnie dodać egzemplarze książki
    Bibliotekarz wybiera opcję definiowania egzemplarzy
    System prezentuje zdefiniowane egzemplarze
    FOR EACH egzemplarz:
        Bibliotekarz wybiera opcję dodania egzemplarza
        System prosi o podanie danych egzemplarza
        Bibliotekarz podaje dane egzemplarza i zatwierdza.
        System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
Bibliotekarz zatwierdza dodanie książki.
System informuje o poprawnym dodaniu książki.""".strip();


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCountSections() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/count/sections")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfSections").value(13));
    }

    @Test
    void testCountSectionsWithKeywords() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/count/sections/keywords")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfSectionsWithKeywords").value(2));
    }

    @Test
    void testFindSectionsWithErrors() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/sections/errors")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sectionsWithErrors").isArray());
    }

    @Test
    void testEmptyFile() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", MediaType.TEXT_PLAIN_VALUE, new byte[0]);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/count/sections")
                        .file(emptyFile))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("File is empty."));
    }

    @Test
    void testFileReadingError() throws Exception {
        MockMultipartFile invalidFile = new MockMultipartFile("file", "file.html", MediaType.TEXT_PLAIN_VALUE, "Invalid scenario content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/count/sections")
                        .file(invalidFile))
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("An error occurred: The provided file is not a .txt file."));
    }
}
