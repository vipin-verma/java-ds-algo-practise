package questions;


import org.example.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Order {

    private static int idCounter = 1;
    private final int orderId ;

    public Order() {
        this.orderId = idCounter++;
    }

    public int getOrderId()
    {
        return orderId;
    }
}

class OrderProducer  implements  Runnable {

    private BlockingQueue<Order> queue ;

    public OrderProducer (BlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            for (int i=0; i< 10 ; i++){
                Order order = new Order();
                System.out.println("order placed " + order.getOrderId());

                queue.put(order);
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class OrderConsumer implements  Runnable{

    private BlockingQueue<Order> queue ;

    public OrderConsumer (BlockingQueue<Order> queue)
    {
        this.queue = queue;
    }



    @Override
    public void run() {
 try {
     while (true) {
         Order order = queue.take();
         System.out.println("order processed " + order.getOrderId());
         Thread.sleep(1000);
     }

 }  catch (InterruptedException e) {
     throw new RuntimeException(e);
 }

    }
}


public class ProducerConsumer {

    public static  void main (String [] args )
    {

        BlockingQueue<Order> queue = new LinkedBlockingQueue<>(5);

        Thread producer = new Thread(new OrderProducer(queue));

        Thread consumer = new Thread(new OrderConsumer(queue));

        Thread consumer1 = new Thread(new OrderConsumer(queue));

        producer.start();
        consumer.start();
        consumer1.start();
    }







}





