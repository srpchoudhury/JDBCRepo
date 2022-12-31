package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertBlobTWRTestMySQL {
	private static final String INSERT_ACTREES_INFO="INSERT INTO ACTREES_INFO  (NAME,ADDRS,PHOTO) VALUES (?,?,?)";
    public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);) {
			String aname=null,addrs=null,locPath=null;
			if(sc!=null)
				System.out.print("Enter Actrees Name :: ");
			     aname=sc.next();
			    System.out.print("Enter Actrees Address :: ");
			     addrs=sc.next();
			    System.out.print("Enter Image path :: ");
		         locPath=sc.next();
				try(InputStream is=new FileInputStream(locPath);){
						
				    try(Connection con=DriverManager.getConnection("jdbc:mysql:///mydb6pm","root","ROOT");
							PreparedStatement ps=con.prepareStatement(INSERT_ACTREES_INFO);) {
				    	if(ps!=null) {
				    	ps.setString(1,aname);
				    	ps.setString(2,addrs);
				    	ps.setBlob(3, is);
				    	}
				    	int count=0;
				    	if(ps!=null)
				    			count=ps.executeUpdate();
				    	
				    	if(count==0) {
				    		System.out.println("Data can not be Inserted");
				    	}else {
				    		System.out.println("Data Sucessfully Inserted");
				    	}
				    	
				    	
				    }//try
				}//try
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in Record Insertion");
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
}
