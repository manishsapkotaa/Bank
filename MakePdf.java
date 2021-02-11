package system.banking;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class MakePdf {
	public static void main(String []args) throws Exception
	{
		Date d2=new Date();
		Scanner sc =new Scanner(System.in);
		String url ="jdbc:mysql://localhost:3306/atm";
		String uname="root";
		String pass ="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		System.out.println("Enter Account number\n");
		int an=sc.nextInt();
		
		
		Connection con2=DriverManager.getConnection(url,uname,pass);
		Statement st2=con2.createStatement();
		String qry2="select  name, pin from bankinfo where Acno="+an+"";
		ResultSet rs2=st2.executeQuery(qry2);
		rs2.next();
		int pfd=rs2.getInt("pin");
		String nam=rs2.getString("name");
		
		System.out.println("Enter pin \n");
		int pin=sc.nextInt();
		if(pin==pfd)
		{
		Connection con=DriverManager.getConnection(url,uname,pass);
		Statement st=con.createStatement();
		
		String qry="SELECT deposite,Acno,Date FROM deposite where Acno= "+an+"";
		ResultSet rs=st.executeQuery(qry);
		///get total avo
		Connection con11=DriverManager.getConnection(url,uname,pass);

		Statement st11=con11.createStatement();
		Scanner sc11 =new Scanner(System.in);
		String qry11="select available from available where Acno="+an+"";
		ResultSet rs11=st11.executeQuery(qry11);
		rs11.next();
		int tavo=rs11.getInt("available");
		Document doc=new Document();
		PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/sandip/Bankstatement3.pdf "));
		doc.open();
		doc.add(new Paragraph("                                                   GOLE BANK PVT LTD                    \n\n"));
		doc.add(new Paragraph("                                   BANK STATEMENT                                               "));
		doc.add(new Paragraph("                                                                                   Date: "+d2.toString()));
		doc.add(new Paragraph("      A/c Number:"+an));
		doc.add(new Paragraph("      A/c Holder Name :"+nam));
		doc.add(new Paragraph("      Currency:NPR"));
		doc.add(new Paragraph("      Available Amount:"+tavo+"\n\n"        ));
		doc.add(new Paragraph("      Transaction Type                     Amount            Acno             Date\n"));
		while( rs.next())
		{
		 int pn=rs.getInt("deposite");
		 int a=rs.getInt("Acno");
		 Timestamp d=rs.getTimestamp("Date");
		 doc.add(new Paragraph("       deposite                                   "+pn+"               "+a+"                 "+d));
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
	doc.add(new Paragraph("       Withdraw                                 "+pn1+"                "+a1+"                 "+d1));
		}
	doc.add(new Paragraph("\n\n"));
	doc.add(new Paragraph("                                           THANK YOU FOR USING OUR SERVICE   "));
		
		doc.close();
	System.out.println("BankStatemet is Ready");
		}
		else
		{
			System.out.println("pin is incorrect try again");
		}

		}	
	}

