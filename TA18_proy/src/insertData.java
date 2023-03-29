import java.beans.Statement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class insertData {
    public static void insertData(String db,String table_name,String name,String lastname,String age,String gender){

        try {
            String Querydb = "USE"+db+";";
            Statement stdb = conexion.createStatement():
            stdb.executeUpdate(Querydb);

            String Query ="Insert Into"+table_name+"(Nombre,Apellidos,Edas,Sexo) value("+"\""+name+"\","+
            "\""+lastname+"\","+"\""+age+"\","+gender+"\");";
            Statement st = conexion.createStatement();
            st.executeUpdate(Query);
            System.out.println("Datos almacenados correctos");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
        }

    }
    
}
