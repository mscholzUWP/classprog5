

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scholzm
 */
public class CheckingAccount extends Account{
   
   public CheckingAccount(int id)
   {
      super(id);
      type = Account.AccountType.CHECKING;
   }
   
   //@override
   public float getAccountBalance()
   {
      return balance;
   }
   
   public float addMonthlyInterest()
   {
      return balance;
   }
   
   //public String getAccountSummary()
}
