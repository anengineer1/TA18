package ejercicio_4;

import java.sql.Connection;
import connection_handler.MySqlConnectionHandler;

import javax.swing.JOptionPane;

public class Ejercicio4App {
	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("Introduzca ip");
		String puerto = JOptionPane.showInputDialog("Introduzca puerto");
		String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
		String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

		Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
		String dbase_name = "TA18_Ejercicio_4";

		// create database
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		String nombre_tabla = "peliculas";
		String query_departamentos = "codigo INT PRIMARY KEY," + "nombre NVARCHAR(100) NOT NULL,"
				+ "calificacionedad int";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query_departamentos, con_handler);
// salas
		nombre_tabla = "salas";
		String query = "codigo int PRIMARY KEY," + "nombre NVARCHAR(100) NOT NULL,"
				+ "FOREIGN KEY (pelicula) REFERENCES peliculas (codigo)";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query, con_handler);

		// Insert salas
		nombre_tabla = "salas";
		String[] datos_salas = new String[5];
		datos_salas[0] = "0, 'Royal', 3";
		datos_salas[1] = "1, 'Liceo', 2";
		datos_salas[2] = "2, 'Nana', 1";
		datos_salas[3] = "3, 'Sala3', 0";
		datos_salas[4] = "4, 'Salala', 4";
		for (int i = 0; i < datos_salas.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_salas[i], con_handler);
		}

		// Insert peliculas
		nombre_tabla = "peliculas";
		String[] datos_peliculas = new String[5];
		datos_peliculas[0] = "'0', 'Nvidia','3'";
		datos_peliculas[1] = "'1', 'AMD','4";
		datos_peliculas[2] = "'2', 'LG','2";
		datos_peliculas[3] = "'3', 'Motorola','3";
		datos_peliculas[4] = "'4', 'Sound','1";
		for (int i = 0; i < datos_peliculas.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_peliculas[i], con_handler);
		}
		MySqlConnectionHandler.printValues(dbase_name, nombre_tabla, con_handler);
		con_handler = MySqlConnectionHandler.closeConnection(con_handler);
	}
}
