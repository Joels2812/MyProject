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
    protected static Connection con ;
    protected static void getConnection() throws SQLException{
        
        try {
            String url="jdbc:sqlserver://tecproject.database.windows.net:1433;database=myproject;user=tec@tecproject;password=PruebasTek1425;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            
        }
    }
    
    protected static void closeConnection() throws SQLException{
       
        if(con ==null) return;
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
