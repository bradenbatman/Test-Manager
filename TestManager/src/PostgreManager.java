import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreManager {

    private String databaseConnectionString = "jdbc:postgresql://localhost/testmanager";
    private String databaseUserName = "postgres";

    private String databaseUserPassword = "9484377";

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

    public void runQuery(String query) {
		try (Statement stmt = conn.createStatement();)
		{
	        System.out.println("The SQL statement is: " + query + "\n");

	        stmt.executeUpdate(query);

	        System.out.println("Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
