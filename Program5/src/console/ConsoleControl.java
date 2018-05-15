/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import main.BankAccountManagement;
import main.SSNum;
import java.io.File;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import main.Account;
import main.Customer;

/**
 *
 * @author scholzm
 */
public class ConsoleControl {
   private Scanner stdin;
   BankAccountManagement bankdata;
    
   public ConsoleControl(BankAccountManagement data)
   {
      this.bankdata = data;
      stdin = new Scanner (System.in);
   }
    
    /**
   replaces keyboard input scanner with file input for testing
   */
   private void ScannerInputByFile(String filename)
   {
      try
      {
         //String filename = "test_input4.txt";
         File f = new File(filename);
         stdin = new Scanner(f);
         //System.out.println(String.format("opening %s as input file", filename));
      }
      catch(java.io.FileNotFoundException e)
      {
         // dont overwrite manual if file not found
         return;
      }
      catch (Exception ex)
      {
         System.out.println(String.format(
               "Working Directory = %s", System.getProperty("user.dir")));
         System.out.println(ex.toString());
         ex.printStackTrace();
         return;
      }
   }
   
   private int getAccountNuber(Scanner stdin)
   {
      String accountNum = (stdin.nextLine());
      return Integer.parseInt(accountNum);
   }
   
   private void createaccount(Scanner stdin)
   {
      String name;
      SSNum ssn;
      main.Account.AccountType type = null;
      int accountNum;
      
      try
      {
         System.out.println("Please enter the name of the customer:");
         name = stdin.nextLine();

         System.out.println("Please enter the SSN of the customer (***-**-****):");
         ssn = new SSNum(stdin.nextLine());
         
         System.out.println("Please select account type: 1.Savings, 2.Checking");
         int typei = Integer.parseInt(stdin.nextLine());
         if (typei != 1 && typei != 2){throw new IllegalArgumentException();}
         //type = main.Account.AccountType.byInput(typei); // convert int into enum
         if (typei == 1 )
         {
            type = main.Account.AccountType.SAVINGS;
         }
         else if (typei == 2 )
         {
            type = main.Account.AccountType.CHECKING;
         }
         else 
         {
            // can't find symbol?
            //throw java.util.InputMismatchException();
         }
         

         System.out.println("Please enter account number with five digits:");
         accountNum = getAccountNuber(stdin);
      } 
      catch(java.util.InputMismatchException e)
      {
          throw e;  
      }

      main.Account added = bankdata.createNewAccount(name, ssn, type, accountNum);

      System.out.println("The new account has been created. Account summary:");
      System.out.println(added.getAccountSummary());
   }
   
   private void withdraw(Scanner stdin, main.Account manacc)
   {
      if (manacc.getAccountBalance() == 0)
      {
         System.out.println("The balance is 0.0, Please deposit before withdraw.");
         return;
      }
      
      float amount =  Float.parseFloat(stdin.nextLine().trim());
      boolean success = manacc.withdraw(amount);
      if (success)
      {
         System.out.println(String.format(
               "Your withdrawal has been accepted with an amount of %.2f", amount));
      }
      else
      {
         System.out.println("transaction failed");
      }
   }
   
   private void deposit(Scanner stdin, main.Account manacc)
   {
      float amount =  Float.parseFloat(stdin.nextLine().trim());
      boolean success = manacc.deposit(amount);
      if (success)
      {
         System.out.println(String.format(
               "Your deposit has been accepted with an amount of %.2f", amount));
      }
      else
      {
         System.out.println("transaction failed");
      }
   }
   
   private void manageaccount(Scanner stdin)
   {
      if (bankdata.numAccounts() == 0)
      {
         System.out.println("The number of accounts is 0. Please create an account first.");
         return;
      }
      
      System.out.println("Please enter your account number:");
      int accountNumber = getAccountNuber(stdin);
      main.Account manacc = bankdata.findAccount(accountNumber);
      
      System.out.println("Please select account type: "
            + "1.View account summary, "
            + "2.Withdraw, 3.Deposit, "
            + "4.Main menu");
      
      int cmd = Integer.parseInt(stdin.nextLine().trim());// get rid of spaces and newline 
      switch (cmd) {
         case 1: // View account summary
            manacc.getAccountSummary();
            return;
         case 2: // Withdraw
            withdraw(stdin, manacc);
            return;
         case 3: // Deposit
            deposit(stdin, manacc);
            return;
         case 4: // Main menu
            return;
         default:
            System.out.println("bad command" + cmd);
            break;
      }
      
   }
      
   private void listaccounts(Scanner stdin)
   {
      int numaccounts = bankdata.numAccounts();
      System.out.println("The total number of accounts is " + numaccounts);
      ArrayList<main.Account> accounts = bankdata.sortAccounts();
      Iterator<main.Account> accitr = accounts.iterator();
      while (accitr.hasNext()) 
      {
         Account next = accitr.next();
         next.addMonthlyInterest();
         System.out.println(next.getAccountSummary());
      }
   }
   
   public void runConsole()
   {
      ScannerInputByFile("testinput2.txt");
      System.out.println("Banking System is running...!");
      
      while(stdin.hasNext())
      {
         System.out.println(
               "Please enter C to create a new account, "
               + "M to manage an existing account, "
               + "L to list all account in the order by "
               + "balance from lowest to highest, "
               + "Q to quit");
      
         String cmd = stdin.nextLine().trim();// get rid of spaces and newline 

         switch (cmd.toUpperCase()) {
            case "C":
               createaccount(stdin);
               break;
            case "M":
               manageaccount(stdin);
               break;
            case "L":
               listaccounts(stdin);
               break;
            case "Q":
               System.out.println("Thanks for using Banking System. Bye!");
               break;
            default:
               System.out.println("bad command" + cmd);
               break;
         } 
      }
   }  
}
