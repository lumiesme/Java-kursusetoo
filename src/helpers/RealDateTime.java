package helpers;

import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with real time
 */
public class RealDateTime implements ActionListener {
    private final View view;
    private final Timer timer;

    /**
     * A class constructor that is executed once
     * @param view Label lblTime to access
     */
    public RealDateTime(View view) {
        this.view = view;
        timer = new Timer(500,this); // Creates timer update every half seconds
    }

    /**
     * This method updates the date and time on the label
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        show(); // This make real datetime visible
    }

    /**
     * Start time
     */
    public void start() {
        timer.start();
    }

    /**
     * Stop time
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Show real time on the label
     */
    public void show() {
        String strTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        view.getLblTime().setText(strTime); // Update label text
    }
}
