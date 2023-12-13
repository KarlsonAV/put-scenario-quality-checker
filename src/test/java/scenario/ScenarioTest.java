package scenario;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import section.Section;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScenarioTest {
    Section section1 = new Section("Hektor miał Trojan radę nad Skamandru rzeką");
    Section section2 = new Section("");
    Section section3 = new Section("System zwraca wartość ujemną");
    Section section4 = new Section("    Hektor miał Trojan radę nad Skamadru rzeką");
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
        scenario.sections.add(section1);
        scenario.sections.add(section2);
        scenario.sections.add(section3);
        scenario.sections.get(2).subsections.add(section4);
    }
    @Test
    public void testCheckIfBeginsWithActorNameNormal1(){
        //assertTrue(section1.checkIfBeginsWithActorName(allActors));

        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(0)));
    }
    @Test
    public void testCheckIfBeginsWithActorNameEmpty(){
        //assertFalse(section2.checkIfBeginsWithActorName(allActors));
        assertFalse(scenario.checkIfBeginsWithActorName(scenario.sections.get(1)));
    }
    @Test
    public void testCheckIfBeginsWithActorNameNormal2(){
        //assertTrue(section3.checkIfBeginsWithActorName(allActors));
        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(2)));
    }
    @Test
    public void testCheckIfBeginsWithActorNameIndentation(){
        //assertTrue(section3.checkIfBeginsWithActorName(allActors));
        assertTrue(scenario.checkIfBeginsWithActorName(scenario.sections.get(2).subsections.get(0)));
    }



}