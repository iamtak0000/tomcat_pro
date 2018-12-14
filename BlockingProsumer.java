import java.util.Random;  
import java.util.concurrent.ArrayBlockingQueue;  
import java.util.concurrent.LinkedBlockingQueue;  
import java.util.concurrent.BlockingQueue;  
 
 
public class BlockingProsumer {  
    //private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);  
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(3);  
      
    public static void main(String[] args) {  
        Consumer c1 = new Consumer("1", queue); c1.start();  
        //Consumer c2 = new Consumer("2", queue); c2.start();  
        //Consumer c3 = new Consumer("3", queue); c3.start();  
        //Consumer c4 = new Consumer("4", queue); c4.start();  
        //Consumer c5 = new Consumer("5", queue); c5.start();  
        //Consumer c6 = new Consumer("6", queue); c6.start();  
        //Consumer c7 = new Consumer("7", queue); c7.start();  
        //Consumer c8 = new Consumer("8", queue); c8.start();  
        //Consumer c9 = new Consumer("9", queue); c9.start();  
          
        Producer p1 = new Producer(queue);  p1.start();  
    }  
      
    // 생산자.   
    static class Producer extends Thread {  
        // INDEX  
        private volatile static int i = 1;  
          
        private BlockingQueue<Integer> queue;  
          
        public Producer(BlockingQueue<Integer> queue) {  
            this.queue = queue;  
        }  
          
        public void run() {  

            // 임의의 시간마다 데이터를 넣어 준다.  
            //while(true) {  
            for(int idx =0; idx <= 100; idx++) {
                try {  
                    Thread.sleep(new Random().nextInt(500));  
                    // 수정사항 - offer에서 put으로 변경
                    // 데이터를 넣고 나면 알아서 notify시켜 준다. 
                    queue.put(i++); 
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
  
            }  
        }  
    }  
      
      
    // 소비자   
    static class Consumer extends Thread {  
        private BlockingQueue<Integer> queue;  
        private String name;  
        public Consumer(String name, BlockingQueue<Integer> queue) {  
            this.name = name;  
            this.queue = queue;  
        }  
          
        public void run() {  

            //while ( true ) {  
            for(int idx =0; idx <= 100; idx++) {
                try {  
                    // queue에 data가 없으면 알아서 wait하고 있다.  
                    Integer index = queue.take();  
                    System.err.println("Consumer : " + name + "\tIndex : " + index);  

                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  

        }  
    }  
      
}  
