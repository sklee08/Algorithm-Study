package algo_study;

import java.util.Scanner;

public class Problem_BOJ_11179_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		long N = scan.nextLong();
		long answer = 0;
		int pow = getPow(N);
		
		while(N > 0) {
			answer += (N % 2) * (long)Math.pow(2, pow);
			N/=2;
			pow--;
		}
		System.out.println(answer);
	}
	
	public static int getPow(long n) {
		int cnt = 0;
		while(true) {
			if((long)Math.pow(2, cnt) > n) {
				break;
			}
			cnt++;
		}
		return cnt - 1;
	}

}
