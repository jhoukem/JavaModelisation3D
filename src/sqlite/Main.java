package sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException
	{

		GtsBase lgt = new GtsBase();
		lgt.open();
		ResultSet rs = null;
		try {
			rs = lgt.executeQry("select * from FichiersGts");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			while(rs.next())
			{
				// read the result set
				System.out.println("num = " + rs.getString("id"));
				System.out.println("path = " + rs.getString("path"));
				System.out.println("num = " + rs.getString("title"));
				System.out.println("path = " + rs.getString("des"));
				System.out.println("keyword = " + rs.getString("keyword"));
				
				System.out.println("-------------------------------------");
			}
			System.out.println("-------------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		lgt.close();
		}

	}
}
