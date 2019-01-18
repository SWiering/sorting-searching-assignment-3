package nl.hva.ict.ss.textsearch;

public class BackwardsSearch {
    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    
    private int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String needle;  
    
    int findLocation(String needle, String haystack) {
        this.R = 256;
        this.needle = needle;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < needle.length(); j++)
            right[needle.charAt(j)] = j;
        
        
        int M = needle.length();
        int N = haystack.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (needle.charAt(j) != haystack.charAt(i+j)) {
                    skip = Math.max(1, j - right[haystack.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return -1;                       // not found
       
    }

    /**
     * Returns the number of character compares that where performed during the last search.
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch() {
        return 0;
    }

}
