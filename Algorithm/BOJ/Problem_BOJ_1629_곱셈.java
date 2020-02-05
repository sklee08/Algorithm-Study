package algorithm_study;

import java.util.Scanner;

public class Problem_BOJ_1629 {

	// Non recursive
	public static long getMul(long A, long B, long C) {
		A = A %C;
		if(B == 0) {
			return 1;
		}else if(B %2 ==0) {
			return getMul(A*A, B>>1,C) % C;
		}else {
			return A*getMul(A*A, (B-1) >> 1, C) % C;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		long A = scan.nextLong();
		long B = scan.nextLong();
		long C = scan.nextLong();
		System.out.println(getMul(A,B,C));
		scan.close();
	}

}
