package ejercicio_2;

import connection_handler.MySqlConnectionHandler;
import java.sql.Connection;

public class Ejercicio_2_App {

	public static void main(String[] args) {
		Connection con_handler = MySqlConnectionHandler.initConnection("127.0.0.1", "10023", "remote", "cuser90r");

		String dbase_name = "TA18_Ejercicio_2";
		
		
		// create database	
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		// create tables
		// departamentos
		String nombre_tabla = "departamentos";
		String query_departamentos = "codigo INT PRIMARY KEY,"
				+ "nombre NVARCHAR(100) NOT NULL,"
				+ "presupuesto INT";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query_departamentos, con_handler);
		
		// empleados
		nombre_tabla = "empleados";
		String query = "dni VARCHAR(8),"
				+ "nombre NVARCHAR(100) NOT NULL,"
				+ "apellidos NVARCHAR(255),"
				+ "departamento INT,"
				+ "FOREIGN KEY (departamento) REFERENCES departamentos (codigo)"
				+ "ON DELETE NO ACTION ON UPDATE CASCADE,"
				+ "PRIMARY KEY (dni, departamento)";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query, con_handler);

		
		// Insert departamentos
		nombre_tabla = "departamentos";
		query = "0, 'Labrador Megalómano', 30000";
		MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, query, con_handler);

		
		// Insert empleados
		nombre_tabla = "empleados";
		query = "'23456789', 'García', 'Palancas Nieto', 0";
		MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, query, con_handler);
		
		
		
		con_handler = MySqlConnectionHandler.closeConnection(con_handler);
	}



}
