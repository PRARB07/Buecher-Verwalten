package assortmentManagement.BuecherVerwalten.src.Swing;

import assortmentManagement.BuecherVerwalten.src.assortmentManagement.Book;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class Panels extends JFrame {

    public static JPanel getSearchAndBookListPanel(boolean addBook) {
        JPanel bookPanel = new JPanel();
        JPanel searchPanel = new JPanel();

        // Search Panel
        searchPanel.add(Components.getLabel("Search"));
        searchPanel.add(Components.getTextField());

        // Book Panel
        bookPanel.setLayout(new GridLayout(2, 1));
        bookPanel.add(searchPanel);
        try {
            bookPanel.add(Panels.getBookListPanel(addBook));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookPanel;
    }

    /// Main Panels
    public static JPanel getRentalPanel() {
        JPanel panel = new JPanel();

        panel.add(Panels.getBookViewPanel());
        panel.add(Buttons.getBorrowBtn());

        return panel;
    }

    public static JPanel getManagementPanel() {
        JPanel panel = new JPanel();

        panel.add(Panels.getBookViewPanel());
        panel.add(Buttons.getDeleteBtn());
        panel.add(Buttons.getEditBtn());

        return panel;
    }


    public static JPanel getBookListPanel(boolean addBook) throws SQLException {
        JPanel panel = new JPanel();

        // Test
        Book[] testBookList = {

        };

        if (addBook) {
            panel.add(Buttons.getAddBtn());
        }

        panel.add(Components.getList());

        return panel;
    }

    public static JPanel getBookViewPanel() {
        JPanel panel = new JPanel();

        // Test
        var book = new Book(0, "Test123", "Author", LocalDate.now(), "Beschreibung", 1);

        JLabel lblTitle = new JLabel(book.getTitle());
        JLabel lblAuthor = new JLabel(book.getAuthor());
        JLabel lblDescription = new JLabel(book.getDescription());
        JLabel lblDate = new JLabel(book.getPublicationDate().toString());

        panel.add(lblTitle);
        panel.add(lblAuthor);
        panel.add(lblDescription);
        panel.add(lblDate);

        return panel;
    }
}
