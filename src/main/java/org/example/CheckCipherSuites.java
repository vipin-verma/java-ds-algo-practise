package org.example;

import javax.net.ssl.SSLSocketFactory;
import java.util.Arrays;
import java.util.List;

public class CheckCipherSuites {

    public static void main(String[] args) {
        // List of cipher suites to check
        List<String> cipherSuitesToCheck = Arrays.asList(
                "TLS_RSA_WITH_AES_128_CBC_SHA", // 0x2f
                "TLS_RSA_WITH_AES_256_CBC_SHA", // 0x35
                "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", // 0xc013
                "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA" // 0xc014
        );

        // Get the default SSL socket factory
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        // Get the list of supported cipher suites
        String[] supportedCipherSuites = factory.getSupportedCipherSuites();

        // Check each specified cipher suite
        System.out.println("Checking specified cipher suites:");
        for (String cipher : cipherSuitesToCheck) {
            if (Arrays.asList(supportedCipherSuites).contains(cipher)) {
                System.out.println(cipher + " is supported.");
            } else {
                System.out.println(cipher + " is not supported or is disabled.");
            }
        }
    }
}
