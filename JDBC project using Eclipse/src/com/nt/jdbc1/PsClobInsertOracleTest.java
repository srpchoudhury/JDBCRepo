package com.nt.jdbc1;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsClobInsertOracleTest {
	public static final String INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER VALUES (JSID_SEQ.NEXTVAL,?,?,?)";
      public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			//read inputs
			String name=null,address=null,resumeLoc=null;
			if(sc!=null) {
				System.out.println("Enter jobseeker name :: ");
				name=sc.next();
				System.out.println("Enter jobseeker address :: ");
				address=sc.next();
				System.out.println("Enter jobseeker resume location :: ");
				resumeLoc=sc.next().replace("?", "");
			}
			//create inout steram pointiong to photo file
			try(Reader reader=new FileReader(resumeLoc)){
			//establish the connection
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","mydb6pm","MYDB6PM");
					PreparedStatement ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY)
					){
				//SET VA;UES TO WUERY PARAM
				if(ps!=null)
				ps.setString(1,name);
				ps.setString(2,address);
				ps.setCharacterStream(3,reader);
				
				//execyte q
				int count=0;
				if(ps!=null)
					count=ps.executeUpdate();
				
				if(count==0) {
					System.out.println("Record Not inserted");
				}else
					System.out.println("Record  inserted");
				
				
				
			}//try 3
			}//try2
		}//try1
		catch(SQLException se) {
			System.out.println("problem in record innsertion");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
