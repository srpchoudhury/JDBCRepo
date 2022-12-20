//SelectTest6WithMYSQL.java
package com.nt.jdbc;

/*
 * Java Application to get highest sal employee details
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest7 {
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
				//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP);
				String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
				System.out.println(query);
				
				//send and execute the sql query
				if(st!=null)
				rs=st.executeQuery(query);
				
				//process the ResultSet (0 or more record)
				if(rs!=null) {
					boolean flag=false;
					while(rs.next()) {
						flag=true;
					   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));	
					}//while
					if(flag==false)
						System.out.println("Records Not FOund");
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
