package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {
    public static void main(String[] args) {
    	Connection con=null;
    	Statement st=null;
		try {
			//load the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
		
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
				
				if(con!=null)
					st=con.createStatement();
				
				//prepRE sql query
				//create table temp_student(sno number(5) primary key,sname varchar2(15));
				String query="CREATE TABLE TEMP_STUDENT(SNO number(5) PRIMARY KEY,SNAME VARCHAR2(15))";
				System.out.println(query);
				
				//send and execute sql query in db s/w
				int count=0;
				if(st!=null) 
					count=st.executeUpdate(query);
				System.out.println("count :: "+count);
					
					//process the result
					if( count ==0)
						System.out.println("Db table is  created");
					else
						System.out.println("Db table is not created");
				
				}catch(SQLException se) {
					if(se.getErrorCode()==955)
						System.out.println("DB table is already created");
						se.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					
					
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
