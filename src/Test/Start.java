package Test;

import javax.swing.*;
import java.awt.*;

public class Start
{
    public static void main(String [] args)
    {
        JFrame window = new JFrame("Position");
        window.setVisible(true);
        window.setPreferredSize(new Dimension(1200,500));
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        window.add(panel );

        JButton button = new JButton("Compile");
        button.setBounds(500,400, 120,35);
        panel.add(button);

    }
}
