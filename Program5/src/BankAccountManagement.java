

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
   
   public void manageAccount()
   {
   
   }
   
   public ArrayList sortAccounts()
   {
      insertionSortInArrayList(accountlist);
      return accountlist;
   }
   
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
   
   public int numAccounts()
   {
      return accountlist.size();
   }
   
   // loads dummmy data for gui testing
   private void loadDummydata()
   {
      
   }

   public void run()
   { // console version
      ConsoleControl textio = new ConsoleControl(this);
      textio.runConsole();
   }
    
   public void runGUI()
   { // gui version
      loadDummydata();
      // load / run gui
   }
}
