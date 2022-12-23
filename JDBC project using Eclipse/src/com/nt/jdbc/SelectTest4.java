//SelectTest.java
package com.nt.jdbc;

/*
 * Java Application to get Employee details based on given initial characters Employee name
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {
       public static void main(String[] args) {
    	Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//read inputs
				sc=new Scanner(System.in);
				String initChars=null;
			if(sc!=null) {
				System.out.print("Enter initial character of Employee name ::: ");
			    initChars=sc.next();
			}
			//converting given input value as required sql query
			initChars=initChars.toUpperCase();
			initChars="'"+initChars+"%'";
			
		//register jdbc driver by loading JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
			
			if(con!=null) {
				st=con.createStatement();
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
