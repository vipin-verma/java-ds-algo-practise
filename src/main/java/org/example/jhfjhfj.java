/*
package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SFANotificationDataMain {

    public static void main(String[] args) {

        // Scenario 1: Minimal required fields only.
        SFANotificationData notification1 = new SFANotificationData.Builder("REQ001", new String[]{"token1", "token2"})
                .resourceURL("http://example.com/home")
                .accessDate(System.currentTimeMillis())
                .appDomain("exampleAppDomain")
                .clientAgent("Mozilla/5.0")
                .ipAdd("192.168.1.100")
                .user("user1")
                .expiryTime("600")
                .titleText("Minimal Test")
                .build();

        System.out.println("=== Scenario 1: Minimal Required Fields ===");
        System.out.println("Data Map:");
        printMap(notification1.getDataMap());
        System.out.println("Encrypted Payload (valid key/iv): "
                + notification1.getEncryptedPayload("dummyKey", "1234567890123456"));
        System.out.println("Encrypted Payload (empty key): "
                + notification1.getEncryptedPayload("", "1234567890123456"));
        System.out.println();

        // Scenario 2: All optional fields included.
        List<String> platformKeys = Arrays.asList("key1", "key2", "key3");
        SFANotificationData notification2 = new SFANotificationData.Builder("REQ002", new String[]{"tokenA", "tokenB"})
                .resourceURL("https://secure.example.com/path")
                .accessDate(System.currentTimeMillis())
                .appDomain("secureDomain")
                .clientAgent("Chrome/90.0")
                .ipAdd("10.0.0.1")
                .user("user2")
                .expiryTime("3600")
                .titleText("Full Test")
                .challengeData("challengeXYZ")
                .platformSecUrl("https://platform.example.com")
                .platformSecCid("CID002")
                .platformSecUid("UID002")   // Note: Due to bug in builder, this might set platformSecUrl.
                .platformSecAppId("APP002")
                .platformSecKeys(platformKeys)
                .pnId(12345)
                .version(1.2)
                .pnAuth("authXYZ")
                .build();

        System.out.println("=== Scenario 2: All Optional Fields Included ===");
        System.out.println("Data Map:");
        printMap(notification2.getDataMap());
        System.out.println("Encrypted Payload (valid key/iv): "
                + notification2.getEncryptedPayload("secureKey", "1234567890123456"));
        System.out.println();

        // Scenario 3: Optional fields are empty or whitespace.
        SFANotificationData notification3 = new SFANotificationData.Builder("REQ003", new String[]{"tokenX"})
                .resourceURL("http://test.example.com")
                .accessDate(System.currentTimeMillis())
                .appDomain("testDomain")
                .clientAgent("Safari/537.36")
                .ipAdd("127.0.0.1")
                .user("user3")
                .expiryTime("1200")
                .titleText("Optional Empty Test")
                .challengeData("   ")  // whitespace, will be ignored.
                .platformSecUrl("")      // empty, will be ignored.
                .platformSecCid(null)    // null, will be ignored.
                .platformSecAppId("   ") // whitespace, will be ignored.
                .platformSecUid("")      // empty, will be ignored.
                .build();

        System.out.println("=== Scenario 3: Optional Fields Empty or Whitespace ===");
        System.out.println("Data Map:");
        printMap(notification3.getDataMap());
        System.out.println("Encrypted Payload (valid key/iv): "
                + notification3.getEncryptedPayload("testKey", "1234567890123456"));
    }

    // Utility method to print Map contents.
    private static void printMap(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}

*/
/*
 * Dummy MFAUtil Class Implementation for Testing Purposes.
 * If you already have an MFAUtil implementation, you can remove or comment this out.
 *//*

*/
/*class MFAUtil {
    private static MFAUtil instance = new MFAUtil();

    public static MFAUtil getInstance() {
        return instance;
    }

    // Dummy encryption method: returns "encrypted:" prefixed with the payload.
    public String encrypt(String payload, String key, String iv) {
        if(key == null || key.trim().isEmpty() || iv == null || iv.trim().isEmpty()){
            return null;
        }
        return "encrypted:" + payload;
    }
}
System.out.println("Encrypted Payload (valid key/iv): "
        + notification1.getEncryptedPayload("dummyKey", "1234567890123456"));
...
System.out.println("Encrypted Payload (valid key/iv): "
        + notification2.getEncryptedPayload("secureKey", "1234567890123456"));
...
System.out.println("Encrypted Payload (valid key/iv): "
        + notification3.getEncryptedPayload("testKey", "1234567890123456"));

*//*

