package system.banking;


import java.util.*;
import java.lang.*;

public class AtmMain {
	public static void main(String[] args) throws Exception {
		int k=1;
		System.out.println("\n\n---------------------- WELCOME TO  GOLE BANK PVT LTD ---------------------------\n\n");
		do {
			System.out.println("--------------OPTIONS-----------------\n");
			System.out.println("1:Goto Bank\n2:Goto ATM\n3:Exit");
			System.out.println("Enter  Your Choice\n");
			Scanner sc=new Scanner(System.in);
			 int choice = sc.nextInt();
			switch (choice) {
			case 1:
					System.out.println("---------------WELCOME TO  BANK----------------");
					System.out.println("Enter Your Choice\n1:Create Your Bank Account\n2:Deposite Amount\n3:Exit\n");
					Scanner sc1=new Scanner(System.in);
					 int choice1 = sc1.nextInt();
					switch (choice1) {
					case 1:
						BankAccount obj1 = new BankAccount();
						obj1.doentry();
						break;
					case 2:
						Deposite obj2 = new Deposite();
						obj2.depositeamount();
						break;
					case 3:

						break;
					}
				break;
			case 2:
					System.out.println("--------------WELCOME TO ATM-----------------");
					System.out.println("MENU\n1:Withdraw\n2:Balanceinqury\n3:BankStatement\n4:ChangePin\n5:Exit\n");
					Scanner sc2=new Scanner(System.in);
					int choice2=sc2.nextInt();
					switch (choice2) {
					case 2:
						BalanceInquiry obj3 = new BalanceInquiry();
						obj3.getstat();
						break;
					case 1:
						Withdrawl obj4 = new Withdrawl();
						obj4.dowithdraw();
						break;
					case 3:
						Bankstatement obj5 = new Bankstatement();
						obj5.getstate();
						break;
					case 4:
						ChangePin obj6 = new ChangePin();
						obj6.dochange();
						break;
					case 5:
						break;
					}
			break;
			case 3:
			
				System.out.println("------THANK YOU FOR USING OUR SERVICE-------");
				System.exit(0);
				k++;
			default:
				System.out.println("!!!!!!!!Invalid Input Try Again!!!!!!!\n\n");
			}
k++;
	
}
		while(k<=100);
}

}