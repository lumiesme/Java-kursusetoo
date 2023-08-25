package helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Limits the number of characters in a JTextField field. In this variant, it is one character.
 * <a href="https://www.tutorialspoint.com/how-can-we-limit-the-number-of-characters-inside-a-jtextfield-in-java">TextFieldLimit saadud lahendus</a>
 */
public class TextFieldLimit extends PlainDocument {
    private final int limit;

    public TextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    /**
     * @param offs  the starting offset &gt;= 0
     * @param str   the string to insert; does nothing with null/empty strings
     * @param a     the attributes for the inserted content
     * @throws BadLocationException badLocationException
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if(str == null) return;
        if((getLength() + str.length() <= limit)) {
            super.insertString(offs, str, a);
        }

    }
}
