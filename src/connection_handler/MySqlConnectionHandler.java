package connection_handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}
