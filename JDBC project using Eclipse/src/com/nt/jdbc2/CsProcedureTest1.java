//CsProceduretest1.java
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
   CREATE OR REPLACE PROCEDURE FIRST_PRO (X IN NUMBER,Y IN NUMBER,Z OUT NUMBER) AS
   BEGIN
   Z:=X+Y;
   END;
*/

public class CsProcedureTest1 {
	private static final String CALL_PROCEDURE="{CALL FIRST_PRO(?,?,?)}";
  public static void main(String[] args) {
	  int first=0,second=0;
	try(Scanner sc=new Scanner(System.in)) {
		if(sc!=null) {
		//read inputs
		System.out.print("Enter first value :: ");
		first=sc.nextInt();
	    System.out.print("Enter second value :: ");
	    second=sc.nextInt();
		}
		//ESTABLISH THE CONNECTION
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
				//create callable statement obj having the query calling the pl/sql procedure as the pre-compiled sql query
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE)) {
			//register output param with jdbc datetype
			if(cs!=null)
			cs.registerOutParameter(3, Types.INTEGER);
			//SET values to IN param
			if(cs!=null)
			cs.setInt(1, first);
			cs.setInt(2, second);
			
			//execute or call the pl/sql function
			if(cs!=null)
				cs.execute();
			//gather results from out param
			int result=0;
			if(cs!=null) {
				result=cs.getInt(3);
				System.out.println("Sum is ::: "+result);
			}
			
		}//try 2
	}//try 1
	catch(SQLException se) {
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
  }//main
}//class
