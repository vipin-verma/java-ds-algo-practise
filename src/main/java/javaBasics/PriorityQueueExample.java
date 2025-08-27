package javaBasics;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample{

          public static void main (String [] args)
          {
              PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

              pq.add(3);
              pq.add(4);
              pq.add(7);
              pq.add(1);
              pq.offer(23);


              while(!pq.isEmpty())
              {
                  //System.out.println(pq.poll());
                  System.out.print(pq.remove());
              }


//PriorityQueue<Integer> pq = new PriorityQueue<>();

              // Add elements
              pq.add(30);
              pq.offer(10);
              pq.add(20);

              System.out.println("Queue: " + pq);

              // Access elements
              System.out.println("Peek (highest priority): " + pq.peek());

              // Remove elements
              System.out.println("Poll (remove highest priority): " + pq.poll());
              System.out.println("After Poll: " + pq);

              // Check size
              System.out.println("Size: " + pq.size());
              System.out.println("Contains 20? " + pq.contains(20));

              // Iterate
              for (int num : pq) {
                  System.out.println("Iterating: " + num);
              }


          }

}
