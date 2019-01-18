package nl.hva.ict.ss.textsearch;

public class BackwardsSearch {
    /**
     * Returns index of the left most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the left end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    
    private int Radix;     // the radix
    private int[] left;     // the bad-character skip array // TODO: rename to left

    private char[] pattern;  // store the pattern as a character array
    private String needle;

    private int comparisons;
    
    int findLocation(String needle, String haystack) {
        this.comparisons = 0;

        this.Radix = 256;
        this.needle = needle;

        // position of rightmost occurrence of c in the pattern
        left = new int[Radix];
        for (int c = 0; c < Radix; c++)
            left[c] = -1;
        for (int j = needle.length() - 1; j >= 0; j--)
            left[needle.charAt(j)] = j;
        
        int needleLength = needle.length();
        int haystackLength = haystack.length();
        int skip;

        // start at the end of the string
        for(int currentIndex = haystackLength - needleLength; currentIndex > 0; currentIndex-= skip){
            skip = 0;

            // search the string from the left to the left
            for (int needleIndex = 0; needleIndex < needleLength - 1; needleIndex++ ){
                // compare if chars are the same
                this.comparisons++;

                if(needle.charAt(needleIndex) != haystack.charAt(needleIndex + currentIndex)){
                    // math max takes care of skip not being smaller than 1
                    skip = Math.max(1, needleIndex - left[haystack.charAt(currentIndex+needleIndex)]);
                    break;
                }
            }
            if (skip == 0) return currentIndex;    // found
        }
        return -1;

    }

    public int bmFindLocation(String theNeedle, String theHaystack) {
        // this is the copied algorithm from https://algs4.cs.princeton.edu/53substring/BoyerMoore.java.html
        // with renamed variables to prevent confusion/mixing of values
        int R = 256;

        // position of rightmost occurrence of c in the pattern
        int[] right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < theNeedle.length(); j++)
            right[theNeedle.charAt(j)] = j;


        int M = theNeedle.length();
        int N = theHaystack.length();
        int skipPerStep;
        for (int i = 0; i <= N - M; i += skipPerStep) {
            skipPerStep = 0;
            for (int j = M-1; j >= 0; j--) {
                this.comparisons++;

                if (theNeedle.charAt(j) != theHaystack.charAt(i+j)) {
                    skipPerStep = Math.max(1, j - right[theHaystack.charAt(i+j)]);
                    break;
                }
            }
            if (skipPerStep == 0) return i;    // found
        }
        return -1;                       // not found

    }

    /**
     * Returns the number of character compares that where performed during the last search.
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch() {
        return this.comparisons;
    }

}
