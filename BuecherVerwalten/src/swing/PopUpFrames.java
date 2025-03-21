package swing;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class PopUpFrames extends JFrame {
    /// Ausleihung
    public static void openCustomerWindow() {
        JFrame frame = new JFrame("Buch ausleihen");

        frame.setSize(300, 350);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(getLblTextCenter("Vorname"));
        JTextField name = new JTextField();
        frame.add(name);

        frame.add(getLblTextCenter("Nachname"));
        JTextField forename = new JTextField();
        frame.add(forename);

        frame.add(getLblTextCenter("Straße"));
        JTextField street = new JTextField();
        frame.add(street);

        frame.add(getLblTextCenter("Ort"));
        JTextField location = new JTextField();
        frame.add(location);

        frame.add(getLblTextCenter("PLZ"));
        JTextField plz = new JTextField();
        frame.add(plz);

        frame.add(Buttons.getCancelBtn());
        JButton addCustomer = new JButton("Hinzufügen");
        addCustomer.addActionListener(e -> {
            try {
                MainFrame.lending.lendingBook(
                        forename.getText(),
                        name.getText(),
                        street.getText(),
                        location.getText(),
                        plz.getText(),
                        LocalDate.now(),
                        MainFrame.selectedBook.getBookID()
                );
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            //);
        });

        frame.add(addCustomer);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.mainFrame.setEnabled(true);
                super.windowClosing(e);
            }
        });

        frame.setVisible(true);
    }

    /// Verwaltung
    public static void openAddBookWindow(boolean isEdit) {
        // Frame Window
        String frameTitle = "Buch " + (isEdit ? "bearbeiten" : "hinzufügen");
        JFrame frame = new JFrame(frameTitle);
        frame.setSize(300, 350);
        frame.setLayout(new GridLayout(6, 2));

        // Create TextFields
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField date = new JTextField(LocalDate.now().toString());
        JTextField genre = new JTextField();
        JTextField desc = new JTextField();

        JButton btnAddBook = new JButton(isEdit ? "Aktualisieren" : "Hinzufügen");

        // TODO Genre
        int[] genreId = new int[] { 1, 2 };
        btnAddBook.addActionListener(e -> {
            try {
                if (isEdit) {
                    MainFrame.assortmentManagement.updateBook(
                            MainFrame.selectedBook.getBookID(),
                            title.getText(),
                            author.getText(),
                            LocalDate.parse(date.getText()),
                            desc.getText()
                    );
                }
                else {
                    MainFrame.assortmentManagement.insertBook(
                            title.getText(),
                            author.getText(),
                            LocalDate.parse(date.getText()),
                            desc.getText(),
                            genreId,
                            0
                    );
                }

                MainFrame.mainFrame.setEnabled(true);
                frame.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Falls es bearbeiten ist hole Buch schreibe die Buch Daten in die TextField
        if (isEdit && MainFrame.selectedBook != null) {
            title.setText(MainFrame.selectedBook.getTitle());
            author.setText(MainFrame.selectedBook.getAuthor());
            date.setText(MainFrame.selectedBook.getPublicationDate().toString());
            desc.setText(MainFrame.selectedBook.getDescription());
        }

        // Add Components
        frame.add(getLblTextCenter("Titel"));
        frame.add(title);
        frame.add(getLblTextCenter("Autor"));
        frame.add(author);
        frame.add(getLblTextCenter("Erscheinungsdatum"));
        frame.add(date);
        frame.add(getLblTextCenter("Genre"));
        frame.add(genre);
        frame.add(getLblTextCenter("Beschreibung"));
        frame.add(desc);

        frame.add(Buttons.getCancelBtn());
        frame.add(btnAddBook);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.mainFrame.setEnabled(true);
                super.windowClosing(e);
            }
        });

        frame.setVisible(true);
    }

    private static JLabel getLblTextCenter(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        return lbl;
    }
}
