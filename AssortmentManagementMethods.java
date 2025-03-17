package assortmentManagement;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;


public class AssortmentManagementMethods {

    //Datenbankverbindung
    String user = "root";
    String password = "Admin123";
    String url = "jdbc:mysql://localhost:3306/buecher_verwalten";

    //SQL Abfrage und das Array wo das Ergebnis gespeichert ist
    String sqlQuery;





    //Das gesamte Sortiment aufrufen
    public ArrayList<Book> listSortiment() throws SQLException {
        sqlQuery = "SELECT * FROM Buch";
        ArrayList<Book> sortiment = new ArrayList<Book>();
        Book book;

        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            while(rs.next()){
                book = new Book(rs.getInt("BuchID"),rs.getString("Titel"),rs.getString("Autor"), rs.getDate("Erscheinungsdatum").toLocalDate(),rs.getString("Beschreibung"));
                sortiment.add(book);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return sortiment;

    }



    public void insertBook(int buchID, String title, String author, LocalDate publicationDate, String description){
        sqlQuery = "INSERT INTO Buch(BuchID, Titel, Autor, Erscheinungsdatum, Beschreibung) VALUES ("+buchID+", "+title+", "+author+", "+publicationDate+", "+description+");";


        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             java.sql.Statement stmt = conn.createStatement();
             pstmt.executeUpdate()) {


        }catch(SQLException e){
            e.printStackTrace();
        }

    }


    //Ein Buch Updaten
    public void updateBook(String title, String author, LocalDate publicationDate, String description){
        sqlQuery = "Update FROM Buch SET Titel = "+title+", Autor = " + author + ", Erscheinungsdatum = " + publicationDate + ",  Beschreibung = "+ description+";";


        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {


        }catch(SQLException e){
            e.printStackTrace();
        }

    }


    //Ein Buch Löschen
    public void deleteBook(int buchID){
        sqlQuery = "DELETE FROM Buch WHERE BuchID= "+buchID;


        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

}
