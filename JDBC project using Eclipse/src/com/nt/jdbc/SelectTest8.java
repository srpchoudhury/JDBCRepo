package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Write a JDBC App to get Employee details whose having lowest salary
 */
public class SelectTest8 {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//load the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
		
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
				
				if(con!=null)
					st=con.createStatement();
			//preparing the sql query
				String query="SELECT EMPNO,ENAME,JOB,SA FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
				System.out.println(query);
				
				if(st!=null) {
					rs=st.executeQuery(query);
				}
				
				boolean flag=false;
				while(rs.next()!=false){
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
				if(flag==false) {
					System.out.println("NO RECORD FOUND");
				}
				
				
		}catch(SQLException se) {
			if(se.getErrorCode()>900 && se.getErrorCode()<999)
				System.out.println("Table name or Column name is Invalid");
				se.getMessage();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
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
		}
	}

}
