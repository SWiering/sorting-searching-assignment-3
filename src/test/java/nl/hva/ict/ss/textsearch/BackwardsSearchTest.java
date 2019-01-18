package nl.hva.ict.ss.textsearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BackwardsSearchTest {
    protected BackwardsSearch searchEngine;

    // keep this inbetween the lines
    private final String thousandLetters = "ZzFskkyhRRxChVuBAZkXyeqYFAHEEHygpVliyppHLnaFSjfywCsEfgTeWoPQPnSkXmwCFVhJFaGjX" +
            "eyZAAmbEKqChbgtNRCTVRJRVvjFVTQxHkHrKGjjQUSBZuiyXulnWyiPaUgOdYywkTcFgxGmzbfdLAdrpLOgKQufukDSmtLyFhXYUmrMKeNP" +
            "rjCCPaFaQkfiVrkOFdkKfCUzCxutptvbGLcMFTWgvcCCcoxGZLKGXLmQHfgwaisiOLwjfgfMfdyVRODRYcPlDcQFUVVnRWJIVogTaYemMSJ" +
            "BYwqJqIjejTgFpoLetSLGsMoHktfgfwKSEtAecqMwxPxxzACrbpygvaGwsRWiOFvEumocvZSbYESESkaCyyHIsHvracsuBMqeVaAuFjNvai" +
            "qbpQqajcgwwlirSqwozWVyTEbnWkaMTRDIjCGgeogIArjdGNNOLWESICkeNtzIbYXhmMWWLDFJvMcbayvzzZZbpzpIamGowvAQtmeTZGjkk" +
            "yPZmMkrutmNQhpKuYVzlgOSphaIDOWwmScOpDzOLaWFoZVcSNAevoLdRtPRkPknRoJWbWjcntWzSxQZDMCSxgciWrcYYSjEmmHmgxRAriqF" +
            "OBnpTCCiGlUupNfzuBZYFlhduKSBufToCVEzWzIhAAGeSQgXyODneedlefkinjuDSMOOYhdEHymNmsWYLEjKQnofhUwLShYHXxSczmJdwHR" +
            "VUUjAIsBqxLxJLddfEuFoXsStEAZEnrtcccLTQXeGtajSTGOCgnviuRqUPlSWwPgZysYkQEDCJErtGpJfSxzwRmTzQqRMRWrptDdpqzMyxW" +
            "faFeWGqZauhNWxqpHCwvastqCcFRJgvILXbukAHAJJFaIwpAwZcsQncQilSRiYUKxieKbrsVSJlFtHrWQzBklixlFsJWCZCVOGwEMpWOTWf" +
            "pglrdWrIucaKabOEHfBjnjjVjlRbITgBPeLLndahlVjWtneUhmRyvwLBwoxFEgctnoq";


    @Before
    public void setup() {
        searchEngine = new BackwardsSearch();
    }

    @Test
    public void findSingleOccurrence() {
        int index = searchEngine.findLocation("needle", "whereistheneedleinthishaystack");

        assertEquals("whereisthe".length(), index);
    }

    @Test
    public void cantFindOccurrence() {
        int index = searchEngine.findLocation("needle", "thereisnothinginthishaystack");

        assertEquals(-1, index);
    }

    @Test
    public void canFindLastWhenDouble(){
        int index = searchEngine.findLocation("needle", "theneedleneedle");
        assertEquals("theneedle".length(), index);
    }

    @Test
    public void canFindInStrangeString(){
        int index = searchEngine.findLocation("needle", "theneneedleedle");
        assertEquals("thene".length(), index);
    }

    @Test
    public void comparisons(){
        searchEngine.findLocation("needle", thousandLetters);
        int reverseComparisons = searchEngine.getComparisonsForLastSearch();

        // this is the function to find the index in the original way
        searchEngine.bmFindLocation("needle", thousandLetters);
        int boyerMooreComparisons = searchEngine.getComparisonsForLastSearch();

        // proof that reverse search has less comparisons than traditional way
        assertTrue(reverseComparisons < boyerMooreComparisons);
    }
}