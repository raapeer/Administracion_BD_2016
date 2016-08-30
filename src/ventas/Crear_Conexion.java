/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;
import java.sql.*;

/**
 *
 * @author Tchaikovsky
 */
public class Crear_Conexion {
    static String strUsuario = "sa";
    static String strPassword = "sasa";
    static String strBaseDatos ="ventas";
    static String strHost = "1433";
    
     public Crear_Conexion(String sa,String sasa , String hotel)
    {
        strUsuario = sa;
        strPassword = sasa;
        strBaseDatos = hotel;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     
        } catch ( ClassNotFoundException e )
        {
            System.out.println("ERROR: Error al cargar la clase del Driver");       
        }
    }
     
    //Constructor, le llegan los datos con los que se conectara al DBMS
    // a dif. del otro constructor le llega tbn el host (servidor)
    public Crear_Conexion(String sa,String sasa, String hotel,String srvr)
    {
        strUsuario = sa;
        strPassword = sasa;
        strBaseDatos =hotel;
        strHost = srvr;
        try
        {
            //Cargar el driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     
        } catch ( ClassNotFoundException e )
        {
            System.out.println("ERROR: Error al cargar la clase del Driver\n");
            e.printStackTrace();
        }
    }
     
    public static Connection getConnection() throws SQLException 
    {
        //a continuacion vamos a formar la cadena de conexion, pero...
        //OJO aca debes poner el nombre de la instancia de SQL Server que vas a usar
        String url = "jdbc:sqlserver://localhost:1433;databaseName=LasHuertas;user=sa;password=sasa;";
               
        return DriverManager.getConnection(url,strUsuario,strPassword);
    }
 
    //Cierra objeto Resultset
    public static void cerrar(ResultSet rs)
    {   
        try
        {
            rs.close();
        } 
        catch(Exception ex)
        {}
    }
     
    //Cierra objeto Statement
    public static void cerrar(Statement st)
    {
        try
        {
            st.close();
        } 
        catch(Exception ex)
        {}
    }
     
    //Cierra objeto Connection
    public static void cerrar(Connection con)
    {   
        try
        {
            con.close();
        } 
        catch(Exception ex)
        {}
    }
}   //Fin de la clase
    

