package Test;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class Combo
{
    public static void main(String[] argv) throws Exception
    {
        String[] items = {"item1", "item2"};
        JComboBox cb = new JComboBox(items);
        cb.setEditable(true);

        MyItemListener actionListener = new MyItemListener();
        cb.addItemListener(actionListener);
    }
}

class MyItemListener implements ItemListener
{
    // This method is called only if a new item has been selected.
    public void itemStateChanged(ItemEvent evt)
    {
        JComboBox cb = (JComboBox) evt.getSource();

        Object item = evt.getItem();

        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            // Item was just selected
        } else if (evt.getStateChange() == ItemEvent.DESELECTED)
        {
            // Item is no longer selected
        }
    }
}