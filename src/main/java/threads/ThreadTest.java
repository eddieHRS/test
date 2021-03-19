package threads;

/**
 * @title: ThreadTest
 * @Author eddie
 * @Date: 2021/3/11 11:44
 * @Version 1.0
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread mythread = new Thread(){
            public void run(){
                System.out.println("i am from thread");
            }
        };
        mythread.start();
        Thread.yield();
        System.out.println("i am from main");
        mythread.join();
    }
}
