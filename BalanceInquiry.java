package system.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BalanceInquiry {
	public static void getstat() throws Exception
	{
		int i=1;
		int j=1;
		System.out.println("---------------------------Balance Inquiry---------------------\n");
		String url ="jdbc:mysql://localhost:3306/atm";
		String uname="root";
		String pass ="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Enter Account number
		do {
		try {
		System.out.println("Enter Account number\n");
		Scanner sc4=new Scanner(System.in);
		int ac=sc4.nextInt();
		System.out.println("Your Account number:\t"+ac);
		
		//get A/c pin from database
		Connection con0=DriverManager.getConnection(url,uname,pass);
		Statement st0=con0.createStatement();
		Scanner sc0 =new Scanner(System.in);
		String qry0="select pin from bankinfo where Acno= "+ac+"  ";
		ResultSet rs0=st0.executeQuery(qry0);
		rs0.next();
		int pn=rs0.getInt("pin");
	
		//take pin input from user
		do {
		System.out.println("Input Your Pin\n");
		Scanner sc5=new Scanner(System.in);
		int uinp=sc5.nextInt();
		if(uinp==pn) {
			//get total deposite
			 Connection con55=DriverManager.getConnection(url,uname,pass);
				Statement st55=con55.createStatement();
				Scanner sc55 =new Scanner(System.in);
				String qry55="select deposite from deposite where Acno="+ac+"  ";
				ResultSet rs55=st55.executeQuery(qry55);
				int tdepo = 0;
				while(rs55.next())
				{
					int depo=rs55.getInt("deposite");
				tdepo+=depo;
				}
				System.out.println("Total Deposite:"+tdepo);
                //get total withdraw
				Connection con111=DriverManager.getConnection(url,uname,pass);

				Statement st111=con111.createStatement();
				Scanner sc111 =new Scanner(System.in);
				String qry111="select withdraw from withdraw where Acno="+ac+"    ";
				ResultSet rs111=st111.executeQuery(qry111);
				int twithd=0;
				while(rs111.next())
				{
					int with=rs111.getInt("withdraw");
				twithd+=with;
				}
				
				System.out.println("Total Withdraw:"+twithd);
				int avo=tdepo-twithd;
				System.out.println("Total Available:"+avo);
				//inserting to database available amount
				try {
  					Connection con6=DriverManager.getConnection(url,uname,pass);

  					Statement st6=con6.createStatement();
  					Scanner sc6 =new Scanner(System.in);
  					String qry6="insert into  available (available,Acno)values("+avo+","+ac+"   )";
  					st6.executeUpdate(qry6);
  					}
  					catch(Exception e)
  					
  					{//update
  						Connection con3=DriverManager.getConnection(url,uname,pass);

  						Statement st3=con3.createStatement();
  						Scanner sc3 =new Scanner(System.in);
  						String qry3="update  available set available ="+avo+",Acno="+ac+" where Acno="+ac+" ";  
  						st3.executeUpdate(qry3);
  						
  					}
		
		i=3;
		
		}
		else
		{
			System.out.println("Pin Is Incorrect Try Again\n");
			 i++;
             if(i==3)
              System.out.println("YOu have tried max time return to menu ");
		}
		 }while(i<3);
		j=3;
		}
		catch(Exception e)
		{
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!Account number doesnot exist Please Try Again!!!!!!!!!!!!!!!!\n");
			 j++;
             if(j==3)
              System.out.println("YOu have tried max time return to OPTION ");
		}
	 }while(j<3);
	}

}