package com.nt.jdbc1;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsClobRetriveOracleTest {
	public static final String SELECT_JOBSEEKER_QUERY="SELECT JSID,JNAME,JADDRESS,RESUME FROM JOBSEEKER WHERE JSID=?";
      public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			int jsid=0;
			//read inputs
			if(sc!=null) {
			System.out.println("Enter jsid ::  ");
			jsid=sc.nextInt();
			}
				
			
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","mydb6pm","MYDB6PM");
					PreparedStatement ps=con.prepareStatement(SELECT_JOBSEEKER_QUERY)
					){
				//SET VA;UES TO WUERY PARAM
				if(ps!=null)
			ps.setInt(1, jsid);
				try(ResultSet rs=ps.executeQuery();){
				  //process the result
					if(rs.next()) {
						jsid=rs.getInt(1);
						String name=rs.getString(2);
						String addrs=rs.getString(3);
						System.out.println(jsid+" "+name+" "+addrs);
						//get reader stream pointing to clob file
						try(Reader reader=rs.getCharacterStream(4);
								Writer writer =new FileWriter("Retrive_resume.txt")){
							IOUtils.copy(reader, writer);
							System.out.println("Clob values are retrived and stored in the files");
						}//try 4
					}//if
					else {
						System.out.println("Record not found");
					}
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
