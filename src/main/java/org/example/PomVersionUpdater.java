package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class PomVersionUpdater {

    public static void main(String[] args) {
        String pomFilePath = "/Users/vipinverma/IdeaProjects/ngam_1/ngam/parent/pom.xml";
        String idmDepFilePath = "/Users/vipinverma/IdeaProjects/ngam_1/ngam/buildscripts/idmDepValues-12.2.1.4.0-190915.2042.456.txt";

        // Step 1: Read the idmDep file and store key-value pairs in a map
        Map<String, String> keyValueMap = readIdmDepFile(idmDepFilePath);

        // Step 2: Replace placeholders in the POM file
        replacePlaceholdersInPom(pomFilePath, keyValueMap);
    }

    private static Map<String, String> readIdmDepFile(String filePath) {
        Map<String, String> keyValueMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    keyValueMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyValueMap;
    }

    private static void replacePlaceholdersInPom(String pomFilePath, Map<String, String> keyValueMap) {
        try {
            Path path = Paths.get(pomFilePath);
            String content = new String(Files.readAllBytes(path));

            // Iterate over the key-value map and replace placeholders in the POM file content
            for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
                String placeholder = "${" + entry.getKey() + "}";
                content = content.replace(placeholder, entry.getValue());
            }

            // Write the updated content back to the POM file
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
