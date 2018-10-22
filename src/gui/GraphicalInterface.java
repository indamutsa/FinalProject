package gui;

import client.Client;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * This program is responsible for Graphical interface
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */




/*This class  is responsible of Graphical interface*/
class GraphicalInterface extends JFrame
{
    Client client;
    private JLabel welcome;
    private JButton uploadFile;
    private JButton refresh;

    private JButton updateBasePrice;
    private JButton button;
    private int port;
    private int index;

    private JLabel list;
    private JTextField textField;
    private JTextField textField1;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    private BufferedImage img;
    private BufferedImage img1;
    private JLabel icon;
    private JLabel icon1;
    private Image newImage;
    private Image newImage1;
    private JComboBox pizzaList;
    private ArrayList<String> arrayList;

    /*The constructor where i am initializing everything*/
    public GraphicalInterface()
    {
        try
        {
            port = 5000;
            index = 0;
            client = new Client(port);
            /*Initializing our components*/
            welcome = new JLabel("<html><center><h1>Welcome</h1></center>\n" +
                    "<center><i>INDAMUTSA PIZZERIA Configuration!</i></center></html>");


            list = new JLabel("<html><center><i>List of pizzeria!</i></center></html>");
            uploadFile = new JButton("Upload a file");
            refresh = new JButton("Refresh the page");
            updateBasePrice = new JButton("Update base price");

            pizzaList = new JComboBox();
            textField = new JTextField("Your file path goes here");
            textField1 = new JTextField("0.00");
            textArea = new JTextArea(50, 60);

            img = ImageIO.read(new File("m.png"));
            newImage = img.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            icon = new JLabel(new ImageIcon(newImage));

            img1 = ImageIO.read(new File("pizza.jpg"));
            newImage1 = img1.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            icon1 = new JLabel(new ImageIcon(newImage1));

            /*If the database is null, we notify the client*/
            if (client.pizzeriaList() instanceof String)
                JOptionPane.showMessageDialog(null, "PizzeriaDatabase empty, please update it", "InfoBox: " + "List status", JOptionPane.INFORMATION_MESSAGE);

            else if (!(client.pizzeriaList() instanceof String) && client.pizzeriaList() != null)
            {
                arrayList = (ArrayList) client.pizzeriaList();
                pizzaList = new JComboBox(arrayList.toArray());
                textArea.setText(client.pizzaText(index).toString());
            }

            /*We display the pizza when selected from the combo box*/
            pizzaList.addItemListener(new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    index = pizzaList.getSelectedIndex();
                    textArea.setText(client.pizzaText(index).toString());
                }
            });

            /*Listener for button clicks*/
            ButtonListener buttonListener = new ButtonListener();
            uploadFile.addActionListener(buttonListener);
            refresh.addActionListener(buttonListener);
            updateBasePrice.addActionListener(buttonListener);

            textArea.setLineWrap(true);
            textArea.setEditable(false);
            scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(60, 60, 500, 320);
            scrollPane.setSize(500, 320);


            System.out.println(index);

        } catch (IOException e)
        {

        }
    }

    // This method adds the components to the frame
    public void addComponents(JFrame theFrame)
    {
        /*This is our container which container all our panels*/
        Container container = theFrame.getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.white);

        /*Initiliazing panels and setting up the sizes*/
        JPanel panel = new JPanel();
        JPanel workingPanel = new JPanel();
        workingPanel.setBounds(320, 200, 600, 400);
        workingPanel.setBackground(new Color(255, 255, 153));
        workingPanel.setBorder(LineBorder.createBlackLineBorder());
        panel.setLayout(null);
        workingPanel.setLayout(null);



        /*Placing the panel where we want them to be in our panel*/
        textField.setBounds(545, 120, 400, 25);
        textField1.setBounds(525, 620, 400, 25);
        uploadFile.setBounds(320, 120, 220, 25);
        refresh.setBounds(517, 160, 200, 25);
        updateBasePrice.setBounds(320, 620, 200, 25);
        welcome.setBounds(400, 20, 600, 75);
        welcome.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        list.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        icon.setBounds(50, 200, 200, 200);
        icon1.setBounds(950, 200, 200, 200);
        pizzaList.setBounds(200, 25, 200, 25);
        list.setBounds(200, 3, 200, 25);
        textArea.setBounds(60, 60, 500, 320);

        /*Removes the textfield when one starts write*/
        textField.addFocusListener(new FocusAdapter()
        {
            public void focusGained(FocusEvent e)
            {
                JTextField source = (JTextField) e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });

        /*Removes the textfield when one starts write*/
        textField1.addFocusListener(new FocusAdapter()
        {
            public void focusGained(FocusEvent e)
            {
                JTextField source = (JTextField) e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });

        /*We add components to panels*/
        panel.add(welcome);
        panel.add(uploadFile);
        panel.add(textField);
        panel.add(icon);
        panel.add(icon1);
        panel.add(updateBasePrice);
        panel.add(textField1);
        workingPanel.add(list);
        workingPanel.add(pizzaList);
        workingPanel.add(scrollPane);
        panel.add(refresh);
        panel.add(workingPanel);


        container.add(panel);

    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            /*Here we upload upon click*/
            if (e.getSource() == uploadFile)
            {
                String str = client.uploadFile(textField.getText());

                try
                {
                    if (!(client.pizzeriaList() instanceof String))
                    {
                        if (str.trim().equalsIgnoreCase("PizzeriaServer response: File uploaded successfully"))
                        {
                            /*Setting up the display*/
                            arrayList = (ArrayList) client.pizzeriaList();
                            pizzaList.setModel(new DefaultComboBoxModel(arrayList.toArray()));
                            textArea.setText(client.pizzaText(index).toString());
                            JOptionPane.showMessageDialog(null, str, "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);
                        } else if (str.trim().equalsIgnoreCase("PizzeriaServer response: We already have this pizza in our database"))
                            JOptionPane.showMessageDialog(null, str, "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null, str, "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);

                    } else
                        JOptionPane.showMessageDialog(null, str, "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);


                } catch (Exception exc)
                {
                    String infoMessage = client.uploadFile(textField.getText());
                    String titleBar = "Server off";

                    JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
                }
                /*The listener to update price*/
            } else if (e.getSource() == updateBasePrice)
            {
                try
                {
                    if (textField1.getText().matches("[0.]+"))
                    {
                        JOptionPane.showMessageDialog(null, "O is not valid", "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    if (textField1.getText().matches("[\\d.]+"))
                    {
                        client.updateBasePrice(index + 1, Double.valueOf(textField1.getText()));
                        if (!(client.pizzeriaList() instanceof String))
                        {
                            /*Setting up the display*/
                            arrayList = (ArrayList) client.pizzeriaList();
                            pizzaList.setModel(new DefaultComboBoxModel(arrayList.toArray()));
                            textArea.setText(client.pizzaText(index).toString());
                            JOptionPane.showMessageDialog(null, "Price successfully updated", "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "Price not updated, Check the server and upload a file", "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "Incorrect price provided", "InfoBox: " + "Price update", JOptionPane.INFORMATION_MESSAGE);


                } catch (Exception ex)
                {
                    String info = "Please turn on the server";
                    String title = "Server off";

                    JOptionPane.showMessageDialog(null, info, "InfoBox: " + title, JOptionPane.INFORMATION_MESSAGE);
                }
            }

            else if (e.getSource() == refresh)
            {
                /*Setting up the display*/
                arrayList = (ArrayList) client.pizzeriaList();
                pizzaList.setModel(new DefaultComboBoxModel(arrayList.toArray()));
                textArea.setText(client.pizzaText(index).toString());
            }
        }
    }
}


