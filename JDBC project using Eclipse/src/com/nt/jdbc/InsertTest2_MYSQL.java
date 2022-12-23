package com.nt.jdbc;
/*
 * Write a JDBC app to insert into emp db table only 4 columns(eno,ename,job,sal) by collecting from enduser
 */ 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest2_MYSQL{

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
		   int eno=0;
		   String ename=null;
		   String job=null;
		   float sal=0.0f;
			if(sc!=null) {
				System.out.print("Enter employee no :: ");
				eno=sc.nextInt();
				sc.nextLine();
				
				
				System.out.print("Enter Employee name :: ");
				ename=sc.nextLine();
				ename="'"+ename+"'";
				
				System.out.print("Enter Employee  job :: ");
			    job=sc.nextLine();
				job="'"+job+"'";
				
				System.out.print("Enter Employee sal :: ");
				sal=sc.nextFloat();
				
			}
			
			
			//registed jdbc driver by loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
			
			if(con!=null) {
				st=con.createStatement();
			}
			//prepare the sql query
			//SQL> insert into emp (empno,ename,job,sal) values (404,'DSAF','DSAG',54);
			String query="INSERT INTO  EMP (EMPNO,ENAME,JOB,SAL) VALUES ("+eno+","+ename+","+job+","+sal+")";
			System.out.println(query);
			
			//send and execute sql queryin db s/w
			int count=0;
			if(st!=null) {
				count=st.executeUpdate(query);
			}
			
				System.out.println("No of records that are Affected is :: "+count);
			
		}catch(SQLException se) {
			if(se.getErrorCode()==1400)
				System.out.println("cant insert null value to empno");
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
