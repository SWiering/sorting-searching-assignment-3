package nl.hva.ict.ss.textsearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LanguageDetectorTest {
    private LanguageDetector detector;

    @Before
    public void setup() {
        detector = new LanguageDetector(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Huffman.java"));
    }

    @Test
    public void english(){
        assertTrue(detector.isEnglishText());
    }

    // Add your tests here. They are allowed to NOT use assertXxxx... :-)
}