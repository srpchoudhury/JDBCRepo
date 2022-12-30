package com.nt.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Bank {
	
	
	/*public double withdraw(double accNum,double amt) {
		String amt="SELECT BALANCE FROM BANK WHERE ACCNUM = "+accNum;
		String withdrawQuery="update bank set balance=balance-"+amt+" where accnum=" +accNum;
		return amt;
	}*/
         public static void main(String[] args) {
        	 String bankName=null;
             String branchName=null;
        	 String ifsc=null;
        		
        		 String accHName=null;
        		 double balance=0.0;
        		 
        		 Scanner sc=null;
        		 Connection con=null;
        		 Statement st=null;
        		 ResultSet rs1=null;
        		 ResultSet rs2=null;
        	
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","mydb6pm","MYDB6PM");
				if(con!=null) {
					st=con.createStatement();
				}
				sc=new Scanner(System.in);
				
				if(sc!=null) {
					System.out.print("Enter Account Holder Name :: ");
			        try {
						accHName=sc.nextLine();
						accHName="'"+accHName+"'";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}sc.nextLine();
				 }
				
				if(sc!=null) {
					System.out.print("Enter Balance :: ");
					try {
						balance=sc.nextDouble();sc.nextLine();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(sc!=null) {
					System.out.print("Enter Bank Name :: ");
					try {
						bankName=sc.nextLine();
						bankName="'"+bankName+"'";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(sc!=null) {
					System.out.print("Enter Branch Name :: ");
					try {
						branchName=sc.nextLine();
						branchName="'"+branchName+"'";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(sc!=null) {
					System.out.print("Enter IFSC :: ");
					try {
						ifsc=sc.nextLine();
						ifsc="'"+ifsc+"'";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				String query1="INSERT INTO BANK  VALUES (BANK_SEQ.NEXTVAL,"+bankName+","+branchName+","+ifsc+","+accHName+","+balance+")";
				boolean flag=false;
				if(st!=null) {
					flag=true;
					st.executeUpdate(query1);
					
				}
				if(flag==false) {
					System.out.println("CAN NOT INSERT TO TABLE");
				}else {
					System.out.println("SUCESSFULLY INSERTED TO TABLE");
				}
				
				System.out.println();
				System.out.print("Are you want to withdraw money then type true else false :: ");
				boolean flag2=sc.nextBoolean();
				double amt=0.0;
				int accNum=0;
				//String query3=null;
				//String query4=null;
				if(flag2==true) 
					System.out.print("Enter your Account Number :: ");
					 accNum=sc.nextInt();
					 
					System.out.print("Enter amount to be withdrawn :: ");
					 amt=sc.nextDouble();
					String query2= "select balance from bank where accNum = "+accNum;
					
					//query3="select balance from bank where accNum= "+accNum;
				    //query4="update bank set balance=balance-"+amt+" where accnum=" +accNum;
				    
					rs1=st.executeQuery(query2);
					while(rs1.next()) 
						if(rs1.getFloat(1)<amt) {
							String query31="select balance from bank where accNum= "+accNum;
						    
						}else {
							String query41="update bank set balance=balance-"+amt+" where accnum=" +accNum;
						}
					
						
						
						int count=0;
						if(st!=null) {
						  count =st.executeUpdate(query3);
						}
						
						if(count ==0) {
							System.out.println("Withdraw con not be done ");
						}else {
							System.out.println("Successfully withdrawn");
						}
					
					 
			}catch(SQLException se) {
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//close jdbc objs
				try {
					if(rs1!=null)
						rs1.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(rs2!=null)
						rs2.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
				
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
				
				try {
					if(sc!=null)
						sc.close();
				}catch(Exception se) {
					se.printStackTrace();
				}
			}//finally
			
			
		}//main
}//class
