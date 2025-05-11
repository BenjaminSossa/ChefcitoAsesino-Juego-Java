/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chefcitoasesinovercion7.pkg2;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import javax.swing.JOptionPane;
import java.sql.*;

public class Conectar {
    
   
    
    Connection cn;
    Statement st;
    
    public Connection conectar(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/jugadores","root","");
        System.out.print("Conexion establecida...");
    }catch(Exception e){
    System.out.print(e.getMessage());
    }return cn;
  }
}
