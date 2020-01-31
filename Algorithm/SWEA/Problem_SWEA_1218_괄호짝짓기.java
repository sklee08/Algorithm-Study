package algo_day5;

import java.util.Scanner;
import java.util.Stack;

public class Problem_SWEA_1218_괄호짝짓기 {
	
	public static char getCouple(char ch) {
		switch(ch) {
		case '<':
			return '>';
		case '(':
			return ')';
		case '{':
			return '}';
		case '[':
			return ']';
		}
		
		return 0;
	}
	
	public static void main(String []args) {
		Scanner scan = new Scanner(System.in);
		
		
		for(int t = 1; t <= 10; t++) {
			Stack<Character> stack = new Stack<>();
			int N = scan.nextInt();
			String tmp =scan.next();
						
			boolean isTrue = true;
			for(int i = 0; i < N; i++) {
				if(tmp.charAt(i) == '(' || tmp.charAt(i) == '[' || tmp.charAt(i) == '{' || tmp.charAt(i) == '<') {
					stack.push(tmp.charAt(i));
				}else {
					if(stack.isEmpty()) {
						isTrue = false;
						break;
					}else {
						char ch = stack.pop();
						if(tmp.charAt(i) != getCouple(ch)) {
							isTrue = false;
							break;
						}
					}
				}
			}
			if(!stack.isEmpty()) {
				isTrue = false;
			}
			if(isTrue) {
				System.out.println("#" +t+ " " + 1);
			}else {
				System.out.println("#" +t+ " " + 0);
			}			
		}
		System.out.println(getCouple('<'));
	}
}
