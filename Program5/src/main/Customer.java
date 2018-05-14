package main;

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
public class Customer {
//    Social Security Number (SSN) is the unique identifier of customers. 
//            When opening an account, check if a customer has already existed, 
//            if so, associate the new account to the customer. 
//            􀀬􀁗􀂶􀁖􀀃􀁌􀁏􀁏􀁈􀁊􀁄􀁏􀀃􀁗􀁒􀀃􀁋􀁄􀁙􀁈􀀃two customers with 
//            the same SSN in the customer list.
    
   private SSNum ssn;
   private String name;
   private ArrayList<Account> accountlist;
   
   public Customer(String name, SSNum ssn)
   {
      this.name = name;
      this.ssn = ssn;
   }
   
   public boolean addAccount(Account toAdd)
   {
      
      return false;
   }
   
   public ArrayList<Account> getAccountList()
   {
      return null;
   }

}
