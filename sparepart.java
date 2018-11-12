import java.io.*;
import java.util.*;

class shop 
{
  private
  	static class inventory
  	{
  	private
	  	static int inventoryQuantity[] = {50, 50, 50, 50, 50};
	public
	  	static String inventoryItems[] = {"Oil", "Tires", "Brakes", "Lights", "Battery"};
		
		static void display()
		{
			System.out.println("Items\t\tQuantity");
			for (int i = 0; i < 5; i++) {
				System.out.println((i+1) + ". " + inventoryItems[i] + "\t\t" + inventoryQuantity[i]);
			}
		}  	
		static void quantityIncrement(int i)
		{
			
				inventoryQuantity[i] += 10;
			
		}
		static void quantityDecrement(int decrementArray[])
		{
            for (int i = 0; i < 5; i++) {
                if (inventoryAvailable(decrementArray[i], inventoryQuantity[i]))
					inventoryQuantity[i] -= decrementArray[i];
				else
				{
					quantityIncrement(i);
					inventoryQuantity[i] -= decrementArray[i];
				}
            }
		}
		static boolean inventoryAvailable(int item, int available)
		{
            return item < available;
		}
  	}
  public
	 static Console c=System.console(); 
 	 static void newOrder(String name)
  	 {
		   inventory.display();
		   System.out.println("press 0 to exit!");
		   int ch,no;int count[]=new int[5];
		   boolean custNotSatisfied=true;
		   do{
			
			ch=Integer.parseInt(c.readLine("enter your choice:"));
			switch (ch) {
				case 0:
					custNotSatisfied=false;
					break;
				case 1:
				no=Integer.parseInt(c.readLine("enter quantity:"));
				count[ch-1]+=no;
				break;
				case 2:
				no=Integer.parseInt(c.readLine("enter quantity:"));
				count[ch-1]+=no;
				break;
				case 3:
				no=Integer.parseInt(c.readLine("enter quantity:"));
				count[ch-1]+=no;
				break;
				case 4:
				no=Integer.parseInt(c.readLine("enter quantity:"));
				count[ch-1]+=no;
				break;
				case 5:
				no=Integer.parseInt(c.readLine("enter quantity:"));
				count[ch-1]+=no;
				break;
				default:
				System.out.println("try again!!!!");
					break;
			}

		   }while(custNotSatisfied);
		   try {
			   storeOrderDetails(count, name);
			   
		   } catch (Exception e) {
			   //TODO: handle exception
		   }
		   inventoryUpdate(count);
	
  	 }
  	static void storeOrderDetails(int quantity[],String name)throws IOException
  	{
		FileWriter f=new FileWriter("transaction.txt",true);
		f.write(name+System.getProperty("line.separator"));
		for(int i=0;i<5;i++)
		{
			if(quantity[i] != 0)
			f.write(inventory.inventoryItems[i]+" "+quantity[i]);
		}
		f.write("\n");
		f.close();


	  }
	  static void showOrderDetails(String name)throws IOException
  	{
		BufferedReader r=new BufferedReader(new FileReader("transaction.txt"));
		String i;
		while ((i=r.readLine()) != null)
		{
				
					if(i.equalsIgnoreCase(name))
					{
						System.out.println(i);
						i = r.readLine();
						System.out.println(i);
					}
					
		}
		r.close();
  	}
  	
  	static void inventoryUpdate(int userChoice[])
  	{
		  inventory.quantityDecrement(userChoice);
		
  	}

}

class Customer 
{
	private
        static String name,address, phone;
    public
	
		static Console c = System.console();
		static FileWriter f = null;
		Customer()
		{
			name = "";
			address = "";
            phone = "";
		}
		static void enterDetails()throws IOException
		{
			 name=c.readLine("enter the name:");
			 address=c.readLine("enter the address:");
			 phone=c.readLine("enter the phonenumber:");//exp
			 f=new FileWriter(name+".txt");
			 f.write(name+System.getProperty("line.separator")+address+System.getProperty("line.separator")+phone);
			 f.close();

		}
		static void displayDetails()
		{
			System.out.println("customer registration details are as follows:");
			System.out.println("name:"+name);
			System.out.println("address:"+address);
			System.out.println("phone:"+phone);
		}
		static void custItemDetails()
		{
			
			try {
				shop.showOrderDetails(name);
			} catch (Exception e) {
				//TODO: handle exception
			}
		}
		static String returnName()
		{
			return name;
		}
		

}




class PathFinder
{
	static Console c=System.console();
	public static void main(String args[]) throws Exception
	{
		int choice;
		String name;
		System.out.println("****Welcome to XX Spare Parts shop****");
		
		do
		{
			
			choice=Integer.parseInt(c.readLine("press 0 Registration \npress 1 to continue the menu\npress 2 to exit\n"));
			if(choice==0)
			{
				Customer.enterDetails();
				Customer.displayDetails();
				
			}
			if(choice==1)//exp
			{
					name=Customer.returnName();
					if(name!=null)
					{
						System.out.println("Welcome "+name+" to the shop");
						System.out.println("1.To place a order");
						System.out.println("2.view registration details");
						System.out.println("3.View Items ordered");
						System.out.println("4.logout");
						int key=Integer.parseInt(c.readLine("enter your choice:"));
						switch (key) 
						{
							case 1:
							shop.newOrder(name);
							break;
							case 2:
							Customer.displayDetails();
							break;
							case 3:
							Customer.custItemDetails();
							break;
							case 4:
							System.out.println("Logged out of the console");
							System.exit(0);
							break;
							default:
							System.out.println("try again");
							break;
						}
					}
					else
					{
						System.out.println("please register!!!");

					}
				}

		}while(choice!=2);
		System.out.println("Have a nice day!!!");

	}
}