package algo_study;


import java.util.Scanner;

import java.util.ArrayList;
import java.util.Stack;


public class Problem_JO_1809_탑 {
	
	
	private static Stack<Integer> stack;
	private static Stack<Integer> index;
	private static int N;
	
		
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		stack = new Stack<>();
		index = new Stack<>();
		
		int []arrNum = new int[N];
		int []answer = new int[N];
		
		for(int i = 0; i < N; i++) {
			arrNum[i] = scan.nextInt();			
		}
		stack.push(arrNum[N-1]);
		index.push(N-1);
		for(int i = N-1; i>0; i--) {
			if(arrNum[i] < arrNum[i-1]) {
				while(!stack.isEmpty() && stack.peek() < arrNum[i -1]) {
					answer[index.peek()] = i;
					stack.pop();
					index.pop();
				}
				stack.push(arrNum[i-1]);
				index.push(i-1);
			}else {
				stack.push(arrNum[i-1]);
				index.push(i-1);
				
			}
		}
		
		for(int i = 0; i<N; i++) {
			System.out.print(answer[i] + " ");
		}
	}

}
