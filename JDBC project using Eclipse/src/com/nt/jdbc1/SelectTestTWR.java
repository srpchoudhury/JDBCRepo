//SelectTest.java
package com.nt.jdbc1;

/*
 * Java Application to get Employee details based on given initial characters Employee name
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTestTWR {
       public static void main(String[] args) {
    	
		
		
		try (
		
			
		//register jdbc driver by loading JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.171.224:1521:ORCL","SYSTEM","tiger");
		    //create statement object
				Statement st=con.createStatement();
			//send and execute sql query in db s/w
				ResultSet rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT")
				)
		{
			if(con!=null) {
				
			}
			
			//prepare SQL query
			
			//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME like 'S%';
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME like "+initChars;
			System.out.println(query);
			
 
			//send and execute sql query in DB s/w
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			//process the result set object
			boolean flag=false;
			while(rs.next()) {
				flag=true;
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}
			if(flag=false) {
				System.out.println("No Record Found");
			}
		}catch(SQLException se) {
			if(se.getErrorCode()>900 && se.getErrorCode()<999)
				System.out.println("Invalid Coloum name or table name or SQL keywords");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//close jdbc objects
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
			
			try {
				   if(sc!=null)
					   sc.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}
}
