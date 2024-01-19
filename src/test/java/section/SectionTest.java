package section;


import com.put.gamma.Element.Element;
import com.put.gamma.scenario.Scenario;
import com.put.gamma.visitor.Visitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {

    private Section section;
    private Section parentSection;
    private List<String> actors;
    private List<String> keywords;

    @BeforeEach
    void setUp() {
        parentSection = new Section("Actor1 Content");
        section = new Section("IF: Test Content", parentSection);
        actors = Arrays.asList("Actor1", "Actor2");
        keywords = Arrays.asList("IF", "ELSE", "FOR EACH");
    }

    @Test
    void testConstructors() {
        assertNotNull(section);
        assertEquals("IF: Test Content", section.content);
        assertEquals(parentSection, section.parent);

        Section singleParamSection = new Section("Single Param Content");
        assertEquals("Single Param Content", singleParamSection.content);
        assertNull(singleParamSection.parent);
    }

    @Test
    void testDepthSettingAndGet() {
        section.setDepth(2);
        assertEquals(2, section.getDepth());
    }

    @Test
    void testSubsectionsHandling() {
        Section subSection = new Section("Subsection Content", section);
        section.subsections.add(subSection);
        assertTrue(section.subsections.contains(subSection));
        assertEquals(section, subSection.parent);
    }

    @Test
    void testCheckIfBeginsWithActorName() {
        assertFalse(section.checkIfBeginsWithActorName(actors));
        assertTrue(parentSection.checkIfBeginsWithActorName(actors));
    }

    @Test
    void testCheckIfBeginsWithKeyword() {
        assertTrue(section.checkIfBeginsWithKeyword(keywords));
        assertFalse(parentSection.checkIfBeginsWithKeyword(keywords));
    }
}
