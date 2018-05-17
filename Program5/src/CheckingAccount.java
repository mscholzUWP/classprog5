

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scholzm
 */

/**
checking account class. 
subclass from account
*/
public class CheckingAccount extends Account{
   
   /**
   constructor
   @param id unique id for account
   */
   public CheckingAccount(int id)
   {
      super(id);
      type = Account.AccountType.CHECKING;
   }
   
   //@override
   /**
   @return balance of account
   */
   public float getAccountBalance()
   {
      return balance;
   }
   
   /**
   does nothing. placeholder to provide identical 
   interface to savings account
   @return account balance
   */
   public float addMonthlyInterest()
   {
      return balance;
   }
   
   //public String getAccountSummary()
}
