package assortmentManagement.BuecherVerwalten.src.swing;

import assortmentManagement.BuecherVerwalten.src.assortmentManagement.AssortmentManagementMethods;
import assortmentManagement.BuecherVerwalten.src.assortmentManagement.Book;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static AssortmentManagementMethods assortmentManagement = new AssortmentManagementMethods();
    public static lendingProcessMethods.LendingMethods lending = new lendingProcessMethods.LendingMethods();
    public static JFrame mainFrame;
    public static Book selectedBook;

    public void mainFrame() {
        // Main Window
        mainFrame = new JFrame("Bücher verwaltung");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 700);
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

    private JPanel getTabVerleih() {
        JPanel tabVerleih = new JPanel();

        tabVerleih.setLayout(new GridLayout(1, 2));

        tabVerleih.add(Panels.getSearchAndBookListPanel(false));
        tabVerleih.add(Panels.getRentalPanel());

        return tabVerleih;
    }

    private JPanel getTabVerwaltung() {
        JPanel tabVerwaltung = new JPanel();

        tabVerwaltung.setLayout(new GridLayout(1, 2));

        tabVerwaltung.add(Panels.getSearchAndBookListPanel(true));
        tabVerwaltung.add(Panels.getManagementPanel());

        return tabVerwaltung;
    }
}
