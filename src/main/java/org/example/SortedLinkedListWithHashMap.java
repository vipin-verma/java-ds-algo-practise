package org.example;

import java.util.HashMap;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SortedLinkedListWithHashMap {
    private Node head;
    private HashMap<Integer, Node> nodeMap;

    public SortedLinkedListWithHashMap() {
        head = null;
        nodeMap = new HashMap<>();
    }

    // Search for an element
    public boolean search(int target) {
        return nodeMap.containsKey(target);
    }

    // Insert a new element while maintaining sorted order
    public void insert(int value) {
        Node newNode = new Node(value);

        // Insert into the linked list
        if (head == null || head.data >= value) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.data < value) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        // Add to HashMap
        nodeMap.put(value, newNode);
    }

    // Delete an element
    public boolean delete(int value) {
        Node nodeToDelete = nodeMap.get(value);
        if (nodeToDelete == null) {
            return false; // Element not found
        }

        // Remove from linked list
        if (head == nodeToDelete) {
            head = head.next;
        } else {
            Node current = head;
            while (current.next != nodeToDelete) {
                current = current.next;
            }
            current.next = nodeToDelete.next;
        }

        // Remove from HashMap
        nodeMap.remove(value);
        return true;
    }

    // Display the linked list
    public void display() {
        Node current = head;
        System.out.print("Linked List: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        SortedLinkedListWithHashMap list = new SortedLinkedListWithHashMap();
        int[] values = {1, 3, 5, 7, 9};
        for (int value : values) {
            list.insert(value);
        }

        list.display();

        int targetValue = 5;
        if (list.search(targetValue)) {
            System.out.println(targetValue + " is in the list.");
        } else {
            System.out.println(targetValue + " is not in the list.");
        }

        // Delete an element
        list.delete(5);
        list.display();

        // Search for the deleted element
        if (list.search(targetValue)) {
            System.out.println(targetValue + " is in the list.");
        } else {
            System.out.println(targetValue + " is not in the list.");
        }
    }
}
