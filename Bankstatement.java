package system.banking;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;



public class Bankstatement{
public static void getstate()  throws Exception
{
	int i=1;
	int j=1;
	Date d2=new Date();
	Scanner sc =new Scanner(System.in);
	String url ="jdbc:mysql://localhost:3306/atm";
	String uname="root";
	String pass ="";
	Class.forName("com.mysql.cj.jdbc.Driver");
	do {
	try {
	System.out.println("Enter Account number\n");
	int an=sc.nextInt();
	Connection con2=DriverManager.getConnection(url,uname,pass);
	Statement st2=con2.createStatement();
	String qry2="select  name, pin from bankinfo where Acno="+an+"";
	ResultSet rs2=st2.executeQuery(qry2);
	rs2.next();
	int pfd=rs2.getInt("pin");
	String nam=rs2.getString("name");
	do {
	System.out.println("Enter pin \n");
	int pin=sc.nextInt();
	if(pin==pfd)
	{
	Connection con=DriverManager.getConnection(url,uname,pass);
	Statement st=con.createStatement();
	String qry="SELECT deposite,Acno,Date FROM deposite where Acno= "+an+"";
	ResultSet rs=st.executeQuery(qry);
	//get balance
	Connection con11=DriverManager.getConnection(url,uname,pass);
	Statement st11=con11.createStatement();
	Scanner sc11 =new Scanner(System.in);
	String qry11="select available from available where Acno="+an+"";
	ResultSet rs11=st11.executeQuery(qry11);
	rs11.next();
	int tavo=rs11.getInt("available");
	System.out.println("--*-----*--------*-------GOLE BANK PVT LTD-----*-------*------*\n\n");
	System.out.println("                          BANK STATEMENT                                               ");
	System.out.println("                                                            Date: "+d2.toString());
	System.out.println("A/c Number      :"+an);
	System.out.println("A/c Holder Name :"+nam);
	System.out.println("Currency        :NPR");
	System.out.println("Available Amount:"+tavo+"\n");
	System.out.println("Transaction Type        Amount      Acno       Date                  \n");
	while( rs.next())
	{
	 int pn=rs.getInt("deposite");
	 int a=rs.getInt("Acno");
	 Timestamp d=rs.getTimestamp("Date");
	System.out.println("deposite                "+pn+"        "+a+"       "+d+"    ");
	
}
	
	String qry1="SELECT Acno, withdraw,Date FROM withdraw where Acno="+an+"";
	Connection con1=DriverManager.getConnection(url,uname,pass);
	Statement st1=con1.createStatement();
	ResultSet rs1=st1.executeQuery(qry1);
	while( rs1.next())
	{
	 int pn1=rs1.getInt("withdraw");
	 int a1=rs1.getInt("Acno");
Timestamp d1=rs1.getTimestamp("Date");
	System.out.println("Withdraw                "+pn1+"        "+a1+"       "+d1+"      ");
}
	System.out.println("\n");
	i=3;
	}
	else
	{
		System.out.println("!!!!!Pin Is Incorrect Try Again!!!!!\n");
		 i++;
         if(i==3)
          System.out.println("YOu have tried max time return to OPTION ");
	}
	 }while(i<3);
j=3;
	}
	
	catch(Exception e)
	{
		System.out.println("!!!!!!!Account number does not exist try again!!!!!!!\n");
		 j++;
         if(j==3)
          System.out.println("YOu have tried max time return to OPTION ");
	}

}while(j<3);
}
}