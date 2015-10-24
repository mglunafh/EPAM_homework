package workerthread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author dev64
 */
public class WorkerThreadWithBlockingQueue  {

    private BlockQueue<Runnable> tasks = new BlockQueue<>();
    private Thread thread;
    
    private final class TaskRunner implements Runnable {

        @Override
        public void run() {
            while (true ) {
                Runnable task = tasks.take();
                task.run();
            }
        }
    }
    
    public WorkerThreadWithBlockingQueue() {
        thread = new Thread(new TaskRunner());
        thread.start();
    }
    
    
    public void execute(Runnable r) {
        tasks.put(r);
    }
    
    public static void main(String[] args) {
        
        System.out.println("start");
        
        WorkerThreadWithBlockingQueue worker = new WorkerThreadWithBlockingQueue();
        
        worker.execute(new Runnable() {

            @Override
            public void run() {        
                System.out.println("Hello from" + Thread.currentThread());
            }
        });
        
    }
    
}
