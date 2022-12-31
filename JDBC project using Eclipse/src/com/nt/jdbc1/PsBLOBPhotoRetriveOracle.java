package com.nt.jdbc1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBLOBPhotoRetriveOracle {
	private static final String ARTIST_RETRIVE_QUERY="SELECT AID,ANAME,ADDRS,AIMAGE FROM ACTREES_INFO WHERE AID=?";
          public static void main(String[] args) {
        	  //read inputs
			try(Scanner sc=new Scanner(System.in);) {
				int aid=0;
				if(sc!=null) {
					System.out.print("Enter Artist Aid :: ");
					aid=sc.nextInt();
				}
				
				//create connection ,prepareStatement obj
				
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","MYDB6PM","MYDB6PM");
					PreparedStatement ps=con.prepareStatement(ARTIST_RETRIVE_QUERY);
						) {
					//set query param
					if(ps!=null)
						ps.setInt(1, aid);
					
					//execute query
					try(ResultSet rs=ps.executeQuery();){
						//process the result
						
						if(rs!=null) {
							if(rs.next()) {
							    aid=rs.getInt(1);
								String name=rs.getString(2);
								String addrs=rs.getString(3);
								System.out.println(aid+" "+name+" "+addrs);
								//get input stream pointing to blob column value
								try(InputStream is=rs.getBinaryStream(4);
										//create output stream pointing to destination file
										OutputStream os=new FileOutputStream("retrive_image.jpg");)
								{
									//copy blob column value to destination file
									IOUtils.copy(is,os);
									System.out.println("Blob value is retrived and stored in the file");
									
								}//try 4
								catch(IOException e) {
									e.printStackTrace();
								}
								
							}//if
							else{
								System.out.println("Record not Found");
							}
						}//if
						
					}//try 3
					
				}//try 2
			}
			//try 1
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//main
}//class
