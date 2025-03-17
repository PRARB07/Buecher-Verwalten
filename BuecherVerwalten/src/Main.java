import assortmentManagement.AssortmentManagementMethods;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Test");
        AssortmentManagementMethods ammmm = new AssortmentManagementMethods();
        ammmm.insertBook("Herr der Ringe","Keine Ahnung", LocalDate.of(2025, 4,12),"Das ist ein Buch",2,4);
        System.out.println("test 2");
    }
}
