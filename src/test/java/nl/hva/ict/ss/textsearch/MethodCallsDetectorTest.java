package nl.hva.ict.ss.textsearch;

import org.junit.Test;

import java.util.Set;

public class MethodCallsDetectorTest {

    @Test
    public void testMethodCallDetector() {
        MethodCallsDetector detector = new MethodCallsDetector(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Huffman.java"));
        int amountMethodCalls = detector.getMethodCallsCount();

        System.out.printf("amountMethodCalls: %d%n", amountMethodCalls);

        System.out.println("Method calls:");
        Set<String> methods = detector.getUniqueMethods();
        for (String methodCall : methods) {
            System.out.println(methodCall);
        }
    }
}