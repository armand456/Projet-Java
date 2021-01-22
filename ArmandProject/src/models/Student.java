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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Student{
 
    public int id;
    public String name;
    public String lastName;
    public String matricule;
    public Branch branch;
    public Level level;

    public Student() {
    }

    public Student(String name, String lastName, String matricule, Branch branch, Level level) {
        this.name = name;
        this.lastName = lastName;
        this.matricule = matricule;
        this.branch = branch;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return " name=" + name + ", lastName=" + lastName + ", matricule=" + matricule + ", branch=" + branch + ", level=" + level ;
    }

    
    public void add(Student obj) throws SQLException, ClassNotFoundException {
          Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/armand","root","")) {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO student " +
                    "VALUES ( '"+ obj.getId()+"','"+ obj.getName()+"','"+ obj.getLastName()+"','"+ obj.getMatricule()+"','"+ obj.getBranch().getId()+"','"+ obj.getLevel().getId()+"')");
        } 
        
    }
    
    public List<Student> findStudentByBranch(String branch) throws SQLException{
        List<Student> students = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;

        Branch ba = new Branch();
        Branch bb = ba.findByLabel(branch);
        rs = stmt.executeQuery("SELECT * FROM student WHERE branch = '"+ bb.getId()+"'");
        while ( rs.next() ) {
            Student s = new Student();
            Level l = new Level();
            Branch b = new Branch();
            s.id = rs.getInt("id");
            s.name = rs.getString("name");
            s.lastName = rs.getString("lastName");
            s.matricule = rs.getString("matricule");
            s.branch = b.findById(rs.getInt("branch"));
            s.level = l.findById(rs.getInt("level"));
            students.add(s);
        }

        conn.close();
        return students;
    }
    
    public List<Student> findStudentByLevel(String level) throws SQLException{
        List<Student> students = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/armand"; 
        Connection conn = DriverManager.getConnection(url,"root",""); 
        Statement stmt = conn.createStatement();
        ResultSet rs;

        Level lev = new Level();
        Level le = lev.findByLabel(level);
        rs = stmt.executeQuery("SELECT * FROM student WHERE level = '"+ le.getId()+"'");
        while ( rs.next() ) {
            Student s = new Student();
            Level l = new Level();
            Branch b = new Branch();
            s.id = rs.getInt("id");
            s.name = rs.getString("name");
            s.lastName = rs.getString("lastName");
            s.matricule = rs.getString("matricule");
            s.branch = b.findById(rs.getInt("branch"));
            s.level = l.findById(rs.getInt("level"));
            students.add(s);
        }

        conn.close();
        return students;
    }
    
    
}
