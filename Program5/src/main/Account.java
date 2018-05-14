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
public abstract class Account implements Comparable{
   protected static int totalAccountNumber = 0;
   protected int accountNum;
   protected String holdername;
   protected float balance;
   
   public enum AccountType {
      CHECKING(1, "checking"), 
      SAVINGS(2,"savings");
      
      private int value;
      private String name;
      
      AccountType(int value, String name){
         this.value = value;
         this.name = name;
      }
      public String toString()
      {
         return name;
      }
   }
   
   AccountType type;
   //CHECKING("checking"), SAVINGS("savings");
   
   
   public abstract float getAccountBalance();

   public Account(int accountNum)
   {
      this.accountNum = accountNum;
      balance = 0;
      totalAccountNumber++;
   }
   public void setHolderName(String holdername)
   {
      this.holdername = holdername;
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
   public int compareTo(Object test)
   {
      if(!(test instanceof Account))
      {
         throw new NullPointerException("bad object");
      }
      if(test == null)
      {
         throw new NullPointerException("test is null");
      }
      //if (test instanceof Account)
      
      if (this.balance == test.balance )
         return 0; 
      
      if (this.balance > test.balance )
         return 1; 
      
      if (this.balance < test.balance )
         return -1; 
      
      throw new NullPointerException("not greater,equal,lessthan");
   }
   
   public boolean equals(Object test)
   {
      if(test instanceof Account)
      {
         return (this.accountNum == ((Account)test).accountNum);
      }
      return false;
   }
   
   public String getAccountSummary()
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
