/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Level {
    public int id;
    public String label;
    public String code;
    public int branch_id;

    public Level(String label, int br) {
        this.label = label;
        this.branch_id = br;
    }
    public Level() {
    }

  
    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return  code + " " + label;
    }
    
    public Level findById(int id) throws SQLException{
        Level l = new Level();
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;
        
        rs = stmt.executeQuery("SELECT * FROM level WHERE id ='"+ id +"' ");
        while ( rs.next() ) {
            l.id = rs.getInt("id");
            l.label = rs.getString("label");
            l.code = rs.getString("code");
            l.branch_id = rs.getInt("branch_id");
        }
        conn.close();
        return l;
    }
      public Level findByLabel(String label) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;

        Level b= new Level();

        rs = stmt.executeQuery("SELECT * FROM level WHERE code='"+label+"' ");
        while ( rs.next() ) {
            b.id = rs.getInt("id");
            b.label = rs.getString("label");
            b.code = rs.getString("code");
            b.branch_id = rs.getInt("branch_id");
        }

        conn.close();
        return b;
    }
}
