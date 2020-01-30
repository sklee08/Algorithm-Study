package algo_study;

import java.util.Scanner;

public class Problem_BOJ_1252 {
	
	public static int getCarry(int carry, int a, int b) {
		if(carry + a + b >= 2) return 1;
		else return 0;
	}
	
	public static String reverseString(String s) {
		return (new StringBuilder(s)).reverse().toString();
	}
	
	
	public static void main(String []args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		String ret = "";
		String s1 = "";
		int index = 0;
		
		while(input.charAt(index) != ' ') {
			s1 += input.charAt(index);
			index++;
		}
		int n1 = s1.length();
		String s2 = input.substring(index +1);
		int n2 = s2.length();
		int carry = 0;
		int i;
		if(n1 == n2) {
			
		}else if(n1 > n2){
			for(i = n2; i < n1; i++) {
				s2 = "0" + s2;
			}
		}else {
			for(i = n1; i < n2; i++) {
				s1 = "0" + s1;
			}
		}
		
		
		for(i = Integer.max(n1, n2) - 1; i >= 0 ; i--) {
			int sum = s1.charAt(i)-'0' + s2.charAt(i) - '0' + carry;
			if(sum == 3) {
				ret = "1" + ret;
				carry = 1;
			}else if(sum == 2) {
				ret = "0" + ret;
				carry = 1;
			}else if(sum == 1) {
				ret = "1" + ret;
				carry = 0;
			}else {
				ret = "0" + ret;
				carry = 0;
			}
		}
		if(carry == 1) {
			ret = carry + ret;
		}
		String ans = "";
		boolean one = true;
		for(i = 0; i < ret.length(); i++) {
			if(ret.charAt(i) == '1')
				one = false;
			if(one) continue;
			ans += ret.charAt(i);
		}
		if(ans.equals("")) {
			System.out.println(0);
		}else {
			System.out.println(ans);
		}		
	}
}
