import assortmentManagement.AssortmentManagementMethods;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        AssortmentManagementMethods ammmm = new AssortmentManagementMethods();
        ammmm.insertBook(1,"Harry Potter","Ich", LocalDate.of(2025, 4,12),"Das ist ein Buch");
        System.out.println("test 2");
    }
}
