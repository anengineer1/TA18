package connection_handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MySqlConnectionHandler {

	public static Connection initConnection(String ip, String puerto, String usuario, String contrasena) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Class.forName("com.mysql.jdbc.Driver");
			String prefix = "jdbc:mysql://";
			// String sufix = "?useTimezone=true&serverTimezone=UTC";
			String connection_parameter = prefix + ip + ":" + puerto;
			System.out.println(connection_parameter);
			Connection conexion = DriverManager.getConnection(connection_parameter, usuario, contrasena);
			System.out.println("Server connected");
			return conexion;
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("No se ha podido conectar con mi base de datos");
			System.out.println(ex);
			return null;
		}
	
    }
public static Connection closeConnection(Connection conexion) {
		try {
			conexion.close();
			System.out.println("Ha finalizado la conexión con el servidor");
			return conexion;
		} catch (SQLException e) {
			System.out.println("No se ha podido cerrar base de datos");
		//	 Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, e);
			return conexion;
		}
	}
    
public void createDB(String name,Connection conexion){
    try{
    //Creacion y ejecucion de consulta a la base de datos
    String Query = "CREATE DATABASE"+name;
    Statement st = conexion.createStatement();
    st.executeUpdate(Query);
    //Reinicio de la conexion con el nombre de la base de datos por parametros
    closeConnection(conexion);
//    MySQLConnection("root","",name);
    JOptionPane.showMessageDialog(null, "se ha creado la base de datos"+name+"de forma exitosa", Query, 0);
    }catch(SQLException ex){
  //      Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    }
    public static void executeQuery(String Query, Connection con_handler) {
		try {
			Statement st = con_handler.createStatement();
			st.executeUpdate(Query);
		} catch (Exception e) {
			System.out.println("Problema escribiendo query");
			 Logger.getLogger(SQLException.class.getName()).log(Level.SEVERE, null, e);
		}
	}

    
public void deleteRecord(String table_name,String ID,Connection conexion) {
    
    try {
        String Query = "DELETE FROM "+table_name+"WHERE ID =\""+ID+"\"";
        Statement st = conexion.createStatement();
        st.executeUpdate(Query);
    } catch (SQLException ex) {
System.out.println(ex.getMessage());
JOptionPane.showMessageDialog(null, "Error borrando el registro especifico");
    }
}


public static void getValues(String db,String table_name,Connection conexion) {
    try {
        String Querydb = "USE"+db+";";
        Statement stdb = conexion.createStatement();
        stdb.executeUpdate(Querydb);

        String Query = "SLECT * FROM"+table_name;
        Statement st = conexion.createStatement();
        java.sql.ResultSet resultSet;
        resultSet = st.executeQuery(Query);
        while (resultSet.next()) {
            System.out.println("ID:"+resultSet.getString("ID") +""+
            "Nombre:"+resultSet.getString("Nombre")+""+
            "Apellido:"+resultSet.getString("Apellido")+""+
            "Edad:"+resultSet.getString("edad")+""+
            "sexo"+resultSet.getString("sexo")
            );
            
        }
    } catch (SQLException ex) {
        
    System.out.println(ex.getMessage());
    System.out.println("Error en la adquisicion de datos");

    }
}

public class insertData {
    public static void insertData(String db,String table_name,String name,String lastname,String age,String gender,Connection conexion){

        try {
            String Querydb = "USE"+db+";";
            Statement stdb = conexion.createStatement():
            stdb.executeUpdate(Querydb);

            String Query ="Insert Into"+table_name+"(Nombre,Apellidos,Edas,Sexo) value("+"\""+name+"\","+
            "\""+lastname+"\","+"\""+age+"\","+gender+"\");";
            Statement st = conexion.createStatement();
            st.executeUpdate(Query);
            System.out.println("Datos almacenados correctos");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
        }

    }
    

}
}