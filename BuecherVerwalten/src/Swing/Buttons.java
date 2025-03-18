package assortmentManagement.BuecherVerwalten.src.Swing;

import assortmentManagement.BuecherVerwalten.src.assortmentManagement.AssortmentManagementMethods;

import javax.swing.*;

public class Buttons extends JFrame {
    public static AssortmentManagementMethods assortmentManagement = new AssortmentManagementMethods();

    ///  Ausleihen
    public static JButton getBorrowBtn() {
        JButton button = new JButton("Buch Ausleihen");

        button.addActionListener(e -> {
            Windows.openCustomerWindow();
            Windows.mainFrame.setEnabled(false);
        });

        return button;
    }

    /// Verwaltung
    public static JButton getAddBtn() {
        JButton button = new JButton("Buch hinzufügen");

        button.addActionListener(e -> {
            Windows.openAddBookWindow();
            Windows.mainFrame.setEnabled(false);
        });

        return button;
    }

    public static JButton getEditBtn() {
        JButton button = new JButton("Bearbeiten");

        button.addActionListener(e -> {
            Windows.openEditBookWindow();
            Windows.mainFrame.setEnabled(false);
        });

        return button;
    }

    public static JButton getDeleteBtn() {
        JButton button = new JButton("Löschen");

        button.addActionListener(e -> {
            Windows.mainFrame.setEnabled(false);
            int choice = JOptionPane.showConfirmDialog(
                    Windows.mainFrame,
                    "Wollen Sie das Buch wirklich löschen?",
                    "Warnung",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.OK_OPTION) {
                assortmentManagement.deleteBook(Windows.selectedBook.getBookID());
            }

            Windows.mainFrame.setEnabled(true);
        });

        return button;
    }

    /// Sonstiges
    public static JButton getCancelBtn() {
        JButton button = new JButton("Abbrechen");

        button.addActionListener(e -> {
            Windows.mainFrame.setEnabled(true);
            int choice = JOptionPane.showConfirmDialog(
                    Windows.mainFrame,
                    "Wollen Sie wirklich abbrechen?",
                    "Cancel",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                Windows.mainFrame.setEnabled(true);
                Windows.mainFrame.dispose();
            }
        });

        return button;
    }
}
