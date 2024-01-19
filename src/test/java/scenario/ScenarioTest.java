package scenario;

import com.put.gamma.scenario.Scenario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScenarioTest {
    Section section1 = new Section("Hektor miał Trojan radę nad Skamandru rzeką");
    Section section2 = new Section("");
    Section section3 = new Section("System zwraca wartość ujemną");
    Section section4 = new Section("FOR EACH: System");
    Section section5 = new Section("    Hektor miał Trojan radę nad Skamandru rzeką");
    Section section6 = new Section("    IF: Homer jest aktorem");
    Section section7 = new Section("        Nie ma tu aktora!");
    Section section8 = new Section("    IF: Test aktorow");
    Section section9 = new Section("        Herold jest aktorem");
    List<String> actors = new ArrayList<>(){{
       add("Hektor");
       add("Homer");
       add("Herold");
    }};
    List<String> systemActors = new ArrayList<>(){{
        add("System");
    }};
    Scenario scenario = new Scenario();



    @BeforeAll
    public void setup(){
        scenario.setActors(actors);
        scenario.setSystemActors(systemActors);
        section1.setDepth(1);
        scenario.sections.add(section1);
        section2.setDepth(1);
        scenario.sections.add(section2);
        section3.setDepth(1);
        scenario.sections.add(section3);
        section4.setDepth(1);
        scenario.sections.add(section4);
        section5.setDepth(2);
        scenario.sections.get(3).subsections.add(section5);
        section6.setDepth(2);
        scenario.sections.get(3).subsections.add(section6);
        section7.setDepth(3);
        scenario.sections.get(3).subsections.get(1).subsections.add(section7);
        section8.setDepth(2);
        scenario.sections.get(3).subsections.add(section8);
        section9.setDepth(3);
        scenario.sections.get(3).subsections.get(2).subsections.add(section9);
    }
    @Test
    public void testCheckIfBeginsWithActorNameNormal1(){
        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(0)));
    }
    @Test
    public void testCheckIfBeginsWithActorNameEmpty(){
        assertFalse(scenario.checkIfBeginsWithActorName(scenario.sections.get(1)));
    }
    @Test
    public void testCheckIfBeginsWithActorNameNormal2(){
        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(2)));
    }
    @Test
    public void testCheckIfBeginsWithActorNameKeyword(){
        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(3)));
    }
    @Test
    public void testCheckIfBeginsWithActorNameIndentation(){
        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(3).subsections.get(0)));
    }

    @Test
    public void testCheckIfBeginsWithActorNameIndentationKeyword(){
        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(3).subsections.get(1)));
    }
    @Test
    public void testFindSectionsWithErrors(){
        scenario.displayErrorSections();
        assertEquals(2,scenario.findSectionsWithErrors().size());
    }
    @Test
    public void testCheckIfBeginsWithKeyword(){
        assertTrue(scenario.sections.get(3).subsections.get(2).checkIfBeginsWithKeyword(scenario.keywords));
    }
    @Test
    public void testCheckScenarioUpToDepth1(){
        String exampleScenario = "Hektor miał Trojan radę nad Skamandru rzeką\n\nSystem zwraca wartość ujemną\nFOR EACH: System\n";
        assertEquals(exampleScenario,scenario.displayScenarioUpToDepth(1));
    }
    @Test
    public void testCheckScenarioUpToDepth2(){
        String exampleScenario = "Hektor miał Trojan radę nad Skamandru rzeką\n\nSystem zwraca wartość ujemną\nFOR EACH: System\n    Hektor miał Trojan radę nad Skamandru rzeką\n    IF: Homer jest aktorem\n    IF: Test aktorow\n";
        assertEquals(exampleScenario,scenario.displayScenarioUpToDepth(2));
    }

    @Test
    public void testSectionDepthAssignment() {
        Scenario testScenario = new Scenario();
        Section testSection = new Section("Depth Test");
        testSection.setDepth(2);
        testScenario.sections.add(testSection);

        assertEquals(2, testScenario.sections.get(testScenario.sections.size() - 1).getDepth(), "Section depth should be correctly set and retrieved.");
    }


    @Test
    public void testActorsAndSystemActorsSetting() {
        Scenario testScenario = new Scenario();
        testScenario.setActors(Arrays.asList("Actor1", "Actor2", "Actor3"));
        testScenario.setSystemActors(Collections.singletonList("SystemActor"));

        assertEquals(3, testScenario.getActors().size(), "Actors list should contain 3 actors.");
        assertEquals(1, testScenario.getSystemActors().size(), "System actors list should contain 1 actor.");
    }


    @Test
    public void testInvalidActorName() {
        Scenario testScenario = new Scenario();
        Section invalidActorSection = new Section("Zeus miał radę nad Olimpem");
        testScenario.sections.add(invalidActorSection);

        assertFalse(testScenario.checkIfBeginsWithActorName(invalidActorSection), "Section should not begin with a valid actor name.");
    }


    @Test
    public void testCheckMainStepsForTooFewSteps() {
        Scenario scenarioWithFewSteps = new Scenario();
        for (int i = 0; i < 2; i++) { // Adding less than 3 main steps
            Section section = new Section("Step " + i);
            section.setDepth(1); // Setting depth to indicate main steps
            scenarioWithFewSteps.sections.add(section);
        }
        assertEquals("Za mało kroków", scenarioWithFewSteps.checkMainSteps(), "Scenario should indicate 'Za mało kroków' for less than 3 main steps");
    }

    @Test
    public void testCheckMainStepsForGoodScenario() {
        Scenario goodScenario = new Scenario();
        for (int i = 0; i < 5; i++) { // Adding between 3 and 9 main steps
            Section section = new Section("Step " + i);
            section.setDepth(1); // Setting depth to indicate main steps
            goodScenario.sections.add(section);
        }
        assertEquals("Dobry scenariusz", goodScenario.checkMainSteps(), "Scenario should indicate 'Dobry scenariusz' for 3-9 main steps");
    }

    @Test
    public void testCheckMainStepsForTooManySteps() {
        Scenario scenarioWithManySteps = new Scenario();
        for (int i = 0; i < 10; i++) { // Adding more than 9 main steps
            Section section = new Section("Step " + i);
            section.setDepth(1); // Setting depth to indicate main steps
            scenarioWithManySteps.sections.add(section);
        }
        assertEquals("Za dużo kroków", scenarioWithManySteps.checkMainSteps(), "Scenario should indicate 'Za dużo kroków' for more than 9 main steps");
    }





}