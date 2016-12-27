package com.gom.de.common;

public class Main {
	public static void main(String[] args) {

		new Thread(new ExtractRunnable()).start();
		
//		try
//	    {
//	      String myDriver = "org.gjt.mm.mysql.Driver";
//	      String myUrl = "jdbc:mysql://155.230.90.250:3306/gnoexp?autoReconnect=true&useSSL=false";
//	      Class.forName(myDriver);
//	      Connection conn = DriverManager.getConnection(myUrl, "bchoi000", "Qwert!23");
//	    
//	      // create a sql date object so we can use it in our INSERT statement
//	      Calendar calendar = Calendar.getInstance();
//	      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
//
//	      // the mysql insert statement
//	      String query = " insert into projects (count_external, count_internal, count_total_line, project_name)"
//	        + " values (?, ?, ?, ?)";
//
//	      // create the mysql insert preparedstatement
//	      PreparedStatement preparedStmt = conn.prepareStatement(query);
//	      preparedStmt.setInt(1, 10);
//	      preparedStmt.setInt(2, 20);
//	      preparedStmt.setInt(3, 30);
//	      preparedStmt.setString(4, "test");
//
//	      // execute the preparedstatement
//	      preparedStmt.execute();
//	      
//	      conn.close();
//	    }
//	    catch (Exception e)
//	    {
//	      System.err.println("Got an exception!");
//	      System.err.println(e.getMessage());
//	    }

	}
}
