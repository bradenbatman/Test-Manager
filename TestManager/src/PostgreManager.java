import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgreManager { 

    private String databaseConnectionString = "jdbc:postgresql://localhost/testmanager";
    private String databaseUserName = "postgres";
    private String databaseUserPassword = "root";
	private Connection conn = connect();

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
    
    public void runUpdateQuery(String query) {
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
    
    public ArrayList<ArrayList<Object>> runQuery(String query) {
    	ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
    	
		try (Statement stmt = conn.createStatement();)
		{
	        System.out.println("The SQL statement is: " + query + "\n");
	        
	        ResultSet rset = stmt.executeQuery("Select * from question");
	        
	        try {
				while(rset.next()) {   // Move the cursor to the next row, return false if no more row
					
					ArrayList<Object> sub = new ArrayList<Object>();
					for(int colCount = 1; colCount <= rset.getMetaData().getColumnCount(); colCount++) {
						sub.add(rset.getObject(colCount));
					}
					
					list.add(sub);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        System.out.println("Success");	        
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return list;

	}
}