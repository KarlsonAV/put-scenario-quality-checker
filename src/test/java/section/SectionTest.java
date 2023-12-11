package section;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {
    @Test
    public void testCheckIfBeginsWithKeywordBegins(){
        Section section = new Section("IF: Test content");
        List<String> keywords = new ArrayList<String>(Arrays.asList("IF","BEGIN","TEST"));
        assertTrue(section.checkIfBeginsWithKeyword(keywords));
    }
    @Test
    public void testCheckIfBeginsWithKeywordEmpty(){
        Section section = new Section("IF: Test content");
        List<String> keywords = new ArrayList<String>();
        assertFalse(section.checkIfBeginsWithKeyword(keywords));
    }

    @Test
    public void testCheckIfBeginsWithKeywordLowercase(){
        Section section = new Section("test content");
        List<String> keywords = new ArrayList<String>(Arrays.asList("IF","BEGIN","TEST"));
        assertFalse(section.checkIfBeginsWithKeyword(keywords));
    }

    @Test
    public void testCheckIfBeginsWithKeywordInMiddle(){
        Section section = new Section("Notkeyword: BEGIN content");
        List<String> keywords = new ArrayList<String>(Arrays.asList("IF","BEGIN","TEST"));
        assertFalse(section.checkIfBeginsWithKeyword(keywords));
    }

    @Test
    public void testCheckIfBeginsWithKeywordNoKeyword(){
        Section section = new Section("Test content");
        List<String> keywords = new ArrayList<String>(Arrays.asList("IF","BEGIN","TEST"));
        assertFalse(section.checkIfBeginsWithKeyword(keywords));
    }

}