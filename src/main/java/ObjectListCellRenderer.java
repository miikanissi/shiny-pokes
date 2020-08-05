
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
/*************************************
Author: Miika Nissi
Date started: 14.6.2020
Date submitted: 
Final Project for Java Programming class AVE1017/OJ/3003
*************************************/

// Code sample modified from https://stackoverflow.com/questions/20155122/is-there-any-way-to-add-objects-to-a-jcombobox-and-assign-a-string-to-be-shown
// Changes the default cell renderer to use the name variable of an object
public class ObjectListCellRenderer extends DefaultListCellRenderer
{
    /**
     *
     * @param list
     * @param id
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
     public Component getListCellRendererComponent(
                                   JList list,
                                   Object id,
                                   int index,
                                   boolean isSelected,
                                   boolean cellHasFocus) 
     {
        if (id instanceof Pokemon) 
        {
            id = ((Pokemon)id).getName();
        }
        super.getListCellRendererComponent(list, id, index, isSelected, cellHasFocus);
        return this;
    }
}
