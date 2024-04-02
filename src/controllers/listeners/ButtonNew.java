package controllers.listeners;

import models.Model;
import views.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonNew implements ActionListener {
    private final Model model;
    private final View view;

    /**
     * New Game button constructor.
     * @param model Model
     * @param view View
     */
    public ButtonNew(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Here is the action that happens when the New Game button is pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Reset game state
        model.setImageId(0); // Reset image id
        ButtonSend.guessedLetters.clear(); // Clear guessed letters list
        view.getRealDateTime().stop(); // Stop real time
        view.getGameTime().stopTimer(); // Stop gameTime if running
        view.getGameTime().setSeconds(0); // Reset timer seconds
        view.getGameTime().setMinutes(0); // Reset timer minutes
        model.setCountMissedWords(0); // Reset count of missed words

        // Set up new game
        view.hideNewButtons(); // Set access to buttons and text field
        view.getTxtChar().requestFocus(); // After pressing New Game, the input box becomes active
        view.setNewImage(0); // Set initial image
        String selectedCategory = view.getCmbCategory().getSelectedItem().toString();
        model.generatedWordFromCategoriesList(selectedCategory); // Generate new word
        String wordOfNew = model.addSpaceBetween(String.valueOf(model.getWordNewOfLife()));
        view.getLblResult().setText(wordOfNew); // Display new word
    }
}
