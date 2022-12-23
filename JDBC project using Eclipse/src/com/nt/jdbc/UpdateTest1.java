package com.nt.jdbc;
/*
 * Non-select query execution
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			String sName=null;
			String sAdd=null;
			Float avg=0.0f;
			int sno=0;
			if(sc!=null) {
				System.out.print("Enter Student StudentName :: ");
				sName=sc.nextLine();//gives Rudra
				sName="'"+sName+"'";
				System.out.print("Enter Student Address (City Name) :: ");
				sAdd=sc.nextLine();//gives  Ganjam
				sAdd="'"+sAdd+"'";
				System.out.print("Enter Student Mark avg :: ");
				avg=sc.nextFloat();//gives  88.8
				System.out.print("Enter Student ID :: ");
				sno=sc.nextInt();//gives  2105290122
			}
			
			
			//registed jdbc driver by loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","tiger");
			
			if(con!=null) {
				st=con.createStatement();
			}
			//prepare the sql query
			//update student set sname='Rudra',sadd='Ganjam',avg=88.8 where sno=103;
			String query="UPDATE STUDENT SET SNAME="+sName+",SADD="+sAdd+",AVG="+avg+"WHERE SNO="+sno;
			System.out.println(query);
			
			//send and execute sql queryin db s/w
			int count=0;
			if(st!=null) {
				count=st.executeUpdate(query);
			}
			
			if(count==0)
				System.out.println("No Records Found for Updation");
			else
				System.out.println("No of records that are Affected is :: "+count);
			
		}catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid column name or table name");
			else if(se.getErrorCode()==12899)
				System.out.println("Column value is too large");
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
