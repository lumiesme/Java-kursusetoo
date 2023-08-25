package controllers.listeners;

import models.Model;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class only changes the default category value in the model
 */
public class CategoryItemChange implements ItemListener {

    private final Model model;

    /**
     * Constructor of a class that is executed at creation time. This application only once
     * @param model Model
     */
    public CategoryItemChange(Model model) {
        this.model = model;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // https://stackoverflow.com/questions/330590/why-is-itemstatechanged-on-jcombobox-is-called-twice-when-changed
        if(e.getStateChange() == ItemEvent.SELECTED) { // Without this check, two choices will occur in a row
            model.setSelectedCategory(e.getItem().toString()); // Set selected category for next new game
            // JOptionPane.showMessageDialog(null, e.getItem().toString()); // for testing
        }
    }
}
