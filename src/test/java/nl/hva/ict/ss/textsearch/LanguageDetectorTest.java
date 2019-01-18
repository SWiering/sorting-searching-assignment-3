package nl.hva.ict.ss.textsearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LanguageDetectorTest {
    private LanguageDetector detector;
    private LanguageDetector dutch;
    private LanguageDetector spanish;
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
}