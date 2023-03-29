/**
 * 
 */
package ejercicio_6;

import java.sql.Connection;

import javax.swing.JOptionPane;

import connection_handler.MySqlConnectionHandler;

/**
 * @author Palmira
 *
 */
public class Ejercicio_6_App {

	public static void main(String[] args) {
		// Pedimos los datos al usuario
		String ip = JOptionPane.showInputDialog("Introduzca ip");
		String puerto = JOptionPane.showInputDialog("Introduzca puerto");
		String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
		String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

		Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
		String dbase_name = "TA18_Ejercicio_6";

		// Usamos el metodo para crear la base de datos
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		// creamos las variables que usaremos para crear la tabla Piezas
		String tablaPiezas = "piezas";
		String queryPiezas = "codigo INT PRIMARY KEY, " + "nombre VARCHAR(100)";

		// Creamos la tabla Piezas
		MySqlConnectionHandler.createTable(dbase_name, tablaPiezas, queryPiezas, con_handler);

		// Creamos las variables que usaremos para crear la tabla Proveedores
		String tablaProveedores = "proveedores";
		String queryProveedores = "id CHAR(4) PRIMARY KEY, " + "nombre VARCHAR(100)";

		// Creamos la tabla Proveedores
		MySqlConnectionHandler.createTable(dbase_name, tablaProveedores, queryProveedores, con_handler);

		// Creamos las variables que usaremos para crear la tabla Suministra
		String tablaSuministra = "suministra";
		String querySuministra = "codigoPieza INT, " + "idProveedor CHAR(4), " + "precio INT, "
				+ "PRIMARY KEY (codigoPieza, idProveedor)," + "KEY(codigoPieza), "
				+ "FOREIGN KEY (codigoPieza) REFERENCES piezas(codigo) " + "ON DELETE CASCADE ON UPDATE CASCADE, "
				+ "KEY(idProveedor), " + "FOREIGN KEY(idProveedor) REFERENCES proveedores(id) "
				+ "ON DELETE CASCADE ON UPDATE CASCADE";

		// Creamos la tabla Suministra
		MySqlConnectionHandler.createTable(dbase_name, tablaSuministra, querySuministra, con_handler);

		// Inserción en la tabla Piezas
		String[] datos_Piezas = new String[5];
		datos_Piezas[0] = "1, 'tornillo'";
		datos_Piezas[1] = "2, 'martillo'";
		datos_Piezas[2] = "3, 'destornillador'";
		datos_Piezas[3] = "4, 'llave inglesa'";
		datos_Piezas[4] = "5, 'taladro'";

		for (int i = 0; i < datos_Piezas.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tablaPiezas, datos_Piezas[i], con_handler);
		}

		// Inserción en la tabla Proveedores
		String[] datos_Proveedores = new String[5];
		datos_Proveedores[0] = "'1D5E', 'Bosch'";
		datos_Proveedores[1] = "'1R7T', 'Black&Decker'";
		datos_Proveedores[2] = "'2V6R', 'Dexter'";
		datos_Proveedores[3] = "'3M4N', 'Dewalt'";
		datos_Proveedores[4] = "'8Z5L', 'Makita'";

		for (int i = 0; i < datos_Piezas.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tablaProveedores, datos_Proveedores[i], con_handler);
		}

		// Inserción en la tabla Suministra
		String[] datos_Suministra = new String[5];
		datos_Suministra[0] = "2, '1D5E', 15";
		datos_Suministra[1] = "5, '1R7T', 150";
		datos_Suministra[2] = "1, '2V6R', 5";
		datos_Suministra[3] = "3, '3M4N', 15";
		datos_Suministra[4] = "4, '8Z5L', 40";

		for (int i = 0; i < datos_Suministra.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tablaSuministra, datos_Suministra[i], con_handler);
		}

		// Imprimimos la tabla Piezas
		System.out.println("Tabla " + tablaPiezas);
		MySqlConnectionHandler.printValues(dbase_name, tablaPiezas, con_handler);

		// Imprimimos la tabla Proveedores
		System.out.println("Tabla " + tablaProveedores);
		MySqlConnectionHandler.printValues(dbase_name, tablaProveedores, con_handler);

		// Imprimimos la tabla Suministra
		System.out.println("Tabla " + tablaSuministra);
		MySqlConnectionHandler.printValues(dbase_name, tablaSuministra, con_handler);

		// Cerrar conexion
		MySqlConnectionHandler.closeConnection(con_handler);

	}

}
