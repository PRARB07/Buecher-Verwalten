package lendingProcessMethods;

import java.sql.*;
import java.time.LocalDate;

public class LendingMethods {

    //Datenbankverbindung
    private String user = "root";
    private String password = "Admin123";
    private String url = "jdbc:mysql://localhost:3306/buecher_verwalten";

    //SQL Abfrage und das Array wo das Ergebnis gespeichert ist
    private String sqlQuery;
    private String sqlQuery2;


    public void lendingBook( String forename, String name, String road, String ort, String plz, LocalDate lendingDate, int bookID) throws SQLException {

        String sqlQuery4 = "INSERT INTO Kunde(Vorname, Nachname, Ort, Straße, PLZ) VALUES(?,?,?,?,?)";

        int kundenIDSelect = 0;
        String sqlQuery5 = "SELECT KundenID FROM Kunde ORDER BY KundenID DESC LIMIT 1";


        sqlQuery = "INSERT INTO Ausleihung(Ausleihdatum,KundenID) VALUES(?,?)";

        int ausleihungIDSelect = 0;
        sqlQuery2 = "SELECT AusleihungID FROM Ausleihung ORDER BY AusleihungID DESC LIMIT 1";

        String sqlQuery3 = "UPDATE Buch SET AusleihungID = ? WHERE BuchID = ?";



        try(Connection conn = DriverManager.getConnection(this.url,this.user,this.password);
            PreparedStatement pstmt1 = conn.prepareStatement(sqlQuery);
            PreparedStatement pstmt2 = conn.prepareStatement(sqlQuery2);
            PreparedStatement pstmt3 = conn.prepareStatement(sqlQuery3);
            PreparedStatement pstmt4 = conn.prepareStatement(sqlQuery4);
            PreparedStatement pstmt5 = conn.prepareStatement(sqlQuery5)){

            //Insert auf Kunden Tabelle. Die Stammdaten werden eingetragen
            pstmt4.setString(1,forename);
            pstmt4.setString(2,name);
            pstmt4.setString(3,ort);
            pstmt4.setString(4,road);
            pstmt4.setString(5,plz);
            pstmt4.executeUpdate();

            //Select auf Kunden Tabelle, damit ich die KundenID bekomme von dem Eintrag der gerade gemacht wurde
            ResultSet rs = pstmt5.executeQuery();
            while(rs.next()){
                kundenIDSelect = rs.getInt("KundenID");
            }

            //Insert int die Ausleih Tabelle
            pstmt1.setDate(1,java.sql.Date.valueOf(lendingDate));
            pstmt1.setInt(2,kundenIDSelect);
            pstmt1.executeUpdate();

            //Select auf Ausleih Tabelle, damit ich die AusleihID bekomme
            rs = pstmt2.executeQuery();
            while(rs.next()){
                ausleihungIDSelect = rs.getInt("AusleihungID");
            }

            // Auf die Buch Tabelle, damit sie die richtige AusleihID hat
            pstmt3.setInt(1,ausleihungIDSelect);
            pstmt3.setInt(2,bookID);
            pstmt3.executeUpdate();






        }
    }

}
