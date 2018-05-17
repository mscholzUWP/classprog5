

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
   
   /**
   constructor
   @param id unique id for account
   */
   public SavingsAccount(int id)
   {
      super(id);
      type = Account.AccountType.SAVINGS;
   }
   
   //@override
   /**
   @return balance of account
   */
   public boolean withdraw(float amount)
   {
      if (balance >= amount)
      {
         balance -= amount;
         return true;
      }
      return false;
   }
   /**
   adds amount to balance
   @param amount
   @return 
   */
   public boolean deposit(float amount)
   {
      balance += amount;
      return false; // can this ever be false?
   }
   /**
   @return balance of account
   */
   public float getAccountBalance()
   {
      //addMonthlyInterest();
      return balance;
   }
   
   /**
   adds interest to account balance
   @return balance
   */
   public float addMonthlyInterest()
   {
      balance = balance + balance*annualInterestRate;
      return balance; // could be very wrong
   }
   
}
