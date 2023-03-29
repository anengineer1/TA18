package ejercicio_5;

import connection_handler.MySqlConnectionHandler;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Ejercicio_5_App {

	public static void main(String[] args) {

		String ip = JOptionPane.showInputDialog("Introduzca ip");
		String puerto = JOptionPane.showInputDialog("Introduzca puerto");
		String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
		String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

		Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
		String dbase_name = "TA18_Ejercicio_5";

		// create database
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		// create tables
		// despachos
		String tabla_despachos = "despachos";
		String query_departamentos = "numero INT PRIMARY KEY," + "presupuesto INT";
		MySqlConnectionHandler.createTable(dbase_name, tabla_despachos, query_departamentos, con_handler);

		// directores
		String tabla_directores = "directores";
		String query = "dni VARCHAR(8) NOT NULL PRIMARY KEY," + "nom_apels NVARCHAR(255)," + "dni_jefe VARCHAR(8),"
				+ "despacho INT NOT NULL," + "KEY (dni_jefe), " + "FOREIGN KEY (dni_jefe) REFERENCES directores (dni) "
				+ "ON DELETE NO ACTION ON UPDATE CASCADE," + "KEY (despacho), "
				+ "FOREIGN KEY (despacho) REFERENCES despachos (numero) " + "ON DELETE NO ACTION ON UPDATE CASCADE";
		MySqlConnectionHandler.createTable(dbase_name, tabla_directores, query, con_handler);

		// Insert despachos
		String[] datos_despachos = new String[5];
		datos_despachos[0] = "0, 10";
		datos_despachos[1] = "1, 5";
		datos_despachos[2] = "2, 2";
		datos_despachos[3] = "3, 7";
		datos_despachos[4] = "4, 9";
		for (int i = 0; i < datos_despachos.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tabla_despachos, datos_despachos[i], con_handler);
		}

		// Insert directores
		String[] datos_empleados = new String[5];
		datos_empleados[0] = "'23456789', 'García Palancas', null, 0";
		datos_empleados[1] = "'98764322', 'Naruto Palancas', '23456789', 1";
		datos_empleados[2] = "'23456666', 'Saitama One', '23456789', 2";
		datos_empleados[3] = "'11111111', 'Remiendos Piedra', '23456789', 3";
		datos_empleados[4] = "'44444444', 'Valentino Remiendos', '23456789', 4";
		for (int i = 0; i < datos_empleados.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tabla_directores, datos_empleados[i], con_handler);
		}

		// print despachos
		System.out.println("Tabla " + tabla_despachos);
		MySqlConnectionHandler.printValues(dbase_name, tabla_despachos, con_handler);

		// print directores
		System.out.println("Tabla " + tabla_directores);
		MySqlConnectionHandler.printValues(dbase_name, tabla_directores, con_handler);

		MySqlConnectionHandler.closeConnection(con_handler);

	}

}
