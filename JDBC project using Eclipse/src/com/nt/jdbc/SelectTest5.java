//SelectTest5.java
package com.nt.jdbc;

/*
 * Java Application to get dept details  based on given deptno
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest5 {
         public static void main(String[] args) {
			Scanner sc=null;
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			try {
				sc=new Scanner(System.in);
				int dno=0;
				if(sc!=null) {
					System.out.print("Enter dept no :: ");
				dno=sc.nextInt();//gives 1
				}
				//load jdbc driver class
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
				
				//create jdbc statement object
				if(con!=null) {
					st=con.createStatement();
				}
				//prepare sql query
				// SELECT * FROM DEPT WHERE DEPTNO=10;
				String query=" SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO="+dno;
				System.out.println(query);
				
				//send and execute the sql query
				if(st!=null)
				rs=st.executeQuery(query);
				
				//process the ResultSet (0 or 1 record)
				if(rs!=null) {
					if(rs.next())
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					else
						System.out.println("No Record Found");
				}//if
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//close jdbc objs
				try {
					if(rs!=null)
						rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
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
				
				try {
					if(sc!=null)
						sc.close();
				}catch(Exception se) {
					se.printStackTrace();
				}
				
			}//finally
		}//main
}//class
