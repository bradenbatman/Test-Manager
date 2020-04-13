import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

			new GUI();
			String url = "jdbc:postgresql://localhost/testmanager";
			Properties props = new Properties();
			props.setProperty("user","postgres");
			props.setProperty("password","root");
			
			
			
			Connection conn = null;
			Statement stmt = null;
			try {
				conn = DriverManager.getConnection(url, props);
				stmt = conn.createStatement();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			try {
				System.out.println(stmt.executeQuery("SELECT * FROM question"));
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
	
	void printTbl(ResultSet rst) throws Exception{
		ResultSetMetaData meta = rst.getMetaData();
		int numCols = meta.getColumnCount();
		for(int i=1;1<=numCols;i++) {
			System.out.println(meta.getColumnName(i));
		}
		while(rst.next()) {
			for(int i=1;i<numCols;i++) {
				System.out.println(rst.getString(i));
			}
		}
		
	}
}
