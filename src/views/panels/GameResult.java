package views.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel for guess word. Contains one JLabel
 */
public class GameResult extends JPanel {
    private final JLabel lblResult = new JLabel("L E T ' S  P L A Y"); // Default word

    /**
     * Constructor for the GameResult panel. This is always done when the object is created. It will be done once!
     */
    public GameResult() {
        setBackground(new Color(230,250,220)); // Panel background color
        // setLayout(new FlowLayout(FlowLayout.CENTER));
        setLayout(new GridBagLayout()); // this panel layout
        setBorder(new EmptyBorder(5,5,5,5)); // Empty space around the panel

        // Font style for JLabel
        Font fontBold = new Font("Verdana", Font.BOLD, 24); // JLabel font style
        lblResult.setFont(fontBold); // Set label font and style
        lblResult.setHorizontalAlignment(JLabel.CENTER); // Align JLabel center on panel in GridBagLayout

        add(lblResult); // Add label into panel (bottom panel aka GameResult Panel)
    }

    /**
     * Only the label needs to be picked up from this panel
     * @return JLabel
     */
    public JLabel getLblResult() {
        return lblResult;
    }
}
