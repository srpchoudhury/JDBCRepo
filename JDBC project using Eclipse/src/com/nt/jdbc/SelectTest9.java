package com.nt.jdbc;

/*
 * Write a JDBC App to get Employee details whose having "nth" highest salary.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest9 {
	public static void main(String[] args) {
		 Scanner sc=null;
		 Connection con=null;
		 Statement st=null;
		 ResultSet rs=null;
		 try {
			 sc=new Scanner(System.in);
			 int position=0;
			 System.out.print("Enter the wanted highest salry position :: ");
			 position=sc.nextInt();
			position=position-1;
			 
			 
			 //loading JDBC driver class
			 //Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
			 
			 if(con!=null) {
			 st=con.createStatement();
			 }
			 
			 //prepare sql query
			 //SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM EMP E1 WHERE 1= (SELECT COUNT (DISTINCT SAL)  FROM EMP E2 WHERE E2.SAL>E1.SAL);
			 
			 String query="SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM EMP E1 WHERE "+position+"= (SELECT COUNT (DISTINCT SAL) FROM EMP E2 WHERE E2.SAL<E1.SAL)";
			// String query="SELECT EMPNO,ENAME FROM EMP E1 WHERE "+position+"= (SELECT COUNT (DISTINCT SAL) FROM EMP E2 WHERE E2.SAL<E1.SAL)";
			// String query="SELECT EMPNO,ENAME FROM EMP WHERE EMPNO="+position;
			 System.out.println(query);
			 
			 if(st!=null) {
			 rs=st.executeQuery(query);
			 }
			 
			 boolean flag=false;
			 while(rs.next()){
				 flag=true;
				 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getFloat(6)+" "+rs.getFloat(7)+" "+rs.getInt(8));
				 //System.out.println(rs.getInt(1)+" "+rs.getString(2));
			 }
			 if(flag==false) {
				 System.out.println("No Record Found");
			 }          
		 }catch(SQLException se) {
			 if(se.getErrorCode()>900 && se.getErrorCode()<999)
				 System.out.println("Error in table name or in cloumn name");
			 se.getMessage();
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
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		 }//finally
	}//main
}//class
