package system.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class ChangePin {
public static void dochange() throws Exception {
	int i=1;
	int j=1;
	String url ="jdbc:mysql://localhost:3306/atm";
	String uname="root";
	String pass ="";
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,pass);
	Statement st=con.createStatement();
	Scanner sc=new Scanner(System.in);
	do {
	try {
	System.out.println("Enter Your Account no.\n");
	int Acno=sc.nextInt();
	
	String sql1="select pin  from bankinfo where Acno="+Acno+" ";
	ResultSet rs=st.executeQuery(sql1);
	rs.next();
	String pinn=rs.getString("pin");
	do {

	System.out.println("Type Your Current Pin\n");
	int pin_no =sc.nextInt();
	
	if(pin_no==rs.getInt("pin"))
		
	{
	System.out.println("Enter New Pin \n");
	int pin_no1 =sc.nextInt();
	String sql="update bankinfo set pin="+pin_no1+" where Acno="+Acno+ "";
	st.executeUpdate(sql);
	System.out.println("Pin Successfully Changed!!!\n");
	i=3;
	}
	else
		System.out.println("Current pin is incorrect try again\n");
	i++;
	if(i==3)
		System.out.println("YOu have tried max time return to OPTION ");
}
while(i<4);
	j=3;
	}
	catch(Exception e)
	{
		System.out.println("!!!!!Account number does not exist please Try Again!!!!\n");
		 j++;
         if(j==3)
          System.out.println("YOu have tried max time return to OPTION ");
	}
	
	 }while(j<3);
	
	
}
}