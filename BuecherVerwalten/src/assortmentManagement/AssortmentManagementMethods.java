package assortmentManagement.BuecherVerwalten.src.assortmentManagement;

import javax.xml.transform.Result;

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
                book = new Book(rs.getInt("BuchID"),rs.getString("Titel"),rs.getString("Autor"), rs.getDate("Erscheinungsdatum").toLocalDate(),rs.getString("Beschreibung"),rs.getInt("AusleihungID"));
                sortiment.add(book);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return sortiment;
    }



    //Nach einem bestimmten Buch mit der BuchID suchen
    public Book selectSingelBook(int buchID) throws SQLException{
        sqlQuery = "SELECT * FROM buch WHERE buchID = ?";
        Book book = null;

        try(Connection conn = DriverManager.getConnection(this.url,this.user,this.password);
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery)){
            pstmt.setInt(1,buchID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                return new Book(rs.getInt("BuchID"),
                        rs.getString("Titel"),
                        rs.getString("Autor"),
                        rs.getDate("Erscheinungsdatum").toLocalDate(),
                        rs.getString("Beschreibung"),
                        rs.getInt("AusleihungID")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //Nach einem bestimmten Buch suchen nach Titel
    public ArrayList<Book> selectLikeTitel(String title) throws SQLException {
        sqlQuery = "SELECT * FROM Buch WHERE Titel LIKE ? ;";
        ArrayList<Book> erg = new ArrayList<>();
        Book book;

        try(Connection conn = DriverManager.getConnection(this.url, this.user,this.password);
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery)){
            pstmt.setString(1,"%" + title + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                book = new Book(rs.getInt("BuchID"),rs.getString("Titel"),rs.getString("Autor"),rs.getDate("Erscheinungsdatum").toLocalDate(),rs.getString("Beschreibung"),rs.getInt("AusleihungID"));
                erg.add(book);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return erg;
    }



    public void insertBook(String title, String author, LocalDate publicationDate, String description,int[] genreID, int lendingID) throws SQLException {
        int bookID = 0;
        sqlQuery = "INSERT INTO Buch(Titel, Autor, Erscheinungsdatum, Beschreibung) VALUES (?,?,?,?);";

        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery))
             {
                 //Setzen der Parameter für das Insert
                 pstmt.setString(1,title);
                 pstmt.setString(2,author);
                 pstmt.setDate(3,java.sql.Date.valueOf(publicationDate));
                 pstmt.setString(4,description);
 //                pstmt.setInt(5,lendingID);
                 pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }


        String sqlQuerySelect = "SELECT BuchID FROM Buch ORDER BY BuchID DESC LIMIT 1";
        //Die BuchID mithilfe von einem SELECT herausfinden
        try(Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
            java.sql.Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sqlQuerySelect);
            while(rs.next()) {
                bookID = rs.getInt("BuchID");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        String insertGenre2BookQuery = "INSERT INTO Genre2Buch(GenreID,BuchID) VALUES(?,?)";
        //INSERT in die Genre2Book Tabelle
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt2 = conn.prepareStatement(insertGenre2BookQuery))
        {
            //Setzen der Parameter für das Insert
            for(int i = 0; i < genreID.length; i++){
                pstmt2.setInt(1,genreID[i]);
                pstmt2.setInt(2,bookID);
                pstmt2.executeUpdate();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }




    //Ein Buch Updaten
    public void updateBook(int bookID,String title, String author, LocalDate publicationDate, String description){
        sqlQuery = "Update Buch SET Titel = ?, Autor = ?, Erscheinungsdatum = ?,  Beschreibung = ? WHERE BuchID = ?;";


        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)){

            pstmt.setString(1,title);
            pstmt.setString(2,author);
            pstmt.setDate(3,java.sql.Date.valueOf(publicationDate));
            pstmt.setString(4,description);
            pstmt.setInt(5,bookID);
            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }


    //Ein Buch Löschen
    public void deleteBook(int buchID){
        sqlQuery = "DELETE FROM genre2buch WHERE BuchID = ?";
        sqlQuery2 = "DELETE FROM Buch WHERE BuchID= ?";



        //Verbindung und Abfrage der Datenbank
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             PreparedStatement pstmt2 = conn.prepareStatement(sqlQuery2)) {


            pstmt.setInt(1,buchID);
            pstmt.executeUpdate();

            pstmt2.setInt(1,buchID);
            pstmt2.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }




}
