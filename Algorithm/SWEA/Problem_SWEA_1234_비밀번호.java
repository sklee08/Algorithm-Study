package algo_study;

import java.util.Scanner;
import java.util.Stack;

public class Problem_SWEA_1234_비밀번호 {

	public static String getLeft(String pw, int N) {
		String ret = "";
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i< N; i++) {
			if(!stack.isEmpty()) {
				if(stack.peek() == pw.charAt(i)) {
					stack.pop();
				}else {
					stack.push(pw.charAt(i));
				}				
			}else {
				stack.push(pw.charAt(i));
			}
		}
		while(!stack.isEmpty()) {
			ret = stack.pop() + ret;
		}
		return ret;
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) {
			String ret = "";
			int N = scan.nextInt();
			String pw = scan.next();
			
			ret = getLeft(pw, N);
			
			System.out.println("#" +t+" "+ret);
		}		
		scan.close();
	}

}
