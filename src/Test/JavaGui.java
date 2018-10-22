package Test;

import javax.swing.*;
import java.awt.*;

public class JavaGui
{

    private JLabel welcome;
    private JButton uploadFile;
    private JComboBox petList;


    public JavaGui()
    {
        welcome = new JLabel("Welcome to\nINDAMUTSA PIZZERIA Configuration!");
        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        uploadFile = new JButton("Upload a file");
        JTextField textField = new JTextField("SDKFGSDJFGKSDF");
        //Create the combo box, select the item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox petList = new JComboBox(petStrings);
    }

    // This method adds the components to the frame
    public void addComponents(JFrame theFrame)
    {
        Container contain = theFrame.getContentPane();
        contain.setLayout(new BorderLayout());
        contain.setBackground(Color.white);

        JPanel wins = new JPanel();
        wins.setLayout(new FlowLayout());
        wins.add(welcome);


        JPanel keyboard = new JPanel();
        keyboard.setLayout(new GridLayout(3, 5));
        keyboard.add(welcome);

        JPanel guess = new JPanel();
        guess.setLayout(new FlowLayout());
//        guess.add(petList);


        JPanel hang = new JPanel();
        hang.add(welcome);


        // Adding the panels to the container
        contain.add(wins, BorderLayout.EAST);
        contain.add(guess, BorderLayout.NORTH);
        contain.add(hang, BorderLayout.WEST);
        contain.add(keyboard, BorderLayout.SOUTH);

    }
}
