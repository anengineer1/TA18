/**
 * 
 */
package ejercicio_3;

import java.sql.Connection;

import javax.swing.JOptionPane;

import connection_handler.MySqlConnectionHandler;

/**
 * @author Palmira
 *
 */
public class Ejercicio_3_App {

	public static void main(String[] args) {
		// Pedimos los datos al usuario
		String ip = JOptionPane.showInputDialog("Introduzca ip");
		String puerto = JOptionPane.showInputDialog("Introduzca puerto");
		String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
		String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

		Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
		String dbase_name = "TA18_Ejercicio_3";

		// Usamos el metodo para crear la base de datos
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		// creamos las variables que usaremos para crear la tabla almacenes
		String tablaAlmacenes = "almacenes";
		String queryAlmacenes = "codigo INT PRIMARY KEY, " + "lugar VARCHAR(100), " + "capacidad INT";
		// Creamos la tabla almacenes
		MySqlConnectionHandler.createTable(dbase_name, tablaAlmacenes, queryAlmacenes, con_handler);
		// creamos las variables que usaremos para crear la tabla cajas
		String tablaCajas = "cajas";
		String queryCajas = "numReferencia CHAR(5) PRIMARY KEY, " + "contenido VARCHAR(100), " + "almacen_id INT, "
				+ "CONSTRAINT almacen_id FOREIGN KEY(almacen_id)" + "" + "REFERENCES almacenes (codigo) "
				+ "ON DELETE CASCADE ON UPDATE CASCADE";
		// Creamos la tabla Cajas
		MySqlConnectionHandler.createTable(dbase_name, tablaCajas, queryCajas, con_handler);

		// Inserción en la tabla Almacenes
		String[] datos_Almacenes = new String[5];
		datos_Almacenes[0] = "1, 'Tarragona', 2";
		datos_Almacenes[1] = "2, 'Lleida', 5";
		datos_Almacenes[2] = "3, 'Nueva York', 10";
		datos_Almacenes[3] = "4, 'Edimburgo', 4";
		datos_Almacenes[4] = "5, 'Paris', 6";
		
		for(int i = 0; i<datos_Almacenes.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tablaAlmacenes, datos_Almacenes[i], con_handler);
		}

	}

}
