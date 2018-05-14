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
      main.Account.AccountType type;
      int accountNum;
      
//      try
//      {
         System.out.println("Please enter the name of the customer:");
         name = stdin.nextLine();

         System.out.println("Please enter the SSN of the customer (***-**-****):");
         ssn = new SSNum(stdin.nextLine());
         
         System.out.println("Please select account type: 1.Savings, 2.Checking");
         int typei = Integer.parseInt(stdin.nextLine());
         if (typei != 1 && typei != 2){throw new IllegalArgumentException();}
         type = main.Account.AccountType.values()[typei]; // convert int into enum
         

         System.out.println("Please enter account number with five digits:");
         accountNum = getAccountNuber(stdin);
//      } 
//      catch(java.util.InputMismatchException e)
//      {
//            
//      }

      main.Account added = bankdata.createNewAccount(name, ssn, type, accountNum);
      
      
      System.out.println("The new account has been created. Account summary:");
      System.out.println(added.getAccountSummary());
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
      
      System.out.println("Please select account type: "
            + "1.View account summary, "
            + "2.Withdraw, 3.Deposit, "
            + "4.Main menu");
      
      int cmd = Integer.parseInt(stdin.nextLine().trim());// get rid of spaces and newline 
      switch (cmd) {
         case 1: // View account summary
            bankdata.findAccount(accountNumber).getAccountSummary();
            break;
         case 2: // Withdraw
            Withdraw;
            break;
         case 3: // Deposit
            deposit();
            break;
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
      ArrayList accounts = bankdata.sortAccounts();
//      for(account in accounts
//         print account summary
   }
   
   public void runConsole()
   {
      ScannerInputByFile("testinput1.txt");
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
