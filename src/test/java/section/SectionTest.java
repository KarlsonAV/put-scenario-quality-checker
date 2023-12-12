package section;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {
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
    @Test
    public void testCheckIfBeginsWithActorName(){
        assertTrue(section1.checkIfBeginsWithActorName(actors,systemActors));
        assertFalse(section2.checkIfBeginsWithActorName(actors,systemActors));
        assertTrue(section3.checkIfBeginsWithActorName(actors,systemActors));
        assertTrue(section4.checkIfBeginsWithActorName(actors,systemActors));
    }

}