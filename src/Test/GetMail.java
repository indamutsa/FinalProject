package Test;

public class GetMail implements Runnable
{
    private int startTime;

    public GetMail(int startTime)
    {
        this.startTime = startTime;
    }

    public void run()
    {
        try
        {
            Thread.sleep(startTime * 1000);
        }
        catch (InterruptedException e)
        {

        }
        System.out.println("\nChecking the Mail\n");
    }
}
