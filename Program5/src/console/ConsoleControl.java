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
   
   private void createaccount(Scanner stdin)
   {
      try
      {
         System.out.println("Please enter the name of the customer:");
         String name = stdin.nextLine();

         System.out.println("Please enter the SSN of the customer (***-**-****):");
         SSNum ssn = new SSNum(stdin.nextLine());

         System.out.println("Please select account type: 1.Savings, 2.Checking");
         int type = Integer.parseInt(stdin.nextLine());
         if (type != 1 && type != 2){throw new IllegalArgumentException();}

         System.out.println("Please enter account number with five digits:");
         int accountNum = Integer.parseInt(stdin.nextLine());
         
      } 
      catch(java.util.InputMismatchException e)
      {
            
      }

      addAccount(name, ssn, type, accountNum);
      
      
      System.out.println("The new account has been created. Account summary:");
//      System.out.println(String.format(
//            "Account holder is %s; Account number is %n; Account type is %s;"
//            account.name(), account.number, account.type)));

      System.out.println(account.accountsummary());
   
         
      System.out.println(String.format(
            "Account balance is %f", account.getAccountBalance()));
   }
   
   private void manageaccount(Scanner stdin)
   {
      if (bankdata.numAccounts() == 0)
      {
         System.out.println("The number of accounts is 0. Please create an account first.");
         return;
      }
      
      System.out.println("Please select account type: "
            + "1.View account summary, "
            + "2.Withdraw, 3.Deposit, "
            + "4.Main menu");
                              
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
      
      String cmd = stdin.next().trim();// get rid of spaces and newline 

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
