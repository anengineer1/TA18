package ejercicio_8;

import connection_handler.MySqlConnectionHandler;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Ejercicio_8_App {

	public static void main(String[] args) {

		String ip = JOptionPane.showInputDialog("Introduzca ip");
		String puerto = JOptionPane.showInputDialog("Introduzca puerto");
		String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
		String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

		Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
		String dbase_name = "TA18_Ejercicio_8";

		// create database
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		// create tables
		// Máquinas registradoras
		String tabla_maquinas_registradoras = "maquinas_registradoras";
		String query_maquinas_registradoras = "codigo INT PRIMARY KEY," + "piso INT";
		MySqlConnectionHandler.createTable(dbase_name, tabla_maquinas_registradoras, query_maquinas_registradoras,
				con_handler);

		// cajeros
		String tabla_cajeros = "cajeros";
		String query_cajeros = "codigo INT PRIMARY KEY," + "nomapels NVARCHAR(255)";
		MySqlConnectionHandler.createTable(dbase_name, tabla_cajeros, query_cajeros, con_handler);

		// productos
		String tabla_productos = "productos";
		String query_productos = "codigo INT PRIMARY KEY," + "nombre NVARCHAR(100)," + "precio INT";
		MySqlConnectionHandler.createTable(dbase_name, tabla_productos, query_productos, con_handler);

		// Venta
		String tabla_venta = "venta";
		String query = "fk_codigo_maquina INT," + "fk_codigo_cajero INT," + "fk_codigo_producto INT,"
				+ "PRIMARY KEY (fk_codigo_maquina, fk_codigo_cajero, fk_codigo_producto), "
				+ "FOREIGN KEY (fk_codigo_maquina) REFERENCES " + tabla_maquinas_registradoras + " (codigo) "
				+ "ON DELETE NO ACTION ON UPDATE CASCADE," + "FOREIGN KEY (fk_codigo_cajero) REFERENCES "
				+ tabla_cajeros + " (codigo) " + "ON DELETE NO ACTION ON UPDATE CASCADE,"
				+ "FOREIGN KEY (fk_codigo_producto) REFERENCES " + tabla_productos + " (codigo) "
				+ "ON DELETE NO ACTION ON UPDATE CASCADE";
		MySqlConnectionHandler.createTable(dbase_name, tabla_venta, query, con_handler);

		// Insert maquinas registradoras
		String[] datos_maquinas_registradoras = new String[5];
		datos_maquinas_registradoras[0] = "0, 10";
		datos_maquinas_registradoras[1] = "1, 5";
		datos_maquinas_registradoras[2] = "2, 2";
		datos_maquinas_registradoras[3] = "3, 7";
		datos_maquinas_registradoras[4] = "4, 9";
		for (int i = 0; i < datos_maquinas_registradoras.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tabla_maquinas_registradoras, datos_maquinas_registradoras[i], con_handler);
		}

		// Insert cajeros
		String[] datos_cajeros = new String[5];
		datos_cajeros[0] = "0, 'Naruto Palancas'";
		datos_cajeros[1] = "1, 'Raúl Martínez'";
		datos_cajeros[2] = "2, 'Aitor Menta'";
		datos_cajeros[3] = "3, 'Aitor Tilla'";
		datos_cajeros[4] = "4, 'Carlos Canta'";
		for (int i = 0; i < datos_cajeros.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tabla_cajeros, datos_cajeros[i], con_handler);
		}

		// Insert productos
		String[] datos_productos = new String[5];
		datos_productos[0] = "0, 'Manzanas', 5";
		datos_productos[1] = "1, 'Pera', 3";
		datos_productos[2] = "2, 'Pimientos', 3";
		datos_productos[3] = "3, 'Pizza', 10";
		datos_productos[4] = "4, 'Patatas', 8";
		for (int i = 0; i < datos_productos.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tabla_productos, datos_productos[i], con_handler);
		}

		// Insert venta
		String[] datos_venta = new String[5];
		datos_venta[0] = "0,0,0";
		datos_venta[1] = "2,2,2";
		datos_venta[2] = "3,3,3";
		datos_venta[3] = "4,4,4";
		datos_venta[4] = "1,1,1";
		for (int i = 0; i < datos_venta.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tabla_venta, datos_venta[i], con_handler);
		}

		// print maquinas registradoras
		System.out.println("Tabla " + tabla_maquinas_registradoras);
		MySqlConnectionHandler.printValues(dbase_name, tabla_maquinas_registradoras, con_handler);

		// print cajeros
		System.out.println("Tabla " + tabla_cajeros);
		MySqlConnectionHandler.printValues(dbase_name, tabla_cajeros, con_handler);

		// print productos
		System.out.println("Tabla " + tabla_productos);
		MySqlConnectionHandler.printValues(dbase_name, tabla_productos, con_handler);

		// print venta
		System.out.println("Tabla " + tabla_venta);
		MySqlConnectionHandler.printValues(dbase_name, tabla_venta, con_handler);

		MySqlConnectionHandler.closeConnection(con_handler);

	}

}
