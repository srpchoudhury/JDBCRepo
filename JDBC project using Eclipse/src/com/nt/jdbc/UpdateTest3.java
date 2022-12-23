package com.nt.jdbc;
/*
 * Write a JDBC app to add given percentage of marks to existing avg based on the given 3 city
 * names as the address for student.(4-inputs)
 */ 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest3 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
		    
			float pers=0.0f;
			String fAdd=null,sdd=null,tAdd=null;
			if(sc!=null) {
				System.out.print("Enter avg mark persentage :: ");
				pers=sc.nextFloat();//gives 5%
				sc.nextLine();
				
				System.out.print("Enter first Address :: ");
				fAdd=sc.nextLine();//gives  800
				fAdd="'"+fAdd+"'";
				
				System.out.print("Enter Second Address :: ");
				sdd=sc.nextLine();//gives  1600
				sdd="'"+sdd+"'";
				
				System.out.print("Enter Third Address :: ");
				tAdd=sc.nextLine();//gives  1600
				tAdd="'"+tAdd+"'";
				
			}
			
			
			//registed jdbc driver by loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","tiger");
			
			if(con!=null) {
				st=con.createStatement();
			}
			//prepare the sql query
			//SQL> update student set avg=avg+avg*5/100 where sadd in('bbsr','bang','kadua');
			String query="UPDATE STUDENT SET AVG=AVG+AVG*"+pers/100+" WHERE SADD IN ("+fAdd+","+sdd+","+tAdd+")";
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
