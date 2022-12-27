package com.nt.basics;

import java.text.SimpleDateFormat;

public class DateValuesConversion {
        public static void main(String[] args)throws Exception {
			//converting string date value to java.util.Date class obj
		String s1="41-21-1990";//dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(s1);
		System.out.println("String date value :: "+s1);
		System.out.println("util Date :: "+ud1);
		
		//converting java.util.Date class obj to java.sql.Date class obj
		long ms=ud1.getTime();//gives no of miliseconds that are elapsed b/w 
		//ud1 date and time and 1970 jan 1st mid night 00:00 hrs(Epoach standard)
		System.out.println("ms :: "+ms);
		java.sql.Date sd1=new java.sql.Date(ms);
		System.out.println("util date :: "+ud1);
		System.out.println("sql date :: "+sd1);
		
		//if string date value pattern is yyyy-MM-dd patterm then it can be converted directly to java.sql.date 
		//class obj with out converting java.util.Date class obj
		System.out.println();
		String s2="1991-12-25";//yyyy-MM-dd
		java.sql.Date sqd2=java.sql.Date.valueOf(s2);
		System.out.println("String date value ::"+s2);
		System.out.println("sql date value :: "+sqd2);
		
		//converting java.sql.Date class obj to java.util.Date class obj
		//here we can use java.util.Date class reference to refer java.sql.Date class obj(java.util.Date is super class for java.sql.Date)
		java.util.Date ud2=sqd2;
		System.out.println("sql date 2:: "+sqd2);
		System.out.println("util date 2 :: "+ud2);
		
		
		java.util.Date ud3=new java.util.Date(sqd2.getTime());
		System.out.println("sql date 2:: "+sqd2);
		System.out.println("util date 2 :: "+ud3);
        
        //converting java.sql.Date class obj/java.util.Date class obj to String date value
		SimpleDateFormat sdf2=new SimpleDateFormat("dd-MMM-yyyy");
		String s3=sdf2.format(ud3);
		String s4=sdf2.format(sqd2);
		System.out.println("util date :: "+ud3);
		System.out.println("String date :: "+s3);
		System.out.println("String date :: "+s4);
        }
}
