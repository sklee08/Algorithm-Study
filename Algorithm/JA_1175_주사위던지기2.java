package algorithm_study;

import java.util.Scanner;

public class JA_1175_주사위던지기2 {

	static int [] dices; 
	
	public static void print() {
		for(int d : dices) {
			System.out.print(d+" ");
		}
		System.out.println();
	}
	
	public static boolean isRight(int M, int[] tmp) {
		int sum = 0;
		for(int i = 0; i< tmp.length; i++) {
			sum += tmp[i];
		}
		return sum == M;
	}
	
	public static void getPer(int r, int curr, int M) {
		if(curr == r) {
			if(isRight(M, dices)) {
				print();
			}
			return;
		}else {
			for(int i = 0; i< 6; i++) {
				dices[curr] = i+1;
				getPer(r, curr+1, M);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		dices = new int[N];
		int M = scan.nextInt();

		getPer(N,0, M);
	}

}
