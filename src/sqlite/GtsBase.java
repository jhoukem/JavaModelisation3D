package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GtsBase {
	ResultSet rs ;
	Connection connection ;
	Statement statement;
	public GtsBase(){
		
	}
	
	public ResultSet executeQry(String instruction) throws SQLException {
        return statement.executeQuery(instruction);
    } 
	
	public  void executeStmt(String instruction) throws SQLException {
        statement.executeUpdate(instruction);
    }
	
	
	public String[] getList(ResultSet r){
		ArrayList<String> list = new ArrayList<String>();
		try {
			while(r.next())
				list.add(r.getString("path"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tab[] = new String [list.size()];
		for(int i = 0; i < tab.length; i++)
			tab[i]=list.get(i);
		return tab;
	}
	
	
	public void open(){
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
			statement = connection.createStatement();
			statement.setQueryTimeout(5); 		
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}			
	}
	
	
	public void close(){
		try
		{
			if(connection != null){
				statement.close();
				connection.close();			
			}
		}
		catch(SQLException e)
		{
			// connection close failed.
			System.err.println(e);
		}
	}
	
	
	
}
