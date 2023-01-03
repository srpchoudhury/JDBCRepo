//CsProceduretest2.java
package com.nt.jdbc2;

/*
 * CallableStatement with PL/SQL procedures
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
   CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_ID (NO IN NUMBER,NAME OUT VARCHAR2,DESIGNATION OUT VARCHAR2,SALARY OUT NUMBER) AS
    BEGIN
    SELECT ENAME,JOB,SAL INTO NAME,DESIGNATION,SALARY FROM EMP WHERE EMPNO=NO;
     END;
/

*/

public class CsProcedureTest2 {
	private static final String CALL_EMP_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";
  public static void main(String[] args) {
	  int eno=0;
	try(Scanner sc=new Scanner(System.in)) {
		////read inputs
		if(sc!=null) {
		
		System.out.print("Enter Employee N:: ");
		eno=sc.nextInt();
	    
		}
		//ESTABLISH THE CONNECTION
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
				//create callable statement obj having the query calling the pl/sql procedure as the pre-compiled sql query
				CallableStatement cs=con.prepareCall(CALL_EMP_PROCEDURE)) {
			//register output param with jdbc datetype
			if(cs!=null)
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			
			//SET values to IN param
			if(cs!=null)
			cs.setInt(1, eno);
			
			
			//execute or call the pl/sql function
			if(cs!=null)
				cs.execute();
			//gather results from out param
			
			if(cs!=null) {
				String name=cs.getString(2);
				String desg=cs.getString(3);
				String salary=cs.getString(4);
				System.out.println(" Name :: "+name+" Job :: "+desg+" Salary :: "+salary);
			}
			
		}//try 2
	}//try 1
	catch(SQLException se) {
		System.out.println("Requested data is not available");
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
  }//main
}//class
