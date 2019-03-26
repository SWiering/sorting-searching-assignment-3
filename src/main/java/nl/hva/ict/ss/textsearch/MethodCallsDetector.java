package nl.hva.ict.ss.textsearch;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCallsDetector {
   
    final private String REGEX = "(\\w+\\.)?\\w+(?=\\(.*\\))";
    private String file;

    public MethodCallsDetector(InputStream input) {
        Scanner s = new Scanner(input);
        s.useDelimiter("\\Z"); // EOF marker
        file = s.next();
    }

    public int getMethodCallsCount() {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(file);
        int methodCallCount = 0;

        while (m.find()) {
            methodCallCount++;
        }

        return methodCallCount;
    }

    public Set<String> getUniqueMethods() {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(file);
        Set<String> methodCalls = new HashSet<>();

        while (m.find()) {
            methodCalls.add(m.group(0));
        }

        return methodCalls;
    }
}