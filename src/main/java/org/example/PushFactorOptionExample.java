package org.example;

import java.util.HashMap;
import java.util.Map;

public class PushFactorOptionExample {

    public static void main(String[] args) {
        // Example 1: Map with isPushFactorOptionEnabled set to true
        Map<String, Object> contextTrue = new HashMap<>();
        contextTrue.put("isPushFactorOptionEnabled", true);
        boolean isEnabledTrue = Boolean.TRUE.equals(contextTrue.get("isPushFactorOptionEnabled"));
        System.out.println("Example 1 (should be true): " + isEnabledTrue);

        // Example 2: Map with isPushFactorOptionEnabled set to false
        Map<String, Object> contextFalse = new HashMap<>();
        contextFalse.put("isPushFactorOptionEnabled", "y");
        boolean isEnabledFalse = Boolean.TRUE.equals(contextFalse.get("isPushFactorOptionEnabled"));
        System.out.println("Example 2 (should be false): " + isEnabledFalse);

        // Example 3: Map without the isPushFactorOptionEnabled key
        Map<String, Object> contextMissing = new HashMap<>();
        boolean isEnabledMissing = Boolean.TRUE.equals(contextMissing.get("isPushFactorOptionEnabled"));
        System.out.println("Example 3 (missing key, should be false): " + isEnabledMissing);

        // Example 4: Map with isPushFactorOptionEnabled explicitly set to null
        Map<String, Object> contextNull = new HashMap<>();
        contextNull.put("isPushFactorOptionEnabled", null);
        boolean isEnabledNull = Boolean.TRUE.equals(contextNull.get("isPushFactorOptionEnabled"));
        System.out.println("Example 4 (null value, should be false): " + isEnabledNull);
    }
}
