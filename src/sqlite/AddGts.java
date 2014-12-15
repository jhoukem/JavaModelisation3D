package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddGts {
	Connection connection;
	Statement statement;
	
	public AddGts(){
		// load the sqlite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		connection = null;
		
	
	}
	
	
	public void addThis(String file){
		SelectGts gts = new SelectGts("max(id)");
			
			int cpt=0;
			try {				
				gts.rs.next();
				if(gts.rs.getString("max(id)")==null){
				cpt=1;
				}
				else{
				cpt=1+Integer.parseInt(gts.rs.getString("max(id)"));	
				}
				
				System.out.println(cpt);
			} catch (SQLException e) {
				e.printStackTrace();
							
			}
			gts = new SelectGts("*",file);
			
			try {
				if(gts.rs.next())						
					System.out.println("Fichier existant: "+file);
				
				else{
					try {
						gts.close();
						System.out.println("OKI");
						
						open();
						statement.execute("insert into FichiersGts values('"+cpt+"','"+file+"')");
						System.out.println("Le fichier "+file+" à bien été ajouté");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}
					
					
			} catch (SQLException e) {
				e.printStackTrace();				
			
			}
			finally{
				gts.close();
			}			
	}
	
	/*
	public void addThis(String file){
					try {
					
					
						
						open();
						statement.execute("insert into FichiersGts values(4,'"+file+"')");
						System.out.println("Le fichier "+file+" à bien été ajouté");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
			
	}
		*/
	
	
	public void open(){
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
			statement = connection.createStatement();
			statement.setQueryTimeout(5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				System.err.println(e);
			}		
	}
	

}


