/*
 * author		: [Zhang Yuan]
 * matric no.	: [a0157059x]
 */

import java.util.*;

public class Palindrome {
	/* use this method to check whether the string is palindrome word or not
	 * 		PRE-Condition  :same length
	 * 		POST-Condition :equals after reversion
	 */
	public static boolean isPalindrome(String word) {

		int len=word.length();
		for (int i=0; i<len/2; i++){
			if(word.charAt(i)!=word.charAt(len-1-i))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		String word_1=sc.nextLine();
		String word_2=sc.nextLine();

		if(word_1.length()!=word_2.length())
			System.out.print("NO\n");
		else{
			if(isPalindrome(word_1+word_2))
				System.out.print("YES\n");
			else
				System.out.print("NO\n");
		}

	}
}
