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
public class SavingsAccount extends Account{
   private float annualInterestRate = 0.005f;
   
   
   public SavingsAccount(int id)
   {
      super(id);
      type = Account.AccountType.SAVINGS;
   }
   
   //@override
   public boolean withdraw(float amount)
   {
      if (balance >= amount)
      {
         balance -= amount;
         return true;
      }
      return false;
   }
   
   public float getAccountBalance()
   {
      //addMonthlyInterest();
      return balance;
   }
   
   public float addMonthlyInterest()
   {
      balance = balance + balance*annualInterestRate;
      return balance; // could be very wrong
   }
   
   public String getAccountSummary()
   {
      //addMonthlyInterest();
      return super.getAccountSummary();
   }
}
