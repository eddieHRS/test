package threads;

/**
 * @title: MyThreadTest
 * @Author eddie
 * @Date: 2021/3/9 16:44
 * @Version 1.0
 */
public class MyThreadTest {
    public static void main(String[] args) {
        // 继承 Thread
        MyThreadExtendThread myThreadExtendThread1 = new MyThreadExtendThread();
        myThreadExtendThread1.start();

        // 实现 Runnable 接口
        MythreadFromRunnable mythreadFromRunnable = new MythreadFromRunnable();
        Thread thread = new Thread(mythreadFromRunnable);
        thread.start();
    }
}
