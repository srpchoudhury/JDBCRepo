package com.nt.jdbc;
/*
 * Write a JDBC app to insert student values
 */ 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest{

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
		    
			float avg=0.0f;
			int sno=0;
			String sname=null;
			String address=null;

			//registed jdbc driver by loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","tiger");
			
			if(con!=null) {
				st=con.createStatement();
			}
			
			
			//send and execute sql queryin db s/w
			System.out.println(" ::::: Enter student table values :::: ");
			String query=null;
			boolean isInsert=true;
			int count=0;
			while(isInsert) {
				if(sc!=null) {
					System.out.println();
					System.out.print("Enter student sno :: ");
					sno=sc.nextInt();//gives 5%
					sc.nextLine();
					
					
					System.out.print("Enter student name :: ");
					sname=sc.nextLine();//gives  800
					sname="'"+sname+"'";
					
					
					System.out.print("Enter student  Address :: ");
					address=sc.nextLine();//gives  1600
					address="'"+address+"'";
					
					System.out.print("Enter student avg marks :: ");
					avg=sc.nextFloat();
					
					//prepare the sql query
					//SQL> insert into student values (108,'chandu','kadua',85);
					 query="insert into student values ("+sno+","+sname+","+address+","+avg+")";
					System.out.println(query);
					
				}
			if(st!=null) {
				st.executeUpdate(query);
				System.out.println();
				System.out.print("Are you want to insert again then write TRUE else write False :: ");
				
				isInsert=sc.nextBoolean();
				count++;
				if(isInsert==false)
					break;
				
			  }
		
			}
			System.out.println("No of records that are Affected is :: "+count);
			
			
		}catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.out.println("cant insert same student no");
			else if(se.getErrorCode()==1400)
				System.out.println("cant insert null value to sno");
			else if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
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
