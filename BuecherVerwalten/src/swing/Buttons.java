package swing;

import assortmentManagement.AssortmentManagementMethods;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Buttons extends JFrame {
    public static AssortmentManagementMethods assortmentManagement = new AssortmentManagementMethods();

    /// Ausleihen und Annehmen
    public static JButton getBorrowBtn() {
        JButton button = new JButton("Buch " + (MainFrame.selectedBook != null &&
                MainFrame.selectedBook.getLendingID() > 0 ? "annehmen" : "ausleihen"));
        button.setPreferredSize(new Dimension(150, 50));
        button.addActionListener(e -> {
            // Ausleihen
            if (MainFrame.selectedBook.getLendingID() <= 0) {
                PopUpFrames.openCustomerWindow();
                MainFrame.mainFrame.setEnabled(false);
            }
            // Annehmen
            else {
                try {
                    MainFrame.lending.returnBook(MainFrame.selectedBook.getBookID());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(
                        MainFrame.mainFrame,
                        "Buch wurde angenommen",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        return button;
    }

    /// Verwaltung
    public static JButton getAddBtn() {
        JButton button = new JButton("Buch hinzufügen");

        button.addActionListener(e -> {
            PopUpFrames.openAddBookWindow(false);
            MainFrame.mainFrame.setEnabled(false);
        });

        return button;
    }

    public static JButton getEditBtn() {
        JButton button = new JButton("Bearbeiten");

        button.addActionListener(e -> {
            PopUpFrames.openAddBookWindow(true);
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
                    "Wollen Sie das Buch " + MainFrame.selectedBook.getTitle() + " wirklich löschen?",
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
