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
        
    }
    
    public String toString()
    {
        return String.format("%1$s-%2$s-%3$s", num1, num2, num3);
    }
}
