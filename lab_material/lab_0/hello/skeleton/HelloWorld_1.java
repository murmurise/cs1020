/**
 *
 * author : Alex Z
 * matric no: A0157059X
 * 
 */

import java.util.*;

public class HelloWorld_1 {
  
  public static void main(String[] args) {
    
    int opType;
    
    Scanner sc = new Scanner(System.in);
    opType = sc.nextInt();
    String output = "";
    
    switch (opType){
      case 1:{
        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
          sc.nextLine();
          String op = sc.next();
          System.out.println( loOp(op, sc.nextInt(), sc.nextInt()));    
        }
      }
      break;
      
      case 2:{
        while(true){
          sc.nextLine();
          String op = sc.next();
          if (op.equals("0"))
            break;
          System.out.println( loOp(op, sc.nextInt(), sc.nextInt()));    
          
        }
      }
      break;
      
      case 3:{
        while(sc.hasNext()){
          sc.nextLine();
          String op = sc.next();
          System.out.println( loOp(op, sc.nextInt(), sc.nextInt()));    
        } 
      }
      break;
    }
    
    sc.close();    
  }
  
  public static int loOp(String op, int a, int b){
    
    if (op.equals("AND"))
      return a&b;
    else if (op.equals("OR"))
      return a|b;  
    
    return -1;
  }
}

