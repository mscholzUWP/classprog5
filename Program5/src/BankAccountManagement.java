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
      customerlist = new ArrayList<Customer>();
      accountlist = new ArrayList<Account>();
   }
   
   public Account addAccount(String name, SSNum ssn, int type, int accountNum)
   {
      return null;
   }
   
   public void sortAccountsByBalance()
   { // insertion sort? look at prog5 pdf
      
   }
   
   // loads dummmy data for gui testing
   private void loadDummydata()
   {
      
   }

   public void run()
   { // console version
      console.ConsoleControl textio = new console.ConsoleControl();
      textio.runConsole(this);
   }
    
   public void runGUI()
   { // gui version
      loadDummydata();
      // load / run gui
   }
   

   
}
