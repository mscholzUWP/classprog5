/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author scholzm
 */
public class ConsoleControl {
    private Scanner stdin;
    
    public ConsoleControl()
    {
        stdin = new Scanner (System.in); 
    }
    
    /**
   replaces keyboard input scanner with file input for testing
   */
   private void ScannerInputByFile(String filename)
   {
      try
      {
         //String filename = "test_input4.txt";
         File f = new File(filename);
         stdin = new Scanner(f);
         //System.out.println(String.format("opening %s as input file", filename));
      }
      catch(java.io.FileNotFoundException e)
      {
         // dont overwrite manual if file not found
         return;
      }
      catch (Exception ex)
      {
         System.out.println(String.format(
               "Working Directory = %s", System.getProperty("user.dir")));
         System.out.println(ex.toString());
         ex.printStackTrace();
         return;
      }
   }
   
   public void runConsole()
   {
        ScannerInputByFile("test_input.txt");
       while(stdin.hasNext())
       {
        String cmd = stdin.next().trim();// get rid of spaces and newline 

        switch (cmd.toupper()) {
            case "Q":
                break;
            default:
                break;
          
       }
       
   }
   
    
    
}
