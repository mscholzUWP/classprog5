
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scholzm
 */
public class SSNum {
   //123-45-1111    
   // num1-num2-num3
   int num1;// 3 digits
   int num2;// 2 digits
   int num3;// 4 digits


   public SSNum(String ssntext)
   {// parse string as ssn input. example "123-45-1111" 
      StringTokenizer ssntok = new StringTokenizer(ssntext, "-");
      String token;
      
      token = ssntok.nextToken();
      if (token.length() != 3) { throw new IllegalArgumentException();}
      num1 = Integer.parseInt(token);
      
      token = ssntok.nextToken();
      if (token.length() != 2) { throw new IllegalArgumentException();}
      num2 = Integer.parseInt(token);
      
      token = ssntok.nextToken();
      if (token.length() != 4) { throw new IllegalArgumentException();}
      num3 = Integer.parseInt(token);
   }

   public String toString()
   {
       return String.format("%1$s-%2$s-%3$s", num1, num2, num3);
   }
   
   public boolean equals(SSNum test)
   {
      return(this.num1 == test.num1 &&
             this.num2 == test.num2 &&
             this.num3 == test.num3);
   }
      
}
