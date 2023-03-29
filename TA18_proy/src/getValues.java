import java.beans.Statement;
import java.sql.SQLException;

public class getValues {
    

    public static void getValues(String db,String table_name) {
        try {
            String Querydb = "USE"+db+";";
            Statement stdb = conexion.createStatement();
            stdb.executeUpdate(Querydb);

            String Query = "SLECT * FROM"+table_name;
            Statement st = conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            while (resultSet.next()) {
                System.out.println("ID:"+resultSet.getString("ID") +""+
                "Nombre:"+resultSet.getString("Nombre")+""+
                "Apellido:"+resultSet.getString("Apellido")+""+
                "Edad:"+resultSet.getString("edad")+""+
                "sexo"+resultSet.getString("sexo")
                );
                
            }
        } catch (SQLException ex) {
            
        System.out.println(ex.getMessage());
        System.out.println("Error en la adquisicion de datos");

        }
    }
}
