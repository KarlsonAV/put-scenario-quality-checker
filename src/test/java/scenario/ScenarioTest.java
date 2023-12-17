package scenario;

import com.put.gamma.scenario.Scenario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.put.gamma.section.Section;

import java.util.ArrayList;
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
        scenario.sections.add(section1);
        scenario.sections.add(section2);
        scenario.sections.add(section3);
        scenario.sections.add(section4);
        scenario.sections.get(3).subsections.add(section5);
        scenario.sections.get(3).subsections.add(section6);
        scenario.sections.get(3).subsections.get(1).subsections.add(section7);
        scenario.sections.get(3).subsections.add(section8);
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

}