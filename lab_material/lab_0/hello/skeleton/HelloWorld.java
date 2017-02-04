/*
 *
 * author : Alex Z
 * matric no: A0157059X
 * 
 */

import java.util.*;

public class HelloWorld {

	public static void main(String[] args) {

		int opType;

		Scanner sc = new Scanner(System.in);
		opType = sc.nextInt();

		switch (opType){
			case 1:
				nLine(sc.nextInt(), sc);
			case 2:
				readUntil(sc);
			case 3:
				eOF(sc);
		}
		sc.close();
	System.out.print("  ");
	}
	//input reading method 1
	public static void nLine(int n, Scanner sc){

		String output = "";

		for (int i = 0; i < n; i++){
			sc.nextLine();

			String op = sc.next();
			output += loOp(op, sc.nextInt(), sc.nextInt()) + "\n \n";    
		}

		System.out.println(output);
	}

	//input reading method 2
	public static void readUntil(Scanner sc){

		String output = "";

		while(true){
			sc.nextLine();
			String op = sc.next();
			if (op.equals("0"))
				break;
			output += loOp(op, sc.nextInt(), sc.nextInt()) + "\n" ;    

		} 
		System.out.println(output);

	}

	public static void eOF(Scanner sc){

		String output = "";

		while(sc.hasNextLine()){
			sc.nextLine();
			String op = sc.next();
			output += loOp(op, sc.nextInt(), sc.nextInt()) + "\n" ;    

		} 
		System.out.println(output);

	}
	public static int loOp(String op, int a, int b){

		if (op.equals("AND"))
			return a&b;
		else if (op.equals("OR"))
			return a|b;  

		return -1;
	}
}

