package Test;
import java.util.concurrent.locks.ReentrantLock;

public class PerfomSystemCheck implements Runnable
{
    private String checkWhat;
    ReentrantLock lock = new ReentrantLock();

    public PerfomSystemCheck(String checkWhat)
    {
        this.checkWhat = checkWhat;
    }
    @Override
    public void run()
    {
        lock.lock();
        System.out.println();
        lock.unlock();
    }
}
