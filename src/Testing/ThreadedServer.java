package Testing;

public class ThreadedServer
{
    public static void main(String [] args)
    {
        new Server1(6333).start();
    }
}
