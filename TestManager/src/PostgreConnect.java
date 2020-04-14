import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnect { 

    private String databaseConnectionString = "jdbc:postgresql://localhost/testmanager";

    private String databaseUserName = "postgres";

    private String databaseUserPassword = "root";

    public Connection connect() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(databaseConnectionString, databaseUserName, databaseUserPassword );
            System.out.println("You are successfully connected to the PostgreSQL database server.");

        } catch (SQLException e)
        {
        	System.out.println(e.getMessage());
        }
        return conn;
    }
}