import java.beans.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class deleteRecord {

public void deleteRecord(String table_name,String ID) {
    
    try {
        String Query = "DELETE FROM "+table_name+"WHERE ID =\""+ID+"\"";
        Statement st = Conexion.createStatement();
        st.executeUpdate(Query);
    } catch (SQLException ex) {
System.out.println(ex.getMessage());
JOptionPane.showMessageDialog(null, "Error borrando el registro especifico");
    }
}

}
