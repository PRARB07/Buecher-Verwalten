package swing;

import assortmentManagement.AssortmentManagementMethods;

import javax.swing.*;

public class Buttons extends JFrame {
    public static AssortmentManagementMethods assortmentManagement = new AssortmentManagementMethods();

    /// Ausleihen
    public static JButton getBorrowBtn() {
        JButton button = new JButton("Buch Ausleihen");

        button.addActionListener(e -> {
            PopUpFrames.openCustomerWindow();
            MainFrame.mainFrame.setEnabled(false);
        });

        return button;
    }

    /// Verwaltung
    public static JButton getAddBtn() {
        JButton button = new JButton("Buch hinzufügen");

        button.addActionListener(e -> {
            PopUpFrames.openAddBookWindow();
            MainFrame.mainFrame.setEnabled(false);
        });

        return button;
    }

    public static JButton getEditBtn() {
        JButton button = new JButton("Bearbeiten");

        button.addActionListener(e -> {
            PopUpFrames.openEditBookWindow();
            MainFrame.mainFrame.setEnabled(false);
        });

        return button;
    }

    public static JButton getDeleteBtn() {
        JButton button = new JButton("Löschen");

        button.addActionListener(e -> {
            MainFrame.mainFrame.setEnabled(false);
            int choice = JOptionPane.showConfirmDialog(
                    MainFrame.mainFrame,
                    "Wollen Sie das Buch wirklich löschen?",
                    "Warnung",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.OK_OPTION) {
                assortmentManagement.deleteBook(MainFrame.selectedBook.getBookID());
            }

            MainFrame.mainFrame.setEnabled(true);
        });

        return button;
    }

    /// Sonstiges
    public static JButton getCancelBtn() {
        JButton button = new JButton("Abbrechen");

        button.addActionListener(e -> {
            MainFrame.mainFrame.setEnabled(true);
            int choice = JOptionPane.showConfirmDialog(
                    MainFrame.mainFrame,
                    "Wollen Sie wirklich abbrechen?",
                    "Cancel",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                MainFrame.mainFrame.setEnabled(true);
                MainFrame.mainFrame.dispose();
            }
        });

        return button;
    }
}
