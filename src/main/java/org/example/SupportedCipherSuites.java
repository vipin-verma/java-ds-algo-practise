package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

import javax.net.ssl.SSLSocketFactory;

public class SupportedCipherSuites {

    public static void main(String[] args) {
        // Get the default SSL socket factory
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        // Get the list of supported cipher suites
        String[] supportedCipherSuites = factory.getSupportedCipherSuites();

        // Print the supported cipher suites
        System.out.println("Supported Cipher Suites for JDK 21:");
        for (String cipher : supportedCipherSuites) {
            System.out.println(cipher);
        }
    }
}

