package com.nt.jdbc;
/*
 * Write a JDBC app to hike employee salary by given percentage for the employes whose salary is in the given range(start range to end range) total 3-inputs
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest2 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
		    float  pers=0.0f;
			float startRange=0.0f;
			float endRange=0.0f;
			
			if(sc!=null) {
				System.out.print("Enter Hike Employee salary persentage :: ");
				pers=sc.nextFloat();//gives 5%
				
				System.out.print("Enter Employee salary starting Range :: ");
				startRange=sc.nextFloat();//gives  800
				
				System.out.print("Enter Employee salary ending Range :: ");
				endRange=sc.nextFloat();//gives  1600
				
			}
			
			
			//registed jdbc driver by loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
			
			if(con!=null) {
				st=con.createStatement();
			}
			//prepare the sql query
			//SQL> UPDATE EMP SET SAL=SAL+SAL*5/100 WHERE SAL>=800 and sal<=2000;
			String query="UPDATE EMP SET SAL=SAL+SAL*"+pers+"/100 WHERE SAL>="+startRange+" and sal<="+endRange;
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
