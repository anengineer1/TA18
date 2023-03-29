package connection_handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MySqlConnectionHandler {

	public static Connection initConnection(String ip, String puerto, String usuario, String contrasena) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String prefix = "jdbc:mysql://";
			String sufix = "?useTimezone=true&serverTimezone=UTC";
			String connection_parameter = prefix + ip + ":" + puerto + sufix;
			System.out.println(connection_parameter);
			Connection conexion = DriverManager.getConnection(connection_parameter, usuario, contrasena);
			System.out.println("Server connected");
			return conexion;
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("No se ha podido conectar con mi base de datos");
			System.out.println(ex.getMessage());
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
			System.out.println(e.getMessage());
			return conexion;
		}
	}

	public static void createDB(String name, Connection conexion) {
		try {
			// Creacion y ejecucion de consulta a la base de datos
			String Query = "CREATE DATABASE IF NOT EXISTS " + name + ";";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("se ha creado la base de datos " + name + " de forma exitosa");
		} catch (SQLException ex) {
			System.out.println("No se ha podido crear la base de datos");
			System.out.println(ex.getMessage());
		}

	}

	public static void createTable(String db, String name, String query, Connection conexion) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			String QueryComp = "CREATE TABLE IF NOT EXISTS " + name + "(" + query + ");";
			Statement st = conexion.createStatement();
			st.executeUpdate(QueryComp);
			System.out.println("Tabla creada con exito!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la tabla");
		}
	}

	public void deleteRecord(String table_name, String ID, Connection conexion) {

		try {
			String Query = "DELETE FROM " + table_name + "WHERE ID =\"" + ID + "\"";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especifico");
		}
	}

	public static void printValues(String db, String table_name, Connection conexion) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String query = "SELECT * FROM " + table_name;
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}
			rs.close();
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");

		}
	}

	public static void insertData(String db, String table_name, String query, Connection conexion) {

		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			String Query = "Insert Into " + table_name + " VALUES " + "(" + query + ");";
			System.out.println(Query);
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Datos almacenados correctos");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
	}
}
