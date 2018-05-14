package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scholzm
 */
public abstract class Account {
   protected static int totalAccountNumber = 0;
   protected int accountNum;
   protected String holdername;
   protected String type;          
   protected float balance;
   
   
   public abstract float getAccountBalance();

   public Account()
   {
      balance = 0;
      totalAccountNumber++;
   }
   
   public boolean withdraw(float amount)
   {
      if (balance >= amount)
      {
         balance -= amount;
         return true;
      }
      return false;
   }
   
   public boolean deposit(float amount)
   {
      balance += amount;
      return true; // can this ever be false?
   }

   // comperable interface
   public boolean compareTo(Account test)
   {
      return false;
   }
   
   String getAccountSummary()
   {
      //return String.format("");
      String summary = "";
      summary += String.format(
            "Account holder is %s; Account number is %s; Account type is %s;",
            holdername, accountNum, type);
      
//      summary += String.format(
//            "Account balance is %f", getAccountBalance());
      return summary;
   }
}
