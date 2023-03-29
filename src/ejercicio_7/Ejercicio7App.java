package ejercicio_7;
import java.sql.Connection;
import connection_handler.MySqlConnectionHandler;
import java.sql.Connection;
import javax.swing.JOptionPane;

import connection_handler.MySqlConnectionHandler;
public class Ejercicio7App {
    public static void main(String[] args) {
    String ip = JOptionPane.showInputDialog("Introduzca ip");
    String puerto = JOptionPane.showInputDialog("Introduzca puerto");
    String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
    String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

    Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
    String dbase_name = "TA18_Ejercicio_7";

    // create database
    MySqlConnectionHandler.createDB(dbase_name, con_handler);

	String nombre_tabla = "proyecto";
		String query_departamentos = "id CHAR(4) PRIMARY KEY," + "Nombre NVARCHAR(100) NOT NULL,"+"Horas int NOT NULL";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query_departamentos, con_handler);
        // cientificos
		nombre_tabla = "cientificos";
		String query_cientificos = "DNI varchar(8) PRIMARY KEY," + "NomApels NVARCHAR(100) NOT NULL,";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query, con_handler);

        nombre_tabla = "asignado_a";
		String query = "cientifico varchar(8) FOREIGN KEY (cientifico) REFERENCES cientificos (DNI)"+"proyecto char(4) FOREIGN KEY (proyecto) REFERENCES proyecto (id)";
		MySqlConnectionHandler.createTable(dbase_name, nombre_tabla, query, con_handler);
		// Insert cientificos
		nombre_tabla = "cientificos";
		String[] datos_cientificos = new String[5];
		datos_cientificos[0] = "118183738G, 'Andrea'";
		datos_cientificos[1] = "347585991F, 'Alberto'";
		datos_cientificos[2] = "273895988R, 'Maria'";
		datos_cientificos[3] = "0939483738G, 'Alex'";
		datos_cientificos[4] = "5894387280Y, 'Alejandro'";
		for (int i = 0; i < datos_cientificos.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_cientificos[i], con_handler);
		}

		// Insert proyecto
		nombre_tabla = "proyecto";
		String[] datos_proyecto = new String[5];
		datos_proyecto[0] = "'0', 'Investigacion 1','10'";
		datos_proyecto[1] = "'1', 'Investigacion 2','30'";
		datos_proyecto[2] = "'2', 'Invetigacion 3','70'";
		datos_proyecto[3] = "'3', 'Investigacion 4','60'";
		datos_proyecto[4] = "'4', 'Investigacion 5','50'";
		for (int i = 0; i < datos_empleados.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, nombre_tabla, datos_proyecto[i], con_handler);
		}
		MySqlConnectionHandler.printValues(dbase_name, nombre_tabla, con_handler);
		con_handler = MySqlConnectionHandler.closeConnection(con_handler);
	}
}

