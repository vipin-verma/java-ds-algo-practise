package questions;

import java.util.LinkedHashMap;
import java.util.Map;

 class LRUCacheMap <K, V>  extends LinkedHashMap<K , V> {
     private final int capacity;

     public LRUCacheMap(int capacity) {
         super(capacity, .75f, true);
         this.capacity = capacity;

     }


     @Override
     protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
         return size() > capacity;
     }

 }
   public class  LRUCacheMain {

     public static void main (String [] args ){
         LRUCacheMap<Integer , String > cache = new LRUCacheMap<>(3);

         cache.put(1, "A");
         cache.put(2, "B");
         cache.put(3, "C");
         System.out.println(cache);
         cache.put(4, "D");
         System.out.println(cache);
     }




   }






