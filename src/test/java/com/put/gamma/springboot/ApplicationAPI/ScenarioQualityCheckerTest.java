package com.put.gamma.springboot.ApplicationAPI;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/count/sections")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfSections").value(13));
    }

    @Test
    void testCountSectionsWithKeywords() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/count/sections/keywords")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfSectionsWithKeywords").value(2));
    }

    @Test
    void testFindSectionsWithErrors() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/sections/errors")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sectionsWithErrors").isArray());
    }

    @Test
    void testEmptyFile() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", MediaType.TEXT_PLAIN_VALUE, new byte[0]);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/count/sections")
                        .file(emptyFile))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("File is empty."));
    }

    @Test
    void testFileReadingError() throws Exception {
        MockMultipartFile invalidFile = new MockMultipartFile("file", "file.html", MediaType.TEXT_PLAIN_VALUE, "Invalid scenario content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/count/sections")
                        .file(invalidFile))
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("An error occurred: The provided file is not a .txt file."));
    }

    @Test
    void testScenarioUpToDepth() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/scenario/scenarioUpToDepth")
                        .file(file)
                        .param("depth", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scenarioUpToDepth").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scenarioUpToDepth").isString());
    }

    @Test
    void testScenarioUpToDepthFail() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/scenario/scenarioUpToDepth")
                        .file(file))
                        .andExpect(status().isBadRequest());
    }

    @Test
    void testScenarioUpToDepthIncorrectDepth() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/scenario/scenarioUpToDepth")
                        .file(file)
                        .param("depth", "a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testShowEnumeratedScenario() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/scenario/enumerated")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.enumeratedScenario").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.enumeratedScenario").isString())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        String scenarioUpToDepth = JsonPath.read(responseJson, "$.enumeratedScenario").toString();

        assertTrue(scenarioUpToDepth.contains("6"));
    }

    @Test
    void testScenarioMainSteps() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "scenario.txt",
                MediaType.TEXT_PLAIN_VALUE, exampleScenario.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v2/scenario/mainSteps")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mainSteps").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mainSteps").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mainSteps").value("Dobry scenariusz"));
    }

}