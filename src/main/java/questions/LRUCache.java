package questions;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
     private final  int capacity ;
     private final Map<Integer , Node > cache ;
    private final DoublyLinkedList dll;


    static class Node {
          int key , value ;
          Node prev , next ;
          Node (int k , int v )
          {
               key = k;
               value = v;
          }
     }

     static class DoublyLinkedList {
         Node head , tail;

         DoublyLinkedList (){
              head = new Node (0,0);
              tail = new Node (0,0);
              head.next = tail;
              tail.prev = head;
         }

         void addFirst (Node node){
              node.next = head.next;
              node.prev = head ;
              head.next.prev = node ;
              head.next = node ;
         }

         void remove (Node node){
              node.prev.next = node.next;
              node.next.prev = node.prev;


         }

         Node removeLast (){
               Node last = tail.prev ;
               remove(last);
               return last;
         }

     }

     public LRUCache (int capacity)
     {
         this.capacity = capacity;
         this.cache = new HashMap<>();
         this.dll = new DoublyLinkedList();
     }


     public int get (int key )
     {
         if (!cache.containsKey(key)) return -1;
         Node node = cache.get(key);
         dll.remove(node);
         dll.addFirst(node);
         return node.value ;

     }

     public void put (int key , int value){
        if (cache.containsKey(key)){
            Node node = cache.get(key);
            node.value = value ;
            dll.remove(node);
            dll.addFirst(node);
        }else {
                if (cache.size() == capacity )
                {
                    Node last = dll.removeLast();
                    cache.remove(last.key);
                }

                Node newNode = new Node(key , value );
                dll.addFirst(newNode);
                cache.put(key, newNode);

        }



     }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);

        lru.put(1, 10);
        lru.put(2, 20);
        lru.put(3, 30);
        System.out.println(lru.get(1)); // 10 (recently used)

        lru.put(4, 40); // removes key=2
        System.out.println(lru.get(2)); // -1 (not found)

        lru.put(5, 50); // removes key=3
        System.out.println(lru.get(3)); // -1 (not found)

        System.out.println(lru.get(4)); // 40
        System.out.println(lru.get(5)); // 50
    }


}
