package com.nt.jdbc;
/*
 * Non-select query execution
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			String city=null;
			if(sc!=null) {
				System.out.print("Enter Student Address (City Name) :: ");
				city=sc.next();//gives  hyd
			}
			city="'"+city+"'";//gives 'hyd'
			
			//registed jdbc driver by loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","tiger");
			
			if(con!=null) {
				st=con.createStatement();
			}
			//prepare the sql query
			//DELETE FROM STUDENT WHERE SADD='CITY';
			String query="DELETE FROM STUDENT WHERE SADD="+city;
			System.out.println(query);
			
			//send and execute sql queryin db s/w
			int count=0;
			if(st!=null) {
				count=st.executeUpdate(query);
			}
			
			if(count==0)
				System.out.println("No Records Found to Delete");
			else
				System.out.println("No of records that are Affected is :: "+count);
			
		}catch(SQLException se) {
			if(se.getErrorCode()>900 && se.getErrorCode()<999)
				System.out.println("Invalid column name or table name");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			 //close jdbc objs
				 try {
					 if(sc!=null)
						 sc.close();
				 }catch(Exception e) {
					 e.printStackTrace();
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
