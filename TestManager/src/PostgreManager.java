import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

public class PostgreManager { 

    private String databaseConnectionString = "jdbc:postgresql://localhost/testmanager";
    private String databaseUserName = "postgres";
    private String databaseUserPassword = "9484377";
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
    
    public JTable getResultsTable(String query) {
		try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);)
		{
	        System.out.println("The SQL statement is: " + query + "\n");
	        
	        ResultSet rs = stmt.executeQuery(query);
	        
	        ResultSetMetaData rsmd = rs.getMetaData();
    		rs.last();
    		int numRows = rs.getRow();
    		int numColumns = rsmd.getColumnCount();
    		rs.beforeFirst();
    		String[][] data = new String[numRows][numColumns];
    		String[] columns = new String[numColumns];
    		
    		while (rs.next()) {
    			for (int column = 0; column < numColumns; ++column) {
    				rs.next();
    				data[rs.getRow()-1][column-1] = rs.getString(column);
    			}
    		}
    		for (int column = 0; column < numColumns; ++column) {
    			columns[column] = rsmd.getColumnName(column+1);
    		}
    		
    		JTable table = new JTable(data, columns);
    		table.setFillsViewportHeight(true);
			
	        System.out.println("Success");
	        return table;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}