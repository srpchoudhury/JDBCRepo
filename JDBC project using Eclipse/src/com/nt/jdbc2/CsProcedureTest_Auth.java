package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
CREATE OR REPLACE PROCEDURE P_AUTHENTICATE
2  (
3    USERNAME IN VARCHAR2,
4     PASSWORD IN VARCHAR2,
5     RESULT OUT VARCHAR2
6   )
7   AS
8  CNT NUMBER(5);
9  BEGIN
10    SELECT COUNT(*) INTO CNT FROM IRCTC_TAB WHERE UNAME=USERNAME AND PWD=PASSWORD;
11  IF(CNT<>0) THEN
12   RESULT:='VALID CREDENTIAL';
13  ELSE
14   RESULT:='INVALID CREDENTIAL';
15  END IF;
16* END;
*///procedure plsql

public class CsProcedureTest_Auth {
    private static final String CALL_PROCEDURE_QUERY="{CALL P_AUTHENTICATE}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Scanner sc=new Scanner(System.in)) {
//			read inputs
			String username=null,password=null;
			if(sc!=null) {
				System.out.print("Enter user name :: ");
				username=sc.next();

				System.out.print("Enter user password :: ");
				password=sc.next();
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY)){
				
				//register  out  param with jdbc types
				if(cs!=null)
					cs.registerOutParameter(3,Types.VARCHAR );
				
				//set in parameter
				if(cs!=null)
				cs.setString(1,username);
				cs.setString(2,password);
				
				//call PL-SQL procedure
				if(cs!=null)
					cs.execute();
				
				String result=null;
				if(cs!=null) {
					result=cs.getString(3);
					System.out.println(result);
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
