
package ventas;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Ventas {


    public static void main(String[] args) {
       
        try {
            Conexion.conectar("SQL");
            Conexion.conectar("MySQL");
            Conexion.conectar("Oracle");
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
