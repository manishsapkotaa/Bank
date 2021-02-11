package system.banking;

import java.util.Scanner;
import java.sql.*;

public class Deposite {
	
	public void depositeamount() throws Exception
	{
		int i=1;
		String url ="jdbc:mysql://localhost:3306/atm";
		String uname="root";
		String pass ="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=DriverManager.getConnection(url,uname,pass);
			Statement st=con.createStatement();
			Scanner sc=new Scanner(System.in);
		     System.out.println("Enter the Account no \n");
	     	 String Acno =sc.next();
		     String qry="select pin from bankinfo where Acno="+Acno+"";
		     ResultSet rs=st.executeQuery(qry);
		     rs.next();
		    int pn=rs.getInt("pin");
		        do { 
		                  System.out.println("Enter pin\n");
		                  Scanner sc1=new Scanner(System.in);
		                  int pnn=sc1.nextInt();
		                  if(pnn==pn)
	                          	{
	                                 System.out.println("Enter amount \n");
	       		                  Scanner sc2=new Scanner(System.in);

	                                  int deposite =sc2.nextInt();
	                                  Connection con2=DriverManager.getConnection(url,uname,pass);
	                      			Statement st2=con2.createStatement();
	                                   String query2= "insert into deposite (deposite,Acno,Date)"
				                        + "values("+deposite+","+Acno+",NOW() )";
		                                  st2.executeUpdate(query2);
		                                  //get total deposite
		                  		        Connection con5=DriverManager.getConnection(url,uname,pass);
		                  				Statement st5=con5.createStatement();
		                  				Scanner sc5 =new Scanner(System.in);
		                  				String qry5="select deposite from deposite where Acno="+Acno+"  ";
		                  				ResultSet rs5=st5.executeQuery(qry5);
		                  				int tdepo = 0;
		                  				while(rs5.next())
		                  				{
		                  					int depo=rs5.getInt("deposite");
		                  				tdepo+=depo;
		                  				}
		                  				System.out.println("Total Deposite:\t"+tdepo);
		                  				try {
		                  					Connection con6=DriverManager.getConnection(url,uname,pass);

		                  					Statement st6=con6.createStatement();
		                  					Scanner sc6 =new Scanner(System.in);
		                  					String qry6="insert into  totaldepo (tdeposite,Acno)values("+tdepo+","+Acno+"   )";
		                  					st6.executeUpdate(qry6);
		                  					}
		                  					catch(Exception e)
		                  					
		                  					{//update
		                  						Connection con3=DriverManager.getConnection(url,uname,pass);

		                  						Statement st3=con3.createStatement();
		                  						Scanner sc3 =new Scanner(System.in);
		                  						String qry3="update  totaldepo set tdeposite ="+tdepo+",Acno="+Acno+" where Acno="+Acno+" ";  
		                  						st3.executeUpdate(qry3);
		                  						System.out.println("Data updated\n");
		                  					}
		                  				//get total withdraw detail
		                  				Connection con111=DriverManager.getConnection(url,uname,pass);

		                				Statement st111=con111.createStatement();
		                				Scanner sc111 =new Scanner(System.in);
		                				String qry111="select withdraw from withdraw where Acno="+Acno+"    ";
		                				ResultSet rs111=st111.executeQuery(qry111);
		                				int twithd=0;
		                				while(rs111.next())
		                				{
		                					int with=rs111.getInt("withdraw");
		                				twithd+=with;
		                				}
		                				
		                  				
		                				int avo=tdepo-twithd;
		                				
		                				//inserting to database available amount
		                				try {
		                  					Connection con6=DriverManager.getConnection(url,uname,pass);

		                  					Statement st6=con6.createStatement();
		                  					Scanner sc6 =new Scanner(System.in);
		                  					String qry6="insert into  available (available,Acno)values("+avo+","+Acno+"   )";
		                  					st6.executeUpdate(qry6);
		                  					}
		                  					catch(Exception e)
		                  					
		                  					{//update
		                  						Connection con3=DriverManager.getConnection(url,uname,pass);

		                  						Statement st3=con3.createStatement();
		                  						Scanner sc3 =new Scanner(System.in);
		                  						String qry3="update  available set available ="+avo+",Acno="+Acno+" where Acno="+Acno+" ";  
		                  						st3.executeUpdate(qry3);
		                  						
		                  					}
		                  			
		                                  i=3;  
		                            }
		                 
	                      else 
		                     {
			                  System.out.println("pin is incorrect try again\n");
			                  i++;
			                  if(i==3)
				               System.out.println("YOu have tried max time return to OPTION ");
		                      }
	     
		        }while(i<3);
		  
		        
		}
		catch(Exception e)
		{
			System.out.println("!!!!!Account number does Not Exist Try Again!!!!!\n");
			
			
		}
}
}