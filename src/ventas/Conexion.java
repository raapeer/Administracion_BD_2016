/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

public class Conexion {
    public String Gestor;

    public Conexion(String Gestor) {
        this.Gestor = Gestor;
    }
    
   
    public static Connection conectar(String Gestor) throws SQLException{
        
       if(Gestor.equals("SQL")) {
        Connection cn=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           String url = "jdbc:sqlserver://localhost;databaseName=Ventas;user=sa;password=sasa;";
           cn=DriverManager.getConnection(url);
           return (cn); 
        } catch (Exception e) {
            System.out.print("Error al conectar con la base de dtos SQL:" + e);
            return null;
        }
    }if(Gestor.equals("Oracle")) {
        Connection cn=null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
           String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String xe = null;
           cn=DriverManager.getConnection(url,"system","sasa");
           return (cn); 
        } catch (Exception e) {
            System.out.print("Error al conectar con la base de dtos oracle :" + e);
            return null;
        }
    }
       else if(Gestor.equals("MySQL")){
            Connection cn=null;
           try {
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas","root","sasa");
            return cn;
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      return null;
       }

       public static JComboBox llenarcombo(JComboBox selector, String query, String Serv) throws SQLException{
        selector.removeAllItems();
        selector.addItem("Selecciona--");
        Connection cn = Conexion.conectar(Serv);
        if (cn != null){
            try {
                System.out.println("aber");
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    System.out.println("aber1");
                    selector.addItem(rs.getString(1));
                }
                if(rs.next()){
                    System.out.println("aber2");
                    selector.addItem(rs.getString(2));
                }
                return selector;
            } catch (Exception e) {
                System.out.println("Error el llenar el mcombo " + e);
               
            }
        }else{
            System.out.println("Error al conectar la base");
        }
        return selector;
        
    }

    
    }


