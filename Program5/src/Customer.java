
/**
@author Marshall Scholz
@class Cs2430, Spring 2018 
*/

import java.util.ArrayList;
/**
 *
 * @author scholzm
 */
public class Customer {
//    Social Security Number (SSN) is the unique identifier of customers. 
//            When opening an account, check if a customer has already existed, 
//            if so, associate the new account to the customer. 
//            two customers with 
//            the same SSN in the customer list.
    
   private SSNum ssn;
   private String name;
   private ArrayList<Account> accountlist;
   
   public Customer(String name, SSNum ssn)
   {
      this.name = name;
      this.ssn = ssn;
   }
   
   /**
   
   @param toAdd
   @return 
   */
//   public boolean addAccount(Account toAdd)
//   {
//      return false;
//   }
   
//   public ArrayList<Account> getAccountList()
//   {
//      return null;
//   }

   /**
   checks if test equals customer ssn
   @param test
   @return 
   */
   public boolean equals(Object test)
   { // tests if same ssn.
      if(test instanceof Customer)
      {
         return (this.ssn.equals(((Customer)test).ssn));
      }
      if(test instanceof SSNum)
      {
         return (this.ssn.equals(((SSNum)test)));
      }
      return false;
   }
}
