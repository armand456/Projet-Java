/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Branch {
    public int id;
    public String label;

    public Branch() {
    }

    public Branch(String label) {
        this.label = label;
    }

    public Branch(int id, String label) {
        this.id = id;
        this.label = label;
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
        return  label;
    }

    public int add(Branch obj) throws SQLException, ClassNotFoundException {
        int ok = 0;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/armand","root","")) {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO branch " +
                    "VALUES ( '"+ obj.getId()+"','"+ obj.getLabel() +"')";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                ok = rs.getInt(1);
            }
            st.executeUpdate("INSERT INTO level VALUES ( '"+ 0 +"','Licence 1','"+ obj.getLabel()+ "L1" + "','"+ ok +"')");
            st.executeUpdate("INSERT INTO level VALUES ( '"+ 0 +"','Licence 2','"+ obj.getLabel()+ "L2" + "','"+ ok +"')");
            st.executeUpdate("INSERT INTO level VALUES ( '"+ 0 +"','Licence 3','"+ obj.getLabel()+ "L3" + "','"+ ok +"')");
            st.executeUpdate("INSERT INTO level VALUES ( '"+ 0 +"','Master 1','"+ obj.getLabel()+ "M1" + "','"+ ok +"')");
            st.executeUpdate("INSERT INTO level VALUES ( '"+ 0 +"','Master 2','"+ obj.getLabel()+ "M2" + "','"+ ok +"')");
        } 
        return ok;
    }

    
    public int update(Branch obj, Branch objChange) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        PreparedStatement ps = conn.prepareStatement("UPDATE branch SET label = ? WHERE id = ? ");
        Statement st = conn.createStatement(); 
        st.executeUpdate("UPDATE level SET code = replace(code, '"+ obj.getLabel() +"', '"+ objChange.getLabel() +"' ) WHERE branch_id = '"+ obj.getId()+"' "); 

        ps.setString(1,objChange.label);
        ps.setInt(2,objChange.id);
       
        int ok = ps.executeUpdate();
        ps.close();
        return ok;
    }

    
    public void delete(Branch obj) throws SQLException {
         String url = "jdbc:mysql://localhost:3306/armand"; 
            Connection conn = DriverManager.getConnection(url,"root",""); 
            Statement st = conn.createStatement(); 
            st.executeUpdate("DELETE FROM branch " + 
                "WHERE label ='"+ obj.getLabel() +"' "); 
            st.executeUpdate("DELETE FROM level " + 
                "WHERE branch_id ='"+ obj.getId()+"' "); 
          
            conn.close(); 
    }

    
    public List<Branch> findAll() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;

        List <Branch> allBrach= new ArrayList<>();

        rs = stmt.executeQuery("SELECT * FROM branch ");
        while ( rs.next() ) {
            Branch b = new Branch();
            b.id = rs.getInt("id");
            b.label = rs.getString("label");
            allBrach.add(b);
        }

        conn.close();
        return allBrach;
    }

    
    public Branch findById(int id) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;
        
        Branch b = new Branch();
        rs = stmt.executeQuery("SELECT * FROM branch WHERE id ='"+ id +"' ");
        while ( rs.next() ) {
            b.id = rs.getInt("id");
            b.label = rs.getString("label");
            
        }

        conn.close();
        return b;
    }
    
    public Branch findByLabel(String label) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;

        Branch b= new Branch();

        rs = stmt.executeQuery("SELECT * FROM branch WHERE label='"+label+"' ");
        while ( rs.next() ) {
            b.id = rs.getInt("id");
            b.label = rs.getString("label");
        }

        conn.close();
        return b;
    }
    public List<Level> findLevelByBranch(String branch) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;

        List<Level> l = new ArrayList<>();
        rs = stmt.executeQuery("SELECT * FROM level WHERE code LIKE '%"+ branch+"%'");
        while ( rs.next() ) {
            Level lev = new Level();
            lev.id = rs.getInt("id");
            lev.label = rs.getString("label");
            lev.code = rs.getString("code");
            lev.branch_id = rs.getInt("branch_id");
            l.add(lev);
        }

        conn.close();
        return l;
    }
    
    
}
