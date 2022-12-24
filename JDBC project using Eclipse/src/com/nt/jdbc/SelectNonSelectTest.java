package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {
   public static void main(String[] args) {
	   //read inputs
	   Connection con=null;
	   Scanner sc=null;
	   Statement st=null;
	   ResultSet rs=null;
	try {
		sc=new Scanner(System.in);
		String query=null;
		if(sc!=null) {
			System.out.print("Enter SQL query(Select or Non-Select) :: ");
			query=sc.nextLine();
		}//read inputs
		
		//load jdbc driver calss
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
		//gather the results from datebase s/w and use them
		
		//create jdbc statement object
		if(con!=null) {
			st=con.createStatement();
		}
		//send and execute sql query in db s/w
		if(st!=null) {
			boolean flag=st.execute(query);
			if(flag==true) {
				System.out.println("Select SQL Query Executed");
				//gather and process the ResultSet
				rs=st.getResultSet();
				//process the ResultSet object
				if(rs!=null) {
					while(rs.next()) {
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					}//while
				}//if
				
			}//if
			else {
				System.out.println("Non-Select SQL Query is executed");
				//gather result
				int count=st.getUpdateCount();//long count=st.getLargeUpdateCount();
				System.out.print("No of records that are effected :: "+count);
				
			}//else
		}//if
		}//try
	catch(SQLException se) {
		se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace(); 
	}finally{
		//close jdbc objs
		try {
		if(rs!=null) {
			rs.close();
		 }
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		try {
			if(st!=null) {
				st.close();
			 }
			}catch(SQLException se) {
				se.printStackTrace();
			}
		
		try {
			if(con!=null) {
				con.close();
			 }
			}catch(SQLException se) {
				se.printStackTrace();
			}
		
		try {
			if(sc!=null) {
				sc.close();
			 }
			}catch(Exception se) {
				se.printStackTrace();
			}	
	}
   }//main
}//class
