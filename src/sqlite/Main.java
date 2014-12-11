package sqlite;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException
	{
		SelectGts lgt = new SelectGts();
		


		try {

			while(lgt.rs.next())
			{
				// read the result set
				System.out.println("num = " + lgt.rs.getString("id"));
				System.out.println("path = " + lgt.rs.getString("path"));
				System.out.println("-------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lgt.close();

	}
}
