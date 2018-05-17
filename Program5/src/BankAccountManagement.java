/**
@author Marshall Scholz
@class Cs2430, Spring 2018 
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.lang.IllegalStateException;



/**
 *
 * @author scholzm
 */
public class BankAccountManagement {
    // maintains a list of all accounts and a list of customers.
    // Use ArrayList instead of Account array and Customer array.
   ArrayList<Customer> customerlist;
   ArrayList<Account> accountlist;
   

   public BankAccountManagement()
   {
      customerlist = new ArrayList<Customer>(10);
      accountlist = new ArrayList<Account>(20);
   }
   /**
   makes a new customer
   @param name
   @param ssn
   @return customer created
   */
   public Customer createNewCustomer(String name, SSNum ssn)
   {
      // chack that customer doesn't have identical ssn retuen null;
      if (customerlist.contains(new Customer("",ssn)))
      {
         return null;
      }
      
      Customer toAdd = new Customer(name, ssn);
      customerlist.add(toAdd);
      return toAdd;
   }
   
   /**
   will return the customer object with specified ssn
   @param ssn
   @return 
   */
   public Customer findCustomer(SSNum ssn)
   {// this should really be a hashmap.
      Iterator<Customer> itr = customerlist.iterator();
      
      while (itr.hasNext())
      {
         Customer test = itr.next();
         if(test.equals(ssn));
         {
            return test;
         }
      }
      return null;
   }
   /**
   will return the account object with specified id
   @param accountNum
   @return 
   */
   public Account findAccount(int accountNum)
   {// this should really be a hashmap.
      Iterator<Account> itr = accountlist.iterator();
      
      while (itr.hasNext())
      {
         Account test = itr.next();
         if(test.equals(accountNum))
         {
            return test;
         }
      }
      return null;
   }
   
   /**
   creates a new customer if one with identical ssn does not exist
   and then creates an account assigned to that person
   @param name
   @param ssn
   @param type
   @param accountNum
   @return 
   */
   public Account createNewAccount(String name, SSNum ssn, Account.AccountType type, int accountNum)
   {
      Customer holder = findCustomer(ssn);
      if(holder == null)
      {
         holder = createNewCustomer(name, ssn);
      }
      
      // check id is not already used return null; (this is why I like python dictonaries)
      if(accountlist.contains(new CheckingAccount(accountNum)))
      {
         return null;
      }
      Account toAdd;
      if(type == Account.AccountType.CHECKING)
      {
         toAdd = new  CheckingAccount(accountNum);
      }
      else if(type == Account.AccountType.SAVINGS)
      {
         toAdd = new SavingsAccount(accountNum);
      }
      else
      {
         //throw java.lang.IllegalStateException("somthing went wrong");
         throw new NullPointerException("somthing went wrong");
      }
      
      toAdd.setHolderName(name);
      accountlist.add(toAdd);
      
      return toAdd;
   }
   
   /**
   sorts accounts by balance using selection sort
   @return 
   */
   public ArrayList sortAccounts()
   {
      insertionSortInArrayList(accountlist);
      return accountlist;
   }
   
   /**
   sorts list by comparable using selectionsort
   @param list 
   */
   private void insertionSortInArrayList(ArrayList<Account> list)
   {
      int top = accountlist.size();
      
      for(int i = 0; i < top; i++)
      {
         // find min value in array
         int leastindex = i;
         for(int j = i; j < (top); j++)
         {
            if(list.get(leastindex).compareTo(list.get(j)) == 1)
            {
               leastindex = j;
            }
         }
         // swap least with index i 
         Account temp = list.get(i);
         list.set(i, list.get(leastindex));
         list.set(leastindex, temp);
      }
   }
   
   /**
   get number of accounts
   @return 
   */
   public int numAccounts()
   {
      return accountlist.size();
   }

   /**
   runs console version of program
   */
   public void run()
   { // console version
      ConsoleControl textio = new ConsoleControl(this);
      textio.runConsole();
   }
    
   /**
   runs gui version of program
   */
//   public void runGUI()
//   { // gui version
//      // load / run gui
//   }
}
