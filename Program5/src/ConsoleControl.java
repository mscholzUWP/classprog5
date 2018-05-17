/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Iterator;

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
      int anum;
      try
      {
      anum = Integer.parseInt(accountNum);
      }
      catch(java.lang.NumberFormatException e)
      {
         throw new java.util.InputMismatchException();
      }
      if (accountNum.length() != 5)
      {
         throw new java.lang.IllegalArgumentException();
      }
      
      return anum;
   }
   
   private void createaccount(Scanner stdin)
   {
      String name;
      SSNum ssn;
      Account.AccountType type = null;
      int accountNum;
      
      try
      {
         System.out.println("Please enter the name of the customer:");
         name = stdin.nextLine();

         System.out.println("Please enter the SSN of the customer (***-**-****):");
         while(true) 
         {
            try
               {
                  ssn = new SSNum(stdin.nextLine());
                  break;
               }
               catch(java.lang.IllegalArgumentException e)
               {
                  System.out.println(String.format("Invalid Social Security Number. %s", e));
                  System.out.println("Please enter valid SSN:");
               }
         }
         System.out.println("Please select account type: 1.Savings, 2.Checking");
         while(true)
         {
            try
            {
               int typei;
               try
               {
                  typei = Integer.parseInt(stdin.nextLine());
               }
               catch(java.lang.NumberFormatException e)
               {
                  throw new java.util.InputMismatchException();
               }
               
               if (typei != 1 && typei != 2){throw new java.lang.IllegalArgumentException();}
               //type = main.Account.AccountType.byInput(typei); // convert int into enum
               if (typei == 1 )
               {
                  type = Account.AccountType.SAVINGS;
               }
               else if (typei == 2 )
               {
                  type = Account.AccountType.CHECKING;
               }
               else 
               {
                  throw new java.lang.IllegalArgumentException();
               }
               break;
            }
            catch(java.util.InputMismatchException e)
            {
               System.out.println(String.format("Invalid Input. %s Please try again", e));
            }
            catch(java.lang.IllegalArgumentException e)
            {
               System.out.println(String.format("Invalid Input. Please try again"));
            }

         }

         System.out.println("Please enter account number with five digits:");
         while(true)
         {
            try
            {
               accountNum = getAccountNuber(stdin);
               break;
            }
            catch(java.util.InputMismatchException e)
            {
               System.out.println(String.format("Invalid Input. %s Please try again", e));
            }
            catch(java.lang.IllegalArgumentException e)
            {
               System.out.println(String.format("Invalid Input. Please try again"));
            }
         }
      } 
      catch(java.util.InputMismatchException e)
      {
          throw e;  
      }

      Account added = bankdata.createNewAccount(name, ssn, type, accountNum);

      System.out.println("The new account has been created. Account summary:");
      System.out.println(added.getAccountSummary());
   }
   
   private void withdraw(Scanner stdin, Account manacc)
   {
      if (manacc.getAccountBalance() == 0)
      {
         System.out.println("The balance is 0.0, Please deposit before withdraw.");
         return;
      }
      System.out.println("Please enter the amount of money:");
      
      float amount;
      while(true)
      {
         try
         {
            try
            {
               amount =  Float.parseFloat(stdin.nextLine().trim());
               break;
            }
            catch(java.lang.NumberFormatException e)
            {
               throw new java.util.InputMismatchException();
            }
         }
         catch(java.util.InputMismatchException e)
         {
            System.out.println(String.format("Invalid Input. %s Please try again", e));
         }
      }
      
      boolean success = manacc.withdraw(amount);
      if (success)
      {
         System.out.println(String.format(
               "Your withdrawal has been accepted with an amount of %.2f", amount));
      }
      else
      {
         System.out.println("You don't have enough balance. Please try a different amount.");
      }
   }
   
   private void deposit(Scanner stdin, Account manacc)
   {
      System.out.println("Please enter the amount of money:");
      
      float amount;
      while(true)
      {
         try
         {
            try
            {
               amount =  Float.parseFloat(stdin.nextLine().trim());
               break;
            }
            catch(java.lang.NumberFormatException e)
            {
               throw new java.util.InputMismatchException();
            }
         }
         catch(java.util.InputMismatchException e)
         {
            System.out.println(String.format("Invalid Input. %s Please try again", e));
         }
      }
      
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
      Account manacc;
      while(true)
      {
         try
         {
            int accountNumber = getAccountNuber(stdin);
            
            manacc = bankdata.findAccount(accountNumber);
            if (manacc == null)
            {
               System.out.println("The account number you entered does not exist. Please try again");
               continue; // go back to while beginning
            }
            else
            {
               break;
            }
            
         }
         catch(java.util.InputMismatchException e)
         {
            System.out.println(String.format("Invalid Input. %s Please try again", e));
         }
         catch(java.lang.IllegalArgumentException e)
         {
            System.out.println(String.format("Invalid Input. Please try again"));
         }
      }
      
      
      System.out.println("Please select account type: "
            + "1.View account summary, "
            + "2.Withdraw, 3.Deposit, "
            + "4.Main menu");
      
      int cmd; 
      while(true)
      {
         try
         {
            cmd = Integer.parseInt(stdin.nextLine().trim());// get rid of spaces and newline 
         }
         catch(java.lang.NumberFormatException e)
         {  // naughty, but why have us convert numberformat exceptions into 
            //      input mismatch exceptions and then display them. really?
            System.out.println("Invalid Input. java.util.InputMismatchException Please try again");
            continue;
         }
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
               System.out.println("Invalid type. Please try again");
               continue;
         }
      } 
   }
      
   private void listaccounts(Scanner stdin)
   {
      int numaccounts = bankdata.numAccounts();
      System.out.println("The total number of accounts is " + numaccounts);
      ArrayList<Account> accounts = bankdata.sortAccounts();
      Iterator<Account> accitr = accounts.iterator();
      while (accitr.hasNext()) 
      {
         Account next = accitr.next();
         next.addMonthlyInterest();
         System.out.println(next.getAccountSummary());
      }
   }
   
   public void runConsole()
   {
      ScannerInputByFile("testinput3.txt");
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
               System.out.println("Invalid command.");
               //System.out.println("Invalid command: " + cmd);
               break;
         } 
      }
   }  
}
