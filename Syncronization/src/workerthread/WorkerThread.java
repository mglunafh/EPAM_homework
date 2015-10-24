package workerthread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author dev64
 */
public class WorkerThread  {

    private Queue<Runnable> tasks = new LinkedList<>();
    private Thread thread;
    
    private final class TaskRunner implements Runnable {

        @Override
        public void run() {
            while (true ) {        
                Runnable task;
                synchronized(tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            //ignore
                        }
                    }
                    task = tasks.poll();
                }
                if (task != null)
                    task.run();
            }
        }
    }
    
    public WorkerThread() {
        thread = new Thread(new TaskRunner());
        thread.start();
    }
    
    
    public void execute(Runnable r) {
        synchronized(tasks) {
            tasks.offer(r);
            tasks.notify();
        }
    }
    
    public static void main(String[] args) {
        
        System.out.println("start");
        
        
        WorkerThread worker = new WorkerThread();
        
        worker.execute(new Runnable() {

            @Override
            public void run() {        
                System.out.println("Hello from" + Thread.currentThread());
            }
        });
    }
    
}
