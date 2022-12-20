//SelectTest6.java
package com.nt.jdbc;

/*
 * Java Application to get student details
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {
         public static void main(String[] args) {
        	 Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			try {
    				//load jdbc driver class
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
				
				//create jdbc statement object
				if(con!=null) {
					st=con.createStatement();
				}
				//prepare sql query
				// SELECT COUNT(*) FROM STUDENT;
				String query=" SELECT COUNT(*) FROM STUDENT";
				System.out.println(query);
				
				//send and execute the sql query
				if(st!=null)
				rs=st.executeQuery(query);
				
				//process the ResultSet (0 or 1 record)
				if(rs!=null) {
					rs.next();
					//int count=rs.getInt(1);
					int count=rs.getInt("COUNT(*)");
					System.out.println("Records count in Student DB table :: "+count);
				}//if
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//close jdbc objs
				try {
					if(rs!=null)
						rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(st!=null)
						st.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(con!=null)
						con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			
			}//finally
		}//main
}//class
