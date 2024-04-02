package controllers.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.JOptionPane;
import models.Database;
import models.Model;
import views.View;

public class ButtonSend implements ActionListener {
    private Model model;
    private View view;
    private Database database;
    private int getImageId = 0;
    public static HashSet<Character> guessedLetters = new HashSet<>();

    public ButtonSend(Model model, View view) {
        this.model = model;
        this.view = view;
        this.database = new Database(model);

        model.setImageId(model.getImageId() + 1);
        model.setCountMissedWords(model.getCountMissedWords() + 1); // Increment the count of wrong letters

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.getTxtChar().requestFocus();
        String enteredChars = view.getTxtChar().getText().toUpperCase();
        if (enteredChars.isEmpty()) {
            return;
        }
        char guessedLetter = enteredChars.charAt(0);
        if (!Character.isLetter(guessedLetter)) {
            JOptionPane.showMessageDialog(null, "Please enter letters only.", "Error", JOptionPane.ERROR_MESSAGE);
            view.getTxtChar().setText("");
            return;
        }

        boolean correct = checkGuessedLetter(guessedLetter);

        if (!correct) {
            model.setImageId(model.getImageId() + 1);
            model.setCountMissedWords(model.getCountMissedWords() + 1); // Increment the count of wrong letters
            if (!guessedLetters.contains(guessedLetter)) {
                model.getMissedLetters().add(enteredChars);
                guessedLetters.add(guessedLetter);
                updateErrorLabel();
            }
            view.setNewImage(model.getImageId()); // Update the image for incorrect guesses
        } else {
            if (!guessedLetters.contains(guessedLetter)) {
                guessedLetters.add(guessedLetter);
            }
        }

        view.getTxtChar().setText("");

        if (!model.getWordNewOfLife().toString().contains("_")) {
            view.getGameTime().stopTimer();
            model.askPlayerName();
            database.insertScoreToTable(view);
            guessedLetters.clear();
            view.getRealDateTime().start();
            view.showNewButton();
            view.getLblError().setText("Wrong " + model.getImageId() + " letter(s)"); // Update the wrong letter count
            view.getLblResult().setText("L E T ' S  P L A Y");
        }

        if (model.getCountMissedWords() >= 11) {
            view.getGameTime().stopTimer();
            view.getGameTime().setRunning(false);
            JOptionPane.showMessageDialog(null, "Too many wrong letters", "Game Over", JOptionPane.PLAIN_MESSAGE);
            view.getRealDateTime().start();
            guessedLetters.clear();
            view.getLblResult().setText("L E T ' S  P L A Y");
            view.showNewButton();

        }
    }


    private boolean checkGuessedLetter(char guessedLetter) {
        String guessWord = model.getWordToGuess();
        char[] guessList = guessWord.toCharArray();
        boolean correct = false;
        for (int i = 0; i < guessList.length; i++) {
            if (guessList[i] == guessedLetter) {
                model.getWordNewOfLife().setCharAt(i, guessedLetter);
                view.getLblResult().setText(model.addSpaceBetween(model.getWordNewOfLife().toString()));
                correct = true;
            }
        }
        return correct;
    }

    private void updateErrorLabel() {
        String wrongLetters = String.join(", ", model.getMissedLetters());
        view.getLblError().setText("<html>Wrong: " + model.getCountMissedWords() + " letter(s) <font color='red'>" + wrongLetters + "</font></html>");
    }
}