package algo_study;

import java.util.Scanner;
public class Problem_BOJ_9625_BABBA {

	static int K;
	static int a;
	static int b;
	
	public static void getBA(int n) {
		if(n == 1) {
			b= 1;
			a = 0;
		}else if(n == 2) {
			a = 1;
			b = 1;
		}else {
			getBA(n-1);
			int tmpA = a;
			int tmpB = b;
			a = a + tmpB - tmpA;
			b = b + tmpA;
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		K =scan.nextInt();
		getBA(K);
		System.out.printf("%d %d", a, b);
	}

}
