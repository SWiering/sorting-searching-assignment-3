package nl.hva.ict.ss.textsearch;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageDetector {
    private String content;

    private HashMap<String, Integer> chars;
    private HashMap<String, Double> englishVowelPercentages;
    private HashMap<String, Double> englishVowelDistribution;

    private int charCount;

    private boolean englishText;


    public LanguageDetector(InputStream input) {
        englishText = false;

        // new instance scanner
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\\Z");

        // get next lines
        content = scanner.next();

        // create new instances of hashmaps
        this.chars = new HashMap<>();
        this.englishVowelPercentages = new HashMap<>();
        this.englishVowelDistribution = new HashMap<>();

        setVowelPercent();
        Initialize();
    }

    private void setVowelPercent() {
        this.englishVowelPercentages.clear();

        // percentages found on wikipedia
        this.englishVowelPercentages.put("a", 8.167);
        this.englishVowelPercentages.put("e", 12.702);
        this.englishVowelPercentages.put("o", 7.507);
        this.englishVowelPercentages.put("u", 2.758);
        this.englishVowelPercentages.put("i", 6.966);
    }

    private void Initialize() {
        // start with creating a character map
        createCharacterMap();

        // set the distribution of the english vowels
        makeEnglishVowelDistributionMap();

        // range in what the percentage can deviate.
        double rangeOfVariety = 2.03;

        this.englishText = detectEnglishText(rangeOfVariety);
    }


    private void createCharacterMap() {
        //regular expression to get all the letters of the alphabet
        String regex = "([A-Z]|[a-z])";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(content);

        // while the matcher is finding matches
        while (matcher.find())
        {
            String charFound = matcher.group(0).toLowerCase();
            if (!this.chars.containsKey(charFound)) {
                this.chars.put(charFound, 1);
            } else {
                int amount = this.chars.get(charFound);
                this.chars.replace(charFound, ++amount);
            }
        }
    }

    private boolean detectEnglishText(double range) {
        for (Map.Entry<String, Double> entry : this.englishVowelDistribution.entrySet()) {
            String key = entry.getKey();

            double percentage = entry.getValue();
            double statisticalPercentage = this.englishVowelPercentages.get(key);

            // when one vowel doesn't correspond with the range
            if (!(percentage + range > statisticalPercentage && percentage - range < statisticalPercentage)){
                return false;
            }
        }
        return true;
    }

    private void makeEnglishVowelDistributionMap() {
        String[] vowels = {"a", "e", "o", "u", "i"};
        this.charCount = 0;

        for (Map.Entry<String, Integer> entry : this.chars.entrySet())
            this.charCount += entry.getValue();

        for (Map.Entry<String, Integer> entry : this.chars.entrySet()) {
            String key = entry.getKey();

            for (int i = 0; i < vowels.length; i++) {
                if (vowels[i].equalsIgnoreCase(key)){
                    this.englishVowelDistribution.put(key, getPercentage(key));
                }
            }
        }
    }

    private double getPercentage(String string) {
        if (string.isEmpty() || string.length() > 1 || string == null)
            return -1;

        int amount = this.chars.get(string);
        double percentage = ((double) amount / (double) this.charCount) * 100.0f;

        return percentage;
    }

    public boolean isEnglish() {
        return this.englishText;
    }
}
