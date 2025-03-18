package assortmentManagement.BuecherVerwalten.src.Swing;


import assortmentManagement.BuecherVerwalten.src.assortmentManagement.AssortmentManagementMethods;
import assortmentManagement.BuecherVerwalten.src.assortmentManagement.Book;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Components extends JFrame {
    public static AssortmentManagementMethods assortmentManagement = new AssortmentManagementMethods();

    public static GridLayout getMainLayout() {
        GridLayout gridLayout = new GridLayout(1, 2);
        return gridLayout;
    }

    public static FlowLayout getFlowLayout() {
        FlowLayout flowLayout = new FlowLayout();
        return flowLayout;
    }

    public static JButton getButton(String text) {
        JButton button = new JButton(text);

        return button;
    }

    public static JLabel getLabel(String text) {
        JLabel label = new JLabel(text);

        return label;
    }

    public static JTextField getTextField() {
        JTextField txtSearch = new JTextField();
        return txtSearch;
    }

    public static JList getList() throws SQLException {
        ArrayList<Book> sortiment = assortmentManagement.listSortiment();

        JList list = new JList(sortiment.toArray());

        return list;
    }
}
