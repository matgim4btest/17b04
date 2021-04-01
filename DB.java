package rs.edu.matgim.zadatak;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    String connectionString = "jdbc:C:\\Users\\Vocnjak4\\Documents\\NetBeansProjects\\17b04\\src\\main.Banka.db";

    public void printFilijala() {
        try ( Connection conn = DriverManager.getConnection(connectionString);  Statement s = conn.createStatement()) {

            ResultSet rs = s.executeQuery("SELECT * FROM Filijala");
            while (rs.next()) {
                int IdFil = rs.getInt("IdFil");
                String Naziv = rs.getString("Naziv");
                String Adresa = rs.getString("Adresa");

                System.out.println(String.format("%d\t%s\t%s", IdFil, Naziv, Adresa));
            }

        } catch (SQLException ex) {
            System.out.println("Greska prilikom povezivanja na bazu");
            System.out.println(ex);
        }
    }
    public void  printPositiveRacun() {try ( Connection conn = DriverManager.getConnection(connectionString);  Statement s = conn.createStatement()) {

            ResultSet rs = s.executeQuery("SELECT * FROM Racun WHERE Stanje>0");
            while (rs.next()) {
                int idrac = rs.getInt("IdRac");
                int Stanje = rs.getInt("Stanje");
             
            
                System.out.println(idrac+"\t"+Stanje+"\t");
            }

        } catch (SQLException ex) {
            System.out.println("Greska prilikom povezivanja na bazu");
            System.out.println(ex);
        }}
   public void yad(int idFil,int idKom)
{
    try{Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Vocnjak4\\Documents\\NetBeansProjects\\17b04\\src\\main\\Banka.db");
    PreparedStatement stm =con.prepareStatement("SELECT * FROM Racun R Komitent K WHERE Stanje<-R.DozvMinus AND R.IdFIl=? AND R.idKom=? ");
    PreparedStatement stm1 =con.prepareStatement("INSERT INTO Uplata(Iznos,IdRac,IdFil,Osnov) Values(?,?,?,"+"'Uplata na zahtev gradjanina'"+"+)");
    con.setAutoCommit(false);
    stm.setInt(1,idFil);
    stm.setInt(2, idKom);
   int sum=0;
   PreparedStatement stm2 =con.prepareStatement("UPDATE Racun SET Stanje=DozvMinus AND Status='A' WHERE R.Idrac=?");
   
    ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int idrac = rs.getInt("IdRac");
                int Stanje = rs.getInt("Stanje");
                int dozm=rs.getInt("DozvMinus");
                sum=sum-dozm-Stanje;
                int dodati=-dozm-Stanje;
              
            }
    
    
    con.commit();
    con.setAutoCommit(true);
    }
    catch(SQLException ex){
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE,null,ex);
        }
    
}

}
