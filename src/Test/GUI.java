package Test;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GUI extends JPanel
{

    BufferedImage img;
    BufferedImage img1;

//    public void paint(Graphics g)
//    {
//        g.drawImage(img, 100, 20, 300, 300, null);
//    }

//    public void addComponents(JFrame theFrame)
//    {
//        JLabel label = new JLabel("Welocome at INDAMUTSA PIZZERIA Configuration");
//        label.setFont(new Font("Serif", Font.PLAIN, 20));
//
//        add(label);
//
//
//    }

    public GUI(JFrame aFrame)
    {
//        try
//        {
        JPanel panOuter = new JPanel(new BorderLayout());
        JPanel panLeft = new JPanel(new BorderLayout());
        panLeft.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panRight = new JPanel(new BorderLayout());
        panRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panBottom = new JPanel(); // default is FlowLayout
        panBottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panInput = new JPanel(new BorderLayout());
        panInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panConsole = new JPanel(new BorderLayout());

        Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border insideBorder = BorderFactory.createTitledBorder("The Console");
        Border theBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        panConsole.setBorder(theBorder);

        panInput.add(panLeft, BorderLayout.WEST);
        panInput.add(panRight, BorderLayout.EAST);
        panInput.add(panBottom, BorderLayout.SOUTH);

        panOuter.add(panInput, BorderLayout.NORTH);
        panOuter.add(panConsole, BorderLayout.CENTER);

        JLabel lblLeft = new JLabel("Label 1", JLabel.CENTER);
        JLabel lblRight = new JLabel("Label 2", JLabel.CENTER);

        JTextField txtLeft = new JTextField(10);
        JTextField txtLright = new JTextField(10);

        JButton btnBottom = new JButton("Press it!");

        JTextArea txtConsole = new JTextArea(5, 10);

        panLeft.add(lblLeft, BorderLayout.NORTH);
        panLeft.add(txtLeft, BorderLayout.CENTER);

        panRight.add(lblRight, BorderLayout.NORTH);
        panRight.add(txtLright, BorderLayout.CENTER);

        panBottom.add(btnBottom);

        panConsole.add(txtConsole, BorderLayout.CENTER);
        aFrame.setContentPane(panOuter);

/*            img = ImageIO.read(new File("m.png"));
            Image newImage = img.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            JLabel wIcon = new JLabel(new ImageIcon(newImage));
//            wIcon.setLocation(20, 100);
            add(wIcon);

            img1 = ImageIO.read(new File("pizza.jpg"));
            Image newImage1 = img1.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
            JLabel wIcon1 = new JLabel(new ImageIcon(newImage1));
//            wIcon.setLocation(100, 300);
            add(wIcon1);

            Container pane = aFrame.getContentPane();

            JLabel label = new JLabel("dhfkshfksdfhkshfksdhfklsdhfkhsdklfhksdhfklsdfhsl");
            label.setPreferredSize(new Dimension(500, 30));
            pane.add(label, BorderLayout.SOUTH);*/


//        } catch (IOException e)
//        {
//        }
    }

    public static void main(String[] args)
    {

        JFrame f = new JFrame("Load Image Sample");
        f.setPreferredSize(new Dimension(1200, 1000));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

//        new GUI().addComponents(f);
        f.add(new GUI(f));
        f.pack();
        f.setVisible(true);
    }
}