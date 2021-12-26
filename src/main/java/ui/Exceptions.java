package ui;

import javax.swing.*;

public class Exceptions {
    private Exceptions() {
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
