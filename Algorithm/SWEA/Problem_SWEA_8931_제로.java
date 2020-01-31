package algo_day5;

import java.util.Scanner;
import java.util.Stack;

public class Problem_SWEA_8931_제로 {
	
	public static int getSum(Stack<Integer> stack) {
		int sum = 0;
		while(!stack.isEmpty()) {
			sum += stack.pop();
		}
		
		return sum;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int tc = scan.nextInt();
		
		for(int t=1; t<=tc; t++) {
			// test case
			Stack<Integer> stack = new Stack<>();
			int K = scan.nextInt();
			for(int i = 0; i < K; i++) {
				int tmp = scan.nextInt();
				
				if(tmp != 0) {
					// 0이 아닌 경우
					stack.push(tmp);
				}else {
					// 0인 경우
					stack.pop();
				}
			}
			
			if(stack.isEmpty()) {
				System.out.println("#" +t+ " "+0);
			}else {
				System.out.println("#" +t+ " "+getSum(stack));
			}
		}

	}

}
