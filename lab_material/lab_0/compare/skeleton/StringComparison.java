/**
 *
 * author : Alex Z.
 * matric no: A0157059X
 * 
 */

import java.util.*;


public class StringComparison {
  
  public static void main(String[] args) {
    
    // declare the necessary variables
    String input1 = "";
    String input2 = "";
    int result; 
    
    // declare a Scanner object to read input
    Scanner sc = new Scanner(System.in);
    
    // read input and process them accordingly
    input1 = sc.nextLine();
    input2 = sc.nextLine();
    
    //output
    result = input1.toLowerCase().compareTo(input2.toLowerCase());
    if (result != 0){
      if (result > 0)
        result = 2;
      else 
        result = 1;
    }
    
    System.out.print(result+"\n");
  }
}
