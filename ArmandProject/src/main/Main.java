/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Branch;
import models.Student;

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int reponse;
        Branch branche = new Branch();
        
        do{
            System.out.println("1 - Ajouter une filière ");
            System.out.println("2 - Modifier une filière ");
            System.out.println("3 - Supprimer une fiière ");
            System.out.println("4 - Ajouter un étudiant ");
            System.out.println("5 - Afficher les etudiants d'une filière ");
            System.out.println("6 - Afficher les etudiant d'un niveau ");
            System.out.println("7 - Quitter ");
            reponse = sc.nextInt();
            switch (reponse) {
              case 1:
                 
                    Branch b = new Branch();
                    System.out.println("Saisissez la filière");
                    String label = sc.next();
                    Branch br = new Branch(label);
                    b.add(br);
                break;
                case 2:
                 
                    Branch bc = new Branch();
                    System.out.println("Saisissez la filière a modifier");
                    String lab = sc.next();
                    Branch bh = bc.findByLabel(lab);
                    Branch bb = bc.findByLabel(lab);
                    System.out.println("Saisissez la nouvelle filière ");
                    String newLab = sc.next();
                    bh.setLabel(newLab);
                    Branch bf = new Branch();
                    bf.update(bb, bh);
                break;
                case 3:
                 
                    Branch bcS = new Branch();
                    System.out.println("Saisissez la filière a supprimer");
                    String labS = sc.next();
                    Branch bhS = bcS.findByLabel(labS);
                    Branch bfS = new Branch();
                    bfS.delete(bhS);
                break;
              case 4 :
                   Student st = new Student();
                   System.out.println("Saisissez le prenom");
                   String name = sc.next();
                   System.out.println("Saisissez le nom");
                   String lname = sc.next();
                   System.out.println("Saisissez le matricule");
                   String mat = sc.next();
                   
                   System.out.println("Saisissez la filière");
                   String branch = sc.next();
                   Branch bra = new Branch();
                   Branch braaa = new Branch();
                   branche = bra.findByLabel(branch);
                   
                   System.out.println("Choisissez le niveau");
                   List <models.Level> level = braaa.findLevelByBranch(branche.getLabel());
                   for(int i=0; i < level.size(); i++){
                       System.out.println(i+1 +" "+level.get(i));
                   }
                    int lev = sc.nextInt();
                    models.Level levelChoose = level.get(lev - 1);
                
                   Student s = new Student(name, lname, mat, bra.findByLabel(branch), levelChoose);
                   st.add(s);
                  break;
              case 5:
                  System.out.println("Saisissez la filiere a rechercher");
                   String branchSearched = sc.next();
                   Student stu = new Student();
                   List <Student> stud;
                   stud = stu.findStudentByBranch(branchSearched);
                   System.out.println("Liste des eleves de la filiere :"+ branchSearched);
                   for(int i=0; i < stud.size(); i++){
                       System.out.println(i+1 +" "+stud.get(i));
                   }
                  break;
               case 6:
                  System.out.println("Saisissez la filiere a rechercher");
                   String levelSearched = sc.next();
                   Student studen = new Student();
                   List <Student> stude;
                   stude = studen.findStudentByLevel(levelSearched);
                   if(stude.size() != 0){
                       System.out.println("Liste des eleves de la filiere :"+ levelSearched);
                        for(int i=0; i < stude.size(); i++){
                            System.out.println(i+1 +" "+stude.get(i));
                        }
                   }
                   
                  break;
              default:
                System.out.println("Choisissez un bon numero");
            }
         
        }while (reponse != 7);
        //System.out.println("Ajouter une filière \n");
        //String label = sc.next();
        Branch br = new Branch();
        Branch b = new Branch();
        b.delete(b.findAll().get(1));
        System.out.println(br.findAll());

    }
    
}
