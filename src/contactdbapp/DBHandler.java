/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactdbapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDriver;

public class DBHandler {
    public static int COUNTER=1;
    public static DBHandler dbHandler;
    Connection con;
    private DBHandler(){
        try{
            DriverManager.registerDriver(new ClientDriver());
             con=DriverManager.getConnection("jdbc:derby://localhost:1527/Contacts","root","root");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM ROOT.CONTACT", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                COUNTER++;
            }
            rs.absolute(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
     public int addNew(Contact c) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO ROOT.CONTACT ( ID, NAME, PHONE, EMAIL ) " + "VALUES ( ?, ?, ?, ?)");
            pst.setInt(1, COUNTER);
            pst.setString(2, c.getName());
            pst.setString(3, c.getPhone());
            pst.setString(4, c.getEmail());
            int rs = pst.executeUpdate();
            COUNTER++;
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
     
     public int updateContact(Contact c) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE ROOT.CONTACT \n" + "SET NAME = ? , EMAIL = ? ,PHONE = ? \n" + "WHERE ID = ?");

            pst.setString(1, c.getName());
            pst.setString(2, c.getEmail());
            pst.setString(3, c.getPhone());
            pst.setInt(4, c.getID());
            int rs = pst.executeUpdate();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public Contact getFirst() {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM ROOT.CONTACT", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.absolute(1);
            Contact c = new Contact();
            c.setID(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setEmail(rs.getString(4));
            c.setPhone(rs.getString(3));
            return c;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Contact getLast() {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM ROOT.CONTACT", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (!rs.isLast()) {
                rs.next();
            }
            Contact c = new Contact();
            c.setID(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setEmail(rs.getString(4));
            c.setPhone(rs.getString(3));
            return c;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Contact getNext(int id) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM ROOT.CONTACT WHERE ID > ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Contact c = new Contact();
            c.setID(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setEmail(rs.getString(4));
            c.setPhone(rs.getString(3));
            return c;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Contact getPrev(int id) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM ROOT.CONTACT WHERE ID < ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            Contact c = new Contact();
            c.setID(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setEmail(rs.getString(4));
            c.setPhone(rs.getString(3));
            return c;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }    
    
    public int deleteContact(int id){
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM ROOT.CONTACT \n" + "WHERE ID = ?");
            pst.setInt(1, id);
            int rs = pst.executeUpdate();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return 0;
    }

    public static DBHandler getInstance() {
        if (dbHandler == null) {
            dbHandler = new DBHandler();
        }
        return dbHandler;
    }
    
}
