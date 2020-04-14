import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {

			new GUI();
			
			PostgreConnect connector = new PostgreConnect();
			Connection conn = connector.connect();
			
			try (Statement stmt = conn.createStatement();)
			{
				String strSelect = "select * from question";
		        System.out.println("The SQL statement is: " + strSelect + "\n");
		        
		        ResultSet rset = stmt.executeQuery(strSelect);
		        System.out.println("The records selected are:");
		        int rowCount = 0;
		        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
		           int id = rset.getInt("qID");
		           String question = rset.getString("question");
		           String answer = rset.getString("answer");
		           String inc_1 = rset.getString("incorrect1");
		           String inc_2 = rset.getString("incorrect2");
		           String inc_3 = rset.getString("incorrect3");
		           
		           System.out.println(id + ", " + question + ", " + answer + ", " + inc_1 + ", " + inc_2 + ", " + inc_3);
		           ++rowCount;
		        }
		        System.out.println("Total number of records = " + rowCount);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
