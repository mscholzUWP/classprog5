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
   private float annualInterestRate = 0.01f;
   
   
   public SavingsAccount(int id)
   {
      super();
      type = SAVINGS;
   }
   
   public float getAccountBalance()
   {
      return balance;
   }
   
   public float addMonthlyInterest()
   {
      balance = balance + balance*annualInterestRate;
      return balance; // could be very wrong
   }
   
   //public String getAccountSummary()
}
