/**
@author Marshall Scholz
@class Cs2430, Spring 2018 
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
      CHECKING(2, "checking"), 
      SAVINGS(1,"savings");
      
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
      // get enum by passing in it's type #
//      public AccountType byInput(int type)
//      {
//         for(int i =0; i < this.values().length; i++)
//         {
//            if(this.values()[i].value == type)
//            {
//               return this.values()[i];
//            }
//         }
//         return null;
//      }
   }
   AccountType type;
   
   public abstract float getAccountBalance();
   public abstract float addMonthlyInterest();

   /**
   constructor
   @param accountNum unique id for account
   */
   public Account(int accountNum)
   {
      this.accountNum = accountNum;
      balance = 0;
      totalAccountNumber++;
   }
   /**
   sets the name of the account holder
   @param holdername 
   */
   public void setHolderName(String holdername)
   {
      this.holdername = holdername;
   }
   
   /**
   deducts amount from balance if there is enough money
   @param amount
   @return successful
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
      return true; // can this ever be false?
   }

   
   /**
   comparable interface
   @param test
   @return -1,0,1
   */
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
      Account testa = (Account)test;
      //if (test instanceof Account)
      
      if (this.balance == testa.balance )
      {
         return 0; 
      }

      if (this.balance > testa.balance )
      {
         return 1; 
      }
      
      if (this.balance < testa.balance )
      {
         return -1; 
      }

      throw new NullPointerException("not greater,equal,lessthan");
   }
   
   /**
   equals interface
   @param test
   @return bool isequals
   */
   public boolean equals(Object test)
   {
      if(test instanceof Account)
      {
         return (this.accountNum == ((Account)test).accountNum);
      }
      if(test instanceof Integer)
      {
         return (this.accountNum == ((int)test));
      }
      return false;
   }
   
   /**
   summarises account info
   @return 
   */
   public String getAccountSummary()
   {
      //return String.format("");
      String summary = "";
      summary += String.format(
            "Account holder is %s; Account number is %s; "
                  + "Account type is %s; Account balance is %.2f",
            holdername, accountNum, type, balance); // populate data and round balance
      return summary;
   }
}
