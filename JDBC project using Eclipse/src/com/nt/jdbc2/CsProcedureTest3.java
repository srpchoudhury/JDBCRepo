package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest3 {
	private static final String STUDENT_QUERY="{CALL PS_STUDENT(?,?,?,?)}";
        public static void main(String[] args) {
        	//READ INPUTS
        	int no=0;
			try(Scanner sc=new Scanner(System.in)) {
				if(sc!=null) {
					System.out.println("Enter student no :: ");
					no=sc.nextInt();
				}
				//establish the connection
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","tiger");
						CallableStatement cs=con.prepareCall(STUDENT_QUERY)){
					
					//register output param with jdbc datatype
					if(cs!=null)
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
					
					//set values to in param
					if(cs!=null)
					cs.setInt(1, no);
					
					//execute or call plsql procedure
					if(cs!=null)
					cs.execute();
					
					//gather results from out param
					if(cs!=null) {
						String name=cs.getString(2);
						String addrs=cs.getString(3);
					    float avgs=cs.getFloat(4);
						System.out.println(name+ " "+addrs+" "+avgs);
					}
					
				}//try 2
			}//try 1
			catch(SQLException se) {
				System.out.println("No dat found");
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}
