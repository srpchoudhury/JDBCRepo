package com.nt.jdbc1;

/*
 * select test using prepared statement and precompiled query
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsSelectTest1 {
	private static final String SELECT_EMP_QUERY="SELECT EMPNO,ENAME,SAL,JOB,DEPTNO FROM EMP WHERE DEPTNO=?";
      public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
    	  try {
    		  sc=new Scanner(System.in);
    		  if(sc!=null) {
			System.out.println("Enter DEPTNO :: ");
			no=sc.nextInt();
    		  }
			
			//load jdbc driver class
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
			
			//establish the connection
			if(con!=null) {
				ps=con.prepareStatement(SELECT_EMP_QUERY);
				ps.setInt(1, no);
			}
			
			if(ps!=null) {
				rs=ps.executeQuery();
			}
		 
		  int count=0;
			if(rs!=null) {
				while(rs.next()) {
					
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4)+" "+rs.getInt(5));
				 count++;
				}
			}
			if(count==0) {
				System.out.println("NO RECORD FOUND");
			}
			else {
				System.out.println(count+" NO OF RECORD FOUND");
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
			}catch(Exception se) {
				se.printStackTrace();
			}
		}//try
	}//main
}//class
