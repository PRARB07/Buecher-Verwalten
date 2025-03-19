package swing;

import assortmentManagement.Book;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Panels extends JPanel {

    /// Haupt Panel für das Navigieren von Bücher
    public static JPanel getSearchAndBookListPanel(boolean addBook) {
        JPanel bookPanel = new JPanel();
        JPanel searchPanel = new JPanel();

        // Search Panel
        JTextField search = new JTextField(20);
        JButton btnSearch = new JButton("Suchen");
        btnSearch.addActionListener(e -> {
            try {
                for (int i = 0; i < MainFrame.Books.size(); i++) {
                    System.out.println(MainFrame.Books.get(i));
                }
                System.out.println("---");
                MainFrame.Books = MainFrame.assortmentManagement.selectLikeTitel(search.getText());

                for (int i = 0; i < MainFrame.Books.size(); i++) {
                    System.out.println(MainFrame.Books.get(i));
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            MainFrame.mainFrame.revalidate();
            MainFrame.mainFrame.repaint();
        });
        searchPanel.add(search);
        searchPanel.add(btnSearch);

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

    /// Die Liste an Bücher
    public static JPanel getBookListPanel(boolean addBook) throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        if (addBook) {
            panel.add(Buttons.getAddBtn());
        }

        ArrayList<Book> sortiment = MainFrame.assortmentManagement.listSortiment();
        DefaultListModel<Book> dlm = new DefaultListModel<Book>();
        for (Book book : sortiment) {
            dlm.addElement(book);
        }

        JList list = new JList(dlm);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                MainFrame.selectedBook = dlm.get(list.getSelectedIndex());
                // TODO GUI Updaten wenn man Buch auswhält
                getBookViewPanel().revalidate();
                getBookViewPanel().repaint();
            }
        });

        panel.add(list);

        return panel;
    }

    /// Panel für das Ausleihen
    public static JPanel getRentalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        panel.add(Panels.getBookViewPanel());
        panel.add(Buttons.getBorrowBtn());

        return panel;
    }

    /// Verwaltungs Panel für die Bücher
    public static JPanel getManagementPanel() {
        JPanel panel = new JPanel();

        panel.add(Panels.getBookViewPanel());
        panel.add(Buttons.getDeleteBtn());
        panel.add(Buttons.getEditBtn());

        return panel;
    }

    /// Ausgabe zu den Informationen vom Buch
    public static JPanel getBookViewPanel() {
        JPanel panel = new JPanel();
        Book book = null;

        try {
            if (MainFrame.selectedBook != null)
            {
                book = MainFrame.assortmentManagement.selectSingelBook(MainFrame.selectedBook.getBookID());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        panel.setLayout(new BorderLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        panel.setBorder(border);

        if (book == null) {
            return panel;
        }

        // Grid Top
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new GridLayout(2, 1));
        bookPanel.add(new JLabel(book.getTitle()));
        bookPanel.add(new JLabel(book.getPublicationDate().toString()));

        // Add to BorderLayout
        panel.add(bookPanel, BorderLayout.NORTH);
        panel.add(new JLabel(book.getDescription() + "Test"), BorderLayout.CENTER);
        panel.add(new JLabel(book.getAuthor().toString()), BorderLayout.SOUTH);

        return panel;
    }
}
