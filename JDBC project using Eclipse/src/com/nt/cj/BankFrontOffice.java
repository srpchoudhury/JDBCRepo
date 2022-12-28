package com.nt.cj;

//BankFrontOffice.java
import java.util.Scanner;
public class BankFrontOffice{
	public static void main(String[] args){

		Bank hdfcBank=new Bank();
		Scanner scn = new Scanner(System.in);

		loop:while(true){
			System.out.println("\nChoose one option");
			System.out.println(" 1.OpenAccount");
			System.out.println(" 2.Account details");
			System.out.println(" 3.Deposite");
			System.out.println(" 4.Withdraw");
			System.out.println(" 5.Balance Enquiry");
			System.out.println(" 6.Transfer Money");
			System.out.println(" 7.Display All accounts");
			System.out.println(" 8.Exit");

          System.out.print("Enter option:");
			int option=scn.nextInt();scn.nextLine();

			try{
				switch(option){
					case 1:{//Open Account
						hdfcBank.openAccount();
						break;
					}
					case 2:{//Account Details
						System.out.print("Enter Account number:");
						long accNum=scn.nextLong();scn.nextLine();
                      hdfcBank.displayAccount(accNum);
						break;
					}
					case 3:{//Deposite
					     System.out.print("Enter Account Number:");
						 long accNum=scn.nextLong();scn.nextLine();

						 System.out.print("Enter deposite amount:");
						 double amt=scn.nextDouble();scn.nextLine();

						 hdfcBank.deposite(accNum,amt);
						 break;
					}
					case 4:{//WIthdraw
					     System.out.print("Enter Account Number:");
						 long accNum=scn.nextLong();scn.nextLine();

						 System.out.print("Enter Withdraw amount:");
						 double amt=scn.nextDouble();scn.nextLine();

						 hdfcBank.withdraw(accNum,amt);

						 break;
					}
					case 5:{//Balance Enquiry
					     System.out.print("Enter Accoun Number:");
						 long accNum=scn.nextLong();scn.nextLine();

						 hdfcBank.balanceEnquiry(accNum);

						 break;
					}
					case 6:{//Transfer money
					     System.out.print("Enter source Account Number:");
						 long srcAccNum=scn.nextLong();scn.nextLine();

                       System.out.print("Enter destination Account Number:");
						 long destAccNum=scn.nextLong();scn.nextLine();

		                 System.out.print("Enter transfer Ammount:");
						 double amt=scn.nextDouble();scn.nextLine();

						 hdfcBank.transferMoney(srcAccNum,destAccNum,amt);

						 break;
                  }
					case 7:{//Display all accounts
					    System.out.println(hdfcBank);
						break;
					}
					case 8://Exit
					    System.out.println("_V/_V/_V/_Thank You,Please Visit again_V/_V/_V/_");
						break loop;

						default:
							System.out.println("Invalid option");

					}//switch end
				}catch(IllegalArgumentException e){
					System.out.println("Error:"+e.getMessage());
				}
		}//while(true) end
	}//main closeee
}//class close

