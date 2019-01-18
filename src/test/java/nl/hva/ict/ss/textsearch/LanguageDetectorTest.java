package nl.hva.ict.ss.textsearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LanguageDetectorTest {
    private LanguageDetector detector, dutch, spanish;
    private final String resourceMap = "/edu/princeton/cs/algs4/";

    @Before
    public void setup() {
        detector = new LanguageDetector(getClass().getResourceAsStream(resourceMap + "Huffman.java"));
        dutch = new LanguageDetector(getClass().getResourceAsStream(resourceMap + "Huffman.java"));
        spanish = new LanguageDetector(getClass().getResourceAsStream(resourceMap + "Huffman.java"));
    }

    @Test
    public void english(){
        assertTrue(detector.isEnglish());
    }

    @Test
    public void isDutch(){
        System.out.println(dutch.isEnglish());

        assertFalse(dutch.isEnglish());
    }

    @Test
    public void isSpanish(){
        System.out.println(spanish.isEnglish());

        assertFalse(spanish.isEnglish());
    }

    // Add your tests here. They are allowed to NOT use assertXxxx... :-)
}