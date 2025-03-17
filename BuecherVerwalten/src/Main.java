import assortmentManagement.AssortmentManagementMethods;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Test");
        AssortmentManagementMethods ammmm = new AssortmentManagementMethods();
        ammmm.insertBook("Harry Potter","Ich", LocalDate.of(2025, 4,12),"Das ist ein Buch",1,4);
        System.out.println("test 2");
    }
}
