package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This program is responsible for run the GUI
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public class RunGUI
{
    /*We run the gui from here*/
    public static void main(String args[]) throws IOException
    {
        GraphicalInterface gui = new GraphicalInterface();

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI(gui);
            }
        });
    }

    /*This method creates the GUI*/
    private static void createAndShowGUI(GraphicalInterface gui)
    {
        // Create a JFrame and get its content pane
        JFrame theFrame = new JFrame(gui.getClass().getName() + " Application");

        // Instantiate the GUI, which is a JPanel
        gui.addComponents(theFrame);

        // Set the size of the frame and exit behavior
        theFrame.setPreferredSize(new Dimension(1200, 700));
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // show the frame
        theFrame.pack();
        theFrame.setVisible(true);
    }
}
