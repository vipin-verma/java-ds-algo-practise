package org.example;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class VerifiedPushConfigUtil {

    private static VerifiedPushConfig verifiedPushConfig; // Holds the parsed JSON configuration

    /**
     * Load JSON configuration from the provided file path.
     *
     * @param jsonFilePath Path to the JSON file
     * @throws IOException if the file cannot be read or parsed
     */
   /* public static void loadConfig(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON file into the VerifiedPushConfig object
            verifiedPushConfig = objectMapper.readValue(new File(jsonFilePath), VerifiedPushConfig.class);
            System.out.println("Verified Push Config loaded successfully.");
        } catch (IOException e) {
            System.err.println("Failed to load Verified Push Config from: " + jsonFilePath);
            throw e;
        }
    }*/

    /**
     * Get the full Verified Push Configuration.
     *
     * @return VerifiedPushConfig object
     * @throws IllegalStateException if the configuration is not loaded
     */
    public static VerifiedPushConfig getConfig() {
        if (verifiedPushConfig == null) {
            throw new IllegalStateException("Verified Push Config has not been loaded. Please call loadConfig() first.");
        }
        return verifiedPushConfig;
    }

    /**
     * Get a specific factor by its ID.
     *
     * @param factorId The ID of the factor to fetch
     * @return The factor matching the provided ID
     * @throws IllegalArgumentException if no factor matches the provided ID
     */
    public static VerifiedPushConfig.Factor getFactorById(int factorId) {
        if (verifiedPushConfig == null) {
            throw new IllegalStateException("Verified Push Config has not been loaded. Please call loadConfig() first.");
        }

        // Fetch the factor with the given ID
        Optional<VerifiedPushConfig.Factor> factor = verifiedPushConfig.getFactors().stream()
                .filter(f -> f.getFactor_id() == factorId)
                .findFirst();

        // Return the factor or throw an exception if not found
        return factor.orElseThrow(() ->
                new IllegalArgumentException("Factor with ID " + factorId + " not found in the configuration."));
    }
}
