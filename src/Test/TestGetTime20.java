package Test;

public class TestGetTime20
{
    public static void main(String [] args)
    {
        Thread getTime = new GetTime20();
        Runnable getMail = new GetMail(10);
        Runnable getMailAgain = new GetMail(23);

        new Thread(getMail).start();
        new Thread(getMailAgain).start();

        getTime.start();
    }

}
