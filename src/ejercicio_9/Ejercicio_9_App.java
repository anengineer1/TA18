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
		// Creamos la tabla Reserva
		MySqlConnectionHandler.createTable(dbase_name, tablaReserva, queryReserva, con_handler);

		// Inserciones en la tabla Facultad
		String[] datos_Facultad = new String[5];
		datos_Facultad[0] = "1, 'Medicina'";
		datos_Facultad[1] = "2, 'Química'";
		datos_Facultad[2] = "3, 'Física'";
		datos_Facultad[3] = "4, 'Biología'";
		datos_Facultad[4] = "5, 'MicroBiologia'";
		for (int i = 0; i < datos_Facultad.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tablaFacultad, datos_Facultad[i], con_handler);
		}

		// Inserciones en la tabla Investigadores
		String[] datos_Investigadores = new String[5];
		datos_Investigadores[0] = "'4788597C', 'Palmira Romia', 4";
		datos_Investigadores[1] = "'7445589D', 'Francisco Parra', 3";
		datos_Investigadores[2] = "'1457878T', 'Alejandro Jimenez', 1";
		datos_Investigadores[3] = "'4789645S', 'Jose Marin', 5";
		datos_Investigadores[4] = "'1478889A', 'Emma Stone', 2";
		for (int i = 0; i < datos_Investigadores.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tablaInvestigadores, datos_Investigadores[i], con_handler);
		}

		// Inserciones en la tabla Equipos
		String[] datos_Equipos = new String[5];
		datos_Equipos[0] = "'ADR4', 'Alfa', 1";
		datos_Equipos[1] = "'DER2', 'Beta', 4";
		datos_Equipos[2] = "'DFT6', 'Gamma', 2";
		datos_Equipos[3] = "'GRE1', 'Delta', 5";
		datos_Equipos[4] = "'RCG9', 'Epsilon', 4";
		for (int i = 0; i < datos_Equipos.length; i++) {
			MySqlConnectionHandler.insertData(dbase_name, tablaEquipos, datos_Equipos[i], con_handler);
		}
		
		//Inserciones en la tabla Reserva
		String[] datos_Reserva = new String[5];

	}

}
