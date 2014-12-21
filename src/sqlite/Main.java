package sqlite;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException
	{
		
		/*AddGts a =new AddGts();
		try {
			a.statement.executeUpdate("insert into FichiersGts values(2,'test.gts')");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		SelectGts lgt = new SelectGts("*");
		 lgt = new SelectGts("*");
		
		try {
			
			while(lgt.rs.next())
			{
				// read the result set
				System.out.println("num = " + lgt.rs.getString("id"));
				System.out.println("path = " + lgt.rs.getString("path"));
				System.out.println("num = " + lgt.rs.getString("title"));
				System.out.println("path = " + lgt.rs.getString("des"));
				System.out.println("num = " + lgt.rs.getString("keyword"));
				
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
