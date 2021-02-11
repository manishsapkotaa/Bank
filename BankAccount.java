package system.banking;

import java.sql.Connection;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

public class BankAccount {
	public void doentry() throws Exception
	{
		String url ="jdbc:mysql://localhost:3306/atm";
		String uname="root";
		String pass ="";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		Statement st=con.createStatement();
		Scanner sc=new Scanner(System.in);
		Random rand=new Random();
		int Acno=rand.nextInt(1000);
		System.out.println("Your name:\n");
		String name =sc.next();
		System.out.println("Address:\n");
		String address =sc.next(); 
		System.out.println("Create pin:\n");
		int pin =sc.nextInt();
		System.out.println("phone no.:\n");
		long phone =sc.nextLong();
		System.out.println("Email Id:\n");
		String Email_ID=sc.next();
		
		String sql="Insert into bankinfo(Acno,name,address,pin,phone,Email_ID)values("+Acno+",'"+name+"','"+address+"'," +pin+","+phone+",'"+Email_ID+" ')";
		st.executeUpdate(sql);
		System.out.println("------ACCOUNT INFORMATION------\n");
		System.out.println("AC NO.:\n"+Acno);
		System.out.println("NAME:\n"+name);
		System.out.println("ADDRESS:\n"+address);
		System.out.println("PHONE_NO:\n"+phone);
		System.out.println("Email_ID:\n"+Email_ID);
		
		
		

		
		
	}

}