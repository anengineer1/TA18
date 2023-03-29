package ejercicio_1;
import java.sql.Connection;
import connection_handler.MySqlConnectionHandler;
import java.sql.Connection;
import javax.swing.JOptionPane;

import connection_handler.MySqlConnectionHandler;
public class Ejercicio1App {
    public static void main(String[] args) {
    String ip = JOptionPane.showInputDialog("Introduzca ip");
    String puerto = JOptionPane.showInputDialog("Introduzca puerto");
    String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
    String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

    Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
    String dbase_name = "TA18_Ejercicio_1";

    // create database
    MySqlConnectionHandler.createDB(dbase_name, con_handler);

	String nombre_tabla = "fabricantes";
		String query_departamentos = "codigo INT PRIMARY KEY," + "nombre NVARCHAR(100) NOT NULL,";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query_departamentos, con_handler);
// articulos
		nombre_tabla = "articulos";
		String query = "codigo int PRIMARY KEY," + "nombre NVARCHAR(100) NOT NULL," + "precio int NOT NULL,"
				+"FOREIGN KEY (fabricante) REFERENCES fabricantes (codigo)";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query, con_handler);

		// Insert articulos
		nombre_tabla = "articulos";
		String[] datos_articulos = new String[5];
		datos_articulos[0] = "0, 'Monitor', 30,'Nvidia'";
		datos_articulos[1] = "1, 'Raton', 20,'AMD";
		datos_articulos[2] = "2, 'Portatil', 150,'LG'";
		datos_articulos[3] = "3, 'auriculares', 10,'Sound'";
		datos_articulos[4] = "4, 'movil', 100,'Motorola'";
		for (int i = 0; i < datos_articulos.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_articulos[i], con_handler);
		}

		// Insert fabricantes
		nombre_tabla = "fabricantes";
		String[] datos_fabricantes = new String[5];
		datos_fabricantes[0] = "'0', 'Nvidia'";
		datos_fabricantes[1] = "'1', 'AMD'";
		datos_fabricantes[2] = "'2', 'LG'";
		datos_fabricantes[3] = "'3', 'Motorola'";
		datos_fabricantes[4] = "'4', 'Sound'";
		for (int i = 0; i < datos_empleados.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_fabricantes[i], con_handler);
		}
		MySqlConnectionHandler.printValues(dbase_name, nombre_tabla, con_handler);
		con_handler = MySqlConnectionHandler.closeConnection(con_handler);
	}
}

