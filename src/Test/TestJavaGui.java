package Test;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestJavaGui
{
    public static void main(String args[]) throws IOException
    {
        JavaGui gui = new JavaGui();

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI(gui);
            }
        });
    }

    private static void createAndShowGUI(JavaGui gui)
    {
        // Create a JFrame and get its content pane

        JFrame theFrame = new JFrame(gui.getClass().getName() + " Application");
        Container container = theFrame.getContentPane();

        // Instantiate the GUI, which is a JPanel
        gui.addComponents(theFrame);

        // Set the size of the frame and exit behavior
        theFrame.setPreferredSize(new Dimension(600, 400));
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the GUI to the frame
//        container.add(gui);

        // show the frame
        theFrame.pack();
        theFrame.setVisible(true);
    }
}
