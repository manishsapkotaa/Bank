package system.banking;

import java.util.Scanner;
import java.sql.*;


public class Withdrawl {
	public void dowithdraw() throws Exception {
		int i = 1;
		
		String url ="jdbc:mysql://localhost:3306/atm";
		String uname="root";
		String pass ="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
			try {
		Connection con1=DriverManager.getConnection(url,uname,pass);
		Statement st1=con1.createStatement();
		System.out.println("Enter Account no.\n");
		Scanner sc22=new Scanner(System.in);
		int acn =sc22.nextInt();
		 String qry1="select pin from bankinfo where Acno="+acn+"";
		 ResultSet rs1=st1.executeQuery(qry1);
		 rs1.next();
		 int pn=rs1.getInt("pin");
		 //get available amount
		 Connection con11=DriverManager.getConnection(url,uname,pass);
			Statement st11=con11.createStatement();
		String qry11="select  available from available where Acno="+acn+"";
		 ResultSet rs11=st11.executeQuery(qry11);
		 rs11.next();
		 int tavo11=rs11.getInt("available");
		 do {
				System.out.println("Enter pin\n");
				Scanner scc=new Scanner(System.in);
				int pnn=scc.nextInt();
				if(pnn==pn)
				{
		System.out.println("Enter Withdraw Amount\n");
		Scanner scc1=new Scanner(System.in);
		 int  withdrawl  =scc1.nextInt();
		if(withdrawl<=tavo11)
		{
			Connection con2=DriverManager.getConnection(url,uname,pass);
		
		Statement st2=con2.createStatement();
		String query2= "insert into withdraw (Acno,withdraw,Date)"
				+ "values("+acn+","+withdrawl+",NOW() )";
		
		st2.executeUpdate(query2);
		System.out.println("Current Withdraw Amount\n");
		System.out.println(withdrawl);
		//withdraw
		
				Connection con111=DriverManager.getConnection(url,uname,pass);

				Statement st111=con111.createStatement();
				Scanner sc111 =new Scanner(System.in);
				String qry111="select withdraw from withdraw where Acno="+acn+"    ";
				ResultSet rs111=st111.executeQuery(qry111);
				int twithd=0;
				while(rs111.next())
				{
					int with=rs111.getInt("withdraw");
				twithd+=with;
				}
				
				System.out.println("Total Withdraw:\t"+twithd+"\n");
				try {
  					Connection con6=DriverManager.getConnection(url,uname,pass);

  					Statement st6=con6.createStatement();
  					Scanner sc6 =new Scanner(System.in);
  					String qry6="insert into  totalwithd (twithdraw,Acno)values("+twithd+","+acn+"   )";
  					st6.executeUpdate(qry6);
  					}
  					catch(Exception e)
  					
  					{//update
  						Connection con3=DriverManager.getConnection(url,uname,pass);

  						Statement st3=con3.createStatement();
  						Scanner sc3 =new Scanner(System.in);
  						String qry3="update  totalwithd set twithdraw ="+twithd+",Acno="+acn+" where Acno="+acn+" ";  
  						st3.executeUpdate(qry3);
  						System.out.println("Data updated\n");
  					}
				//get total deposite
				 Connection con5=DriverManager.getConnection(url,uname,pass);
   				Statement st5=con5.createStatement();
   				Scanner sc5 =new Scanner(System.in);
   				String qry5="select deposite from deposite where Acno="+acn+"  ";
   				ResultSet rs5=st5.executeQuery(qry5);
   				int tdepo = 0;
   				while(rs5.next())
   				{
   					int depo=rs5.getInt("deposite");
   				tdepo+=depo;
   				}
   				int avo=tdepo-twithd;
				//inserting to database available amount
				try {
  					Connection con6=DriverManager.getConnection(url,uname,pass);

  					Statement st6=con6.createStatement();
  					Scanner sc6 =new Scanner(System.in);
  					String qry6="insert into  available (available,Acno)values("+avo+","+acn+"   )";
  					st6.executeUpdate(qry6);
  					}
  					catch(Exception e)
  					
  					{//update
  						Connection con3=DriverManager.getConnection(url,uname,pass);

  						Statement st3=con3.createStatement();
  						Scanner sc3 =new Scanner(System.in);
  						String qry3="update  available set available ="+avo+",Acno="+acn+" where Acno="+acn+" ";  
  						st3.executeUpdate(qry3);
  						
  					}
				
		}
	
		else
		{
		System.out.println("Insufficient Balance Try Again\n");
		}
		i=3;
				}
				else 
				{
					System.out.println("Pin Is Incorrect Try Again\n");
					i++;
					if(i==3)
						System.out.println("YOu have tried max time return to OPTION ");
				}
			}
		 while(i<3);
		
		
		}
		catch(Exception e)
		{
			System.out.println("!!!Account number does not exist Try again !!!!\n");
			
		}
		
		
	}

}
