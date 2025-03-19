package swing;

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

        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(5, 2));

        frame.add(new JLabel("Vorname"));
        JTextField name = new JTextField();
        frame.add(name);

        frame.add(new JLabel("Nachname"));
        JTextField forename = new JTextField();
        frame.add(forename);

        frame.add(new JLabel("Straße"));
        JTextField street = new JTextField();
        frame.add(street);

        frame.add(new JLabel("Ort"));
        JTextField location = new JTextField();
        frame.add(location);

        frame.add(new JLabel("PLZ"));
        JTextField plz = new JTextField();
        frame.add(plz);

        frame.add(Buttons.getCancelBtn());
        JButton addCustomer = new JButton("Hinzufügen");
        addCustomer.addActionListener(e -> {
            // TODO INSERT
            //MainFrame.assortmentManagement.insertBook(

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
    public static void openAddBookWindow() {
        JFrame frame = new JFrame("Buch hinzufügen");

        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(new JLabel("Titel"));
        JTextField title = new JTextField();
        frame.add(title);

        frame.add(new JLabel("Autor"));
        JTextField author = new JTextField();
        frame.add(author);

        // Date
        frame.add(new JLabel("Erscheinungsdatum"));
        Date currentDate = new Date(LocalDate.now().getYear(), 1, 1);
        JSpinner spinner = new JSpinner(new SpinnerDateModel(
                currentDate,
                null,
                null,
                java.util.Calendar.DAY_OF_MONTH)
        );
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy.MM.dd");
        spinner.setEditor(editor);
        spinner.addChangeListener(e -> {
            Date selectedDate = (Date) spinner.getValue();
        });

        JTextField releaseDate = new JTextField();
        //frame.add(releaseDate);
        frame.add(spinner);

        frame.add(new JLabel("Genre"));
        JTextField genre = new JTextField();
        frame.add(genre);

        frame.add(new JLabel("Beschreibung"));
        JTextField desc = new JTextField();
        frame.add(desc);

        frame.add(Buttons.getCancelBtn());
        JButton addBook = new JButton("Hinzufügen");
        // Test
        int[] genreId = new int[] { 1, 2, 3 };
        addBook.addActionListener(e -> {
            // TODO INSERT
            try {
                MainFrame.assortmentManagement.insertBook(
                        title.getText(),
                        author.getText(),
                        LocalDate.now(),
                        desc.getText(),
                        genreId,
                        0
                );
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        frame.add(addBook);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.mainFrame.setEnabled(true);
                super.windowClosing(e);
            }
        });

        frame.setVisible(true);
    }

    public static void openEditBookWindow() {

    }
}
