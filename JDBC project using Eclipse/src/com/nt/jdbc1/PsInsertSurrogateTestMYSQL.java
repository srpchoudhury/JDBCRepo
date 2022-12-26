package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertSurrogateTestMYSQL {
    private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT (SNAME,SADD) VALUES (?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter Students Count :: ");
				count=sc.nextInt();
			}
			
			//register jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish the connection
			//con=DriverManager.getConnection("jdbc:mysql:///MYDB6PM","root","ROOT");
		
			con=DriverManager.getConnection("jdbc:mysql:///mydb6pm","root","ROOT");
			
			
			//create preparedStatement 
         if(con!=null)
			ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			//read inputs from end user,set them to query param values and execite the pre-compiled sql query form multiple times
			
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i ) {
					System.out.println(":: Enter "+i+" Student Details :: ");
				
					System.out.print("Enter Student Name :: ");
					String name=sc.next();
					System.out.print("Enter Student Adress :: ");
					String addrs=sc.next();
					
					
					
					//set each student details as pre-compiled sql query params
				  ps.setString(1, name);ps.setString(2, addrs);
					//execute pre-compiled sql query each time
					int result=ps.executeUpdate();
							
							//process execution result of pre compiled sql query
							if(result==0) 
								System.out.println(i+" Student details not inserted");
								else
								System.out.println(i+ " Student details are insertd");
				}//for
			}//if
		}//try
			catch(SQLException se) {
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//close jdbc objs
				try {
					if(ps!=null)
						ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
				try {
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
				try {
					if(sc!=null)
						sc.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}//finally
			
			
		

	}//main

}//class
