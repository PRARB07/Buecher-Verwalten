package assortmentManagement;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;


public class AssortmentManagementMethods {

    //Datenbankverbindung
    private String user = "root";
    private String password = "Admin123";
    private String url = "jdbc:mysql://localhost:3306/buecher_verwalten";

    //SQL Abfrage und das Array wo das Ergebnis gespeichert ist
    private String sqlQuery;
    private String sqlQuery2;





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
                book = new Book(rs.getInt("BuchID"),rs.getString("Titel"),rs.getString("Autor"), rs.getDate("Erscheinungsdatum").toLocalDate(),rs.getString("Beschreibung"),rs.getInt("GenreID"),rs.getInt("AusleihID"));
                sortiment.add(book);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return sortiment;

    }



    public void insertBook(String title, String author, LocalDate publicationDate, String description, int genreID, int lendingID) throws SQLException {
        String bookID;
        sqlQuery = "INSERT INTO Buch(BuchID, Titel, Autor, Erscheinungsdatum, Beschreibung) VALUES (?,?,?,?,?,?);";
        String sqlQuerySelect = "SELECT BuchID FROM Buch"


        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             PreparedStatement pstmt2 = conn.prepareStatement(sqlQuery2))
             {

                 pstmt.setString(1,title);
                 pstmt.setString(2,author);
                 pstmt.setDate(3,java.sql.Date.valueOf(publicationDate));
                 pstmt.setString(4,description);
                 pstmt.setInt(5,genreID);
                 pstmt.setInt(6,lendingID);

                 pstmt.executeUpdate();

                 pstmt2.setInt(1,bookID);
                 pstmt2.setInt(2,lendingID);


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
