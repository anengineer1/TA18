package ejercicio_2;

import connection_handler.MySqlConnectionHandler;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Ejercicio_2_App {

	public static void main(String[] args) {

		String ip = JOptionPane.showInputDialog("Introduzca ip");
		String puerto = JOptionPane.showInputDialog("Introduzca puerto");
		String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
		String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

		// Connection con_handler = MySqlConnectionHandler.initConnection("127.0.0.1", "10023", "remote", "cuser90r");
		Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
		String dbase_name = "TA18_Ejercicio_2";

		// create database
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		// create tables
		// departamentos
		String nombre_tabla = "departamentos";
		String query_departamentos = "codigo INT PRIMARY KEY," + "nombre NVARCHAR(100) NOT NULL," + "presupuesto INT";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query_departamentos, con_handler);

		// empleados
		nombre_tabla = "empleados";
		String query = "dni VARCHAR(8)," + "nombre NVARCHAR(100) NOT NULL," + "apellidos NVARCHAR(255),"
				+ "departamento INT," + "FOREIGN KEY (departamento) REFERENCES departamentos (codigo)"
				+ "ON DELETE NO ACTION ON UPDATE CASCADE," + "PRIMARY KEY (dni, departamento)";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query, con_handler);

		// Insert departamentos
		nombre_tabla = "departamentos";
		String[] datos_departamentos = new String[5];
		datos_departamentos[0] = "0, 'IT', 30000";
		datos_departamentos[1] = "1, 'Recursos Humanos', 20000";
		datos_departamentos[2] = "2, 'Mecánica', 15000";
		datos_departamentos[3] = "3, 'Ventas', 10000";
		datos_departamentos[4] = "4, 'Compras', 10000";
		for (int i = 0; i < datos_departamentos.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_departamentos[i], con_handler);
		}

		// Insert empleados
		nombre_tabla = "empleados";
		String[] datos_empleados = new String[5];
		datos_empleados[0] = "'23456789', 'García', 'Palancas Nieto', 0";
		datos_empleados[1] = "'98764322', 'Naruto', 'Palancas Nieto', 1";
		datos_empleados[2] = "'23456666', 'Saitama', 'One Punch', 2";
		datos_empleados[3] = "'11111111', 'Remiendos', 'Piedra Riba', 3";
		datos_empleados[4] = "'44444444', 'Valentino', 'Ataúrco Remiendos', 4";
		for (int i = 0; i < datos_empleados.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_empleados[i], con_handler);
		}
		MySqlConnectionHandler.printValues(dbase_name, nombre_tabla, con_handler);
		con_handler = MySqlConnectionHandler.closeConnection(con_handler);
	}

}
