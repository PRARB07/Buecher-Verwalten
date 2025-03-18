package assortmentManagement.BuecherVerwalten.src.Swing;

import assortmentManagement.BuecherVerwalten.src.assortmentManagement.AssortmentManagementMethods;
import assortmentManagement.BuecherVerwalten.src.assortmentManagement.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Windows extends JFrame {
    public static AssortmentManagementMethods assortmentManagement = new AssortmentManagementMethods();

    ///  Main Window
    public static JFrame mainFrame;
    public static Book selectedBook;

    public static void StartSwing() {
        // Main Window
        mainFrame = new JFrame("Bücher verwaltung");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);

        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Tabs
        tabbedPane.addTab("Verleihprozess", getTabVerleih());
        tabbedPane.addTab("Bücherverwaltung", getTabVerwaltung());

        // Frame loading
        mainFrame.add(tabbedPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private static JPanel getTabVerleih() {
        JPanel tabVerleih = new JPanel();

        tabVerleih.setLayout(Components.getMainLayout());

        tabVerleih.add(Panels.getSearchAndBookListPanel(false));
        tabVerleih.add(Panels.getRentalPanel());

        return tabVerleih;
    }

    private static JPanel getTabVerwaltung() {
        JPanel tabVerwaltung = new JPanel();

        tabVerwaltung.setLayout(Components.getMainLayout());

        tabVerwaltung.add(Panels.getSearchAndBookListPanel(true));
        tabVerwaltung.add(Panels.getManagementPanel());

        return tabVerwaltung;
    }

    /// Ausleihung
    public static void openCustomerWindow() {

    }

    /// Verwaltung
    public static void openAddBookWindow() {
        JFrame frame = new JFrame("Buch hinzufügen");

        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(Components.getLabel("Titel"));
        JTextField title = new JTextField();
        frame.add(title);

        frame.add(Components.getLabel("Autor"));
        JTextField author = new JTextField();
        frame.add(author);

        // Date
        frame.add(Components.getLabel("Erscheinungsdatum"));
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

        frame.add(Components.getLabel("Genre"));
        JTextField genre = new JTextField();
        frame.add(genre);

        frame.add(Components.getLabel("Beschreibung"));
        JTextField desc = new JTextField();
        frame.add(desc);

        frame.add(Buttons.getCancelBtn());
        JButton addBook = new JButton("Hinzufügen");
        // Test
        int[] genreId = new int[] { 1, 2, 3 };
        addBook.addActionListener(e -> {
            // TODO INSERT
            try {
                assortmentManagement.insertBook(
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
                Windows.mainFrame.setEnabled(true);
                super.windowClosing(e);
            }
        });

        frame.setVisible(true);
    }

    public static void openEditBookWindow() {

    }
}
