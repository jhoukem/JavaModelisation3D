package sqlite;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTableFichiersGts 
{
	
	
	public static void main(String[] args) throws ClassNotFoundException
	{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		Statement statement;
		try
		{
			// create a database connection
		connection = DriverManager.getConnection("jdbc:sqlite:./data/bddgts.sqlite");
		statement = connection.createStatement();
		statement.setQueryTimeout(30);  // set timeout to 30 sec.
		statement.executeUpdate("drop table FichiersGts"); 
		statement.executeUpdate("create table FichiersGts (id integer primary key,path text, titre text, des text, motcle text,"
				+ "dateajout text,nbpoints integer,nbsegments integer,nbfaces integer)");
		//statement.executeUpdate("insert into FichiersGts values (1,'cone.gts','title','des','k','date','pts','sgmts','pts')");
		//statement.executeUpdate("insert into FichiersGts values (2,'x_wing.gts')");
		statement.close();
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			try
			{
				if(connection != null){					
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
}

