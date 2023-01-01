package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class StandAloneMatrimonyAppMySQL {
	private static final String INSERT_DETAILS_TO_MATRYMONY_INDIA="INSERT INTO MATRYMONY_INDIA (CNAME,G,DOB,PHOTO,RESUME,DOJ_FIRSTJOB,BIODATA,AUDIO_INFO,VIDEO_INFO)VALUES (?,?,?,?,?,?,?,?,?)";
      public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);) {
			String name=null,dob=null,photoLoc=null,resumeLoc=null,doj_FirstJob=null,biodataLoc=null,audioLoc=null,videoLoc=null;
			String gender=null;
			java.sql.Date sd1=null;
			java.sql.Date sd2=null;
			
			if(sc!=null) {
				System.out.print("Enter CName :: ");
				 name=sc.next();
				 
				System.out.print("Enter Gender :: ");
				 gender=sc.next();sc.nextLine();
				 
				System.out.print("Enter DOB :: ");
				 dob=sc.nextLine();
				 
				 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
				 java.util.Date ud1=sdf1.parse(dob);
				 long ms1=ud1.getTime();
				 sd1=new java.sql.Date(ms1);
				 
				System.out.print("Enter Customer Photo Path :: ");
				 photoLoc=sc.next().replace("?", "");
				 
				System.out.print("Enter Resume path :: ");
				 resumeLoc=sc.next().replace("?", "");sc.nextLine();
				 
				System.out.print("Enter doj of First job :: ");
				 doj_FirstJob=sc.nextLine();
				 SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
				 java.util.Date ud2=sdf2.parse(doj_FirstJob);
				 long ms2=ud2.getTime();
				 sd2=new java.sql.Date(ms2);
				 
				System.out.print("Enter BIO_DATA txt file path :: ");
				 biodataLoc=sc.next().replace("?", "");
				System.out.print("Enter audio info path :: ");
				 audioLoc=sc.next().replace("?", "");
				System.out.print("Enter video info path :: ");
				 videoLoc=sc.next().replace("?", "");
			}
			try(InputStream is1=new FileInputStream(photoLoc);
					InputStream is2=new FileInputStream(resumeLoc);
					Reader reader =new FileReader(biodataLoc);
					InputStream is3=new FileInputStream(audioLoc);
					InputStream is4=new FileInputStream(videoLoc)
					) {
				
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///mydb6pm","root","ROOT");
						
						PreparedStatement ps=con.prepareStatement(INSERT_DETAILS_TO_MATRYMONY_INDIA)
						) {
					
					//set values to query param
					if(ps!=null) {
						ps.setString(1, name);
					ps.setString(2, gender);
					ps.setDate(3, sd1);
					ps.setBinaryStream(4, is1);
					ps.setBinaryStream(5,is2);
					ps.setDate(6, sd2);
					ps.setCharacterStream(7,reader);
					ps.setBinaryStream(8,is3);
					ps.setBinaryStream(9, is4);
					}
					//execute the query
					int count=0;
					if(ps!=null)
						count=ps.executeUpdate();
					
					//process the result
					if(count == 0)
						System.out.println("Recod Not Inserted");
					else
						System.out.println("Record Sucessfully Inserted");
				}//try 3
				
			}//try 2
		}//try 1
		catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
