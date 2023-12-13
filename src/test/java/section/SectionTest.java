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
    List<String> allActors = new ArrayList<>(){{
       add("Hektor");
       add("Homer");
       add("Herold");
       add("System");
    }};

    @Test
    public void testCheckIfBeginsWithActorNameNormal1(){

    }
    //jeden test jeden assert

}