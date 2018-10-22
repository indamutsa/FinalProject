package Test;
// Used to schedule when certain events should be triggered

import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

// Used to tell Java what unit of time I want to use

public class TestSystemThreads
{
    public static void main(String [] args)
    {
        addThreadsToPool();
    }

    public static void addThreadsToPool()
    {
        ScheduledThreadPoolExecutor eventPoolExecutor =  new ScheduledThreadPoolExecutor(5);
        eventPoolExecutor.scheduleAtFixedRate(new CheckSystemTime(), 0,2,SECONDS);
        eventPoolExecutor.scheduleAtFixedRate(new PerfomSystemCheck("Mail"), 0,2,SECONDS);
        eventPoolExecutor.scheduleAtFixedRate(new PerfomSystemCheck("Calendar"), 0,2,SECONDS);
        eventPoolExecutor.scheduleAtFixedRate(new CheckSystemTime(), 0,2,SECONDS);

        System.out.println("Number of threads: " + Thread.activeCount());
        Thread[] listOfThreads = new Thread[Thread.activeCount()];

        Thread.enumerate(listOfThreads);

        for (Thread i : listOfThreads)
            System.out.println("Thread: " + i.getName());

        for (Thread i : listOfThreads)
            System.out.println("Thread priority " + i.getPriority());
//        eventPoolExecutor.shutdown();

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {

        }
    }
}
