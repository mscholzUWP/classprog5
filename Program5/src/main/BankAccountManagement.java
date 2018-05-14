package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

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
      
      Customer toAdd = new Customer(name, ssn);
      customerlist.add(toAdd);
      return toAdd;
      
   }
   
   public Account createNewAccount(String name, SSNum ssn, int type, int accountNum)
   {
      // check id is not already used retuen null;
      if (if type == CHECKING)
      {
         Account toAdd = new CheckingAccount();
      }
      else if (if type == SAVINGS)
      {
         Account toAdd = new SavingsAccount();
      }
      
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
   
   private void insertionSortInArrayList(ArrayList list)
   {
      int top = accountlist.size();
      for(int i = 0; i < top; i++)
      {
         // find min value in array
         int leastindex = i;
         for(int j = i; i < (top-1); j++)
         {
            if(list.get(leastindex).compareTo(list.get(j)) == 1)
            {
               leastindex = j;
            }
         }
         
         // swap least with index i 
         Object temp = list.get(i);
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
      console.ConsoleControl textio = new console.ConsoleControl(this);
      textio.runConsole();
   }
    
   public void runGUI()
   { // gui version
      loadDummydata();
      // load / run gui
   }
}
