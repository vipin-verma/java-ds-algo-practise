package org.example;
import java.util.*;

interface AllForOne {
    void incrementKey(String key);
    void decrementKey(String key);
    String getMaxKey();
    String getMinKey();
}

class AllForOneImpl implements AllForOne {
    private final Map<String, Integer> keyFrequency;
    private final TreeMap<Integer, Set<String>> freqMap;

    public AllForOneImpl() {
        keyFrequency = new HashMap<>();
        freqMap = new TreeMap<>();
    }

    @Override
    public void incrementKey(String key) {
        int freq = keyFrequency.getOrDefault(key, 0);
        keyFrequency.put(key, freq + 1);

        freqMap.computeIfAbsent(freq + 1, k -> new HashSet<>()).add(key);
        if (freq > 0) {
            freqMap.get(freq).remove(key);
            if (freqMap.get(freq).isEmpty()) freqMap.remove(freq);
        }
    }

    @Override
    public void decrementKey(String key) {
        if (!keyFrequency.containsKey(key)) return;

        int freq = keyFrequency.get(key);
        if (freq == 1) {
            keyFrequency.remove(key);
        } else {
            keyFrequency.put(key, freq - 1);
            freqMap.computeIfAbsent(freq - 1, k -> new HashSet<>()).add(key);
        }

        freqMap.get(freq).remove(key);
        if (freqMap.get(freq).isEmpty()) freqMap.remove(freq);
    }

    @Override
    public String getMaxKey() {
        return freqMap.isEmpty() ? "" : freqMap.lastEntry().getValue().iterator().next();
    }

    @Override
    public String getMinKey() {
        return freqMap.isEmpty() ? "" : freqMap.firstEntry().getValue().iterator().next();
    }
}

public class MinMax {
    public static void main(String[] args) {
        AllForOne obj = new AllForOneImpl();
        obj.incrementKey("apple");
        obj.incrementKey("banana");
        obj.incrementKey("apple");
        System.out.println("Max Key: " + obj.getMaxKey()); // apple
        System.out.println("Min Key: " + obj.getMinKey()); // banana
        obj.decrementKey("apple");
        System.out.println("Max Key: " + obj.getMaxKey()); // apple or banana
        System.out.println("Min Key: " + obj.getMinKey()); // apple or banana
    }
}

