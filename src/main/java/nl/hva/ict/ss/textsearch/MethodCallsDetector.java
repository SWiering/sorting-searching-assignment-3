package nl.hva.ict.ss.textsearch;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallsDetector {
    // Doesn't work due not allowing repetitions in negative lookahead
//    final private String METHOD_CALL_REGEX = "(?<!(new|private|public|protected).*)(\\w+\\.)?\\w+(?=\\(.*\\))";
    final private String REGEX = "(\\w+\\.)?\\w+(?=\\(.*\\))";
    private String content; // Once an instance is created this will hold the complete content of the file.

    public MethodCallsDetector(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        content = sc.next();
    }

    public int getMethodCallsCount() {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(content);
        int methodCallCount = 0;

        while (m.find()) {
            methodCallCount++;
        }

        return methodCallCount;
    }

    public Set<String> getUniqueMethods() {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(content);
        Set<String> methodCalls = new HashSet<>();

        while (m.find()) {
            methodCalls.add(m.group(0));
        }

        return methodCalls;
    }
}