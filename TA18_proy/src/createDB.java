import connection_handler.MySqlConnectionHandler;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


class createDB {
public void createDB(String name,Connection conexion){
try{
//Creacion y ejecucion de consulta a la base de datos
String Query = "CREATE DATABASE"+name;
Statement st = conexion.createStatement();
st.executeUpdate(Query);
//Reinicio de la conexion con el nombre de la base de datos por parametros
conexion.closeConnection(conexion);
MySQLConnection("root","",name);
JOptionPane.showMessageDialog(null, "se ha creado la base de datos"+name+"de forma exitosa", Query, 0);
}catch(SQLException ex){
    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
}


}

}