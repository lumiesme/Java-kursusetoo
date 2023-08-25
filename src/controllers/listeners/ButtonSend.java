package controllers.listeners;
import java.util.HashSet;

import models.datastructures.DataScores;
import models.Model;
import views.View;
import models.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * Klass nupu Saada täht jaoks
 */
public class ButtonSend implements ActionListener {
    /**
     * Mudel
     */
    private Model model;
    /**
     * View
     */
    private View view;
    /**
     * Database
     */
    private Database database;

    private int getImageId = 0;

    // private void setImageId() { view.setNewImage(getImageId); }

    public static HashSet<Character> guessedLetters = new HashSet<>();

    /**
     * Konstuktor
     *
     * @param model Model
     * @param view  View
     */
    public ButtonSend(Model model, View view) {
        this.model = model;
        this.view = view;
        this.database = new Database(model);  // for the database
    }

    /**
     * Kui kliikida nupul Saada täht
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        view.getTxtChar().requestFocus();
        String enteredChars = view.getTxtChar().getText().toUpperCase();
        // if there aren't letter in the letterbox, then return
        if (enteredChars.isEmpty()) {
            return;
        }
        char guessedLetter = enteredChars.charAt(0);
        //if missed letters contains the entered chars then change the picture
        if (guessedLetters.contains(guessedLetter)) {
            model.getMissedLetters().add(enteredChars);
            if (model.getImageId() < 11) {
                model.setImageId(model.getImageId() + 1);
                view.setNewImage(model.getImageId());
            }
            view.getLblError();
        } else {
            guessedLetters.add(guessedLetter);
            String guessWord = model.getWordToGuess();
            char[] guessList = guessWord.toCharArray();
            boolean correct = true;
            for (int i = 0; i < guessList.length; i++){
                if (guessList[i] == guessedLetter){
                    model.getWordNewOfLife().setCharAt(i, guessedLetter);
                    view.getLblResult().setText(model.addSpaceBetween(model.getWordNewOfLife().toString()));
                    correct = false;
                }
            }
            if (correct) {
                model.getMissedLetters().add(enteredChars);
                model.setImageId(model.getImageId() + 1); // Update the image ID
                view.setNewImage(model.getImageId()); // Set the new image
                view.getLblError();
            }
        }
        model.setCountMissedWords(model.getMissedLetters().size());
        view.getLblError().setText("Wrong: " + model.getCountMissedWords() + " letter(s) " + model.getMissedLetters());
        view.getTxtChar().setText("");
        if (!model.getWordNewOfLife().toString().contains("_")) {
            view.getGameTime().stopTimer();
            model.askPlayerName();
            database.insertScoreToTable(view);
            guessedLetters.clear();
            view.getRealDateTime().start();
            view.showNewButton();
            view.getLblResult().setText("L E T ' S  P L A Y");
        }
        if (!(model.getCountMissedWords() < 11)) {
            view.getGameTime().stopTimer();
            view.getGameTime().setRunning(false);
            JOptionPane.showMessageDialog(null, "Too many wrong letters", "Game Over", JOptionPane.PLAIN_MESSAGE);
            view.getRealDateTime().start();
            view.getLblResult().setText("L E T ' S  P L A Y");
            view.showNewButton();
        }
    }
}