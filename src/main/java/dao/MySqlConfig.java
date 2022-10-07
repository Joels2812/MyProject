package dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author march
 */
public class MySqlConfig {
    protected static Connection con;
    protected static void getConnection() throws SQLException{
        
        try {
            String url="jdbc:mysql://myprojecttec.mysql.database.azure.com:3306/myproject?useSSL=true";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"itcr", "PruebasTek1425");
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            
        }
    }
    
    protected static void closeConnection()throws SQLException{
        if(!con.isClosed()){
            con.close();
        }
    }
    
    protected static ResultSet executeStatement(String codeStatement) throws SQLException{
        Statement cmd = con.createStatement();
        ResultSet rs = cmd.executeQuery(codeStatement);
        return rs;
    }
}
