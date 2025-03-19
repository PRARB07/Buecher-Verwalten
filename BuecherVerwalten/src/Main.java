import assortmentManagement.AssortmentManagementMethods;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainFrame gui = new MainFrame();
        gui.mainFrame();

        System.out.println("Test");
        int[] genres = {1,2};
        AssortmentManagementMethods ammmm = new AssortmentManagementMethods();
        //ammmm.insertBook("Herr der Ringe","Keine Ahnung", LocalDate.of(2025, 4,12),"Das ist ein Buch",genres,4);
        //ammmm.updateBook(10,"Herr der Ringe","J.R.R. tolkin",LocalDate.of(1945,1,12),"Ich mag Herr der Ringe nicht");
        //ammmm.deleteBook(11);
        //System.out.println(ammmm.selectSingelBook(8));
        //ammmm.selectSingelBook(8);
        //System.out.println(ammmm.listSortiment());
        //System.out.println(ammmm.selectLikeTitel("Herr"));
        //System.out.println("test 2");

        LendingMethods lM = new LendingMethods();
        lM.lendingBook("Pius","Rarbach","weg","Paderborn","11111",LocalDate.of(2024,3,18),8);
    }
}
