package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateRetriveByDateRange {
	private static final String RETRIVE_DATES_QUERY="SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES WHERE DOB>=? AND DOB<=?";
     public static void main(String[] args) {
         Scanner sc=null;
    	 Connection con=null;
    	 PreparedStatement ps=null;
    	 ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			String sdob=null,edob=null;
			if(sc!=null) {
				System.out.print("Enter Start range of DOB(dd-MM-YYYY) :: ");
				sdob=sc.next();
				System.out.print("Enter End range of DOB(dd-MM-YYYY) :: ");
				edob=sc.next();
			}
			
			//convert String date values java.util.Date class obj
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Date ssqdob=new java.sql.Date(sdf1.parse(sdob).getTime());
			java.sql.Date esqdob=new java.sql.Date(sdf1.parse(edob).getTime());
			
			
			//load JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","mydb6pm","MYDB6PM");
			
			//create PreparedStatemnet obj
			if(con!=null) {
				ps=con.prepareStatement(RETRIVE_DATES_QUERY);
			}
			
			//set values to query parameter
			if(ps!=null) {
				ps.setDate(1, ssqdob);
				ps.setDate(2, esqdob);
			}
		
			//execute query
			if(ps!=null) {
				rs=ps.executeQuery();
			}
			
			//process resultSet obj
//			if(rs!=null) {
//				while(rs.next()) {
//					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
//
//				}
//			}
			
			System.out.println("------------------------------");
			
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					int pid=rs.getInt(1);
					String name=rs.getString(2);
				    java.sql.Date sqdob=rs.getDate(3);
				    java.sql.Date sqdoj=rs.getDate(4);
				    java.sql.Date sqdom=rs.getDate(5);
				    
				   //convert java.sql.Date class obj to string date value
				    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				            String dob=sdf.format(sqdob);
				    		String dom=sdf.format(sqdoj);
				    		String doj=sdf.format(sqdom);
					System.out.println(pid+" "+name+" "+dob+" "+doj+" "+doj);
				}//while
				if(flag==false) {
					System.out.println("NO RECORDS ARE FOUND");
				}
			}
		
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//close jdbc objs
			
			try {
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null) {
					ps.close();
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
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally
	}//main
}//class
