/**
 * 
 */
package ejercicio_9;

import java.sql.Connection;

import javax.swing.JOptionPane;

import connection_handler.MySqlConnectionHandler;

/**
 * @author Palmira
 *
 */
public class Ejercicio_9_App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Pedimos los datos al usuario
		String ip = JOptionPane.showInputDialog("Introduzca ip");
		String puerto = JOptionPane.showInputDialog("Introduzca puerto");
		String usuario = JOptionPane.showInputDialog("Introduzca nombre de usuario");
		String contrasena = JOptionPane.showInputDialog("Introduzca contrasena");

		Connection con_handler = MySqlConnectionHandler.initConnection(ip, puerto, usuario, contrasena);
		String dbase_name = "TA18_Ejercicio_9";

		// Usamos el metodo para crear la base de datos
		MySqlConnectionHandler.createDB(dbase_name, con_handler);

		// Creamos las variables que usaremos para crear la tabla Facultad
		String tablaFacultad = "facultad";
		String queryFacultad = "codigo INT PRIMARY KEY, " + "nombre VARCHAR(100)";

		// Creamos la tabla Facultad;
		MySqlConnectionHandler.createTable(dbase_name, tablaFacultad, queryFacultad, con_handler);

		// Creamos las variables que usaremos para crear la tabla Investigadores
		String tablaInvestigadores = "investigadores";
		String queryInvestigadores = "dni VARCHAR(8) PRIMARY KEY, " + "nomApels VARCHAR(255), " + "facultad_id INT, "
				+ "FOREIGN KEY(facultad_id) REFERENCES facultad(codigo) " + "ON DELETE CASCADE ON UPDATE CASCADE";

		// Creamos la tabla Investigadores
		MySqlConnectionHandler.createTable(dbase_name, tablaInvestigadores, queryInvestigadores, con_handler);

		// Creamos las variables que tendrá la tabla Equipos
		String tablaEquipos = "equipos";
		String queryEquipos = "numSerie CHAR(4) PRIMARY KEY, " + "nombre VARCHAR(100), " + "facultad_id INT, "
				+ "FOREIGN KEY(facultad_id) REFERENCES facultad(codigo) " + "ON DELETE CASCADE ON UPDATE CASCADE";
		// Creamos la tabla Equipos
		MySqlConnectionHandler.createTable(dbase_name, tablaEquipos, queryEquipos, con_handler);

		// Creamos las variables que tendrá la tabla Reserva
		String tablaReserva = "reserva";
		String queryReserva = "dni_investigador VARCHAR(8), " + "numSerie_equipo CHAR(4), " + "comienzo DATETIME, "
				+ "fin DATETIME, " + "PRIMARY KEY(dni_investigador, numSerie_equipo), " + "KEY(dni_investigador), "
				+ "FOREIGN KEY(dni_investigador) REFERENCES investigadores(dni) "
				+ "ON DELETE CASCADE ON UPDATE CASCADE, " + "KEY(numSerie_equipo), "
				+ "FOREIGN KEY(numSerie_equipo) REFERENCES equipos(numSerie) " + "ON DELETE CASCADE ON UPDATE CASCADE";
		//Creamos la tabla Reserva
		MySqlConnectionHandler.createTable(dbase_name, tablaReserva, queryReserva, con_handler);

	}

}
