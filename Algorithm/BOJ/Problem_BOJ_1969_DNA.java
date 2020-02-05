package algo_study;

import java.util.Scanner;

public class Problem_BOJ_1969 {
	
	static char [][] dna;
	static int N;
	static int M;
	static char [] chDna = {'A','C','G','T'};
	
	
	public static char getMostChar(int []arr) {
		int max = 0;
		for(int i = 0; i< 4; i++) {
			if(max <= arr[i]) {
				max = arr[i];
			}
		}// max 구하기
		boolean []maxArr = new boolean[4];
		for(int i = 0; i < 4; i++) {
			if(max == arr[i]) {
				maxArr[i] = true;
				break;
			}
		}
		char ret = ' ';
		for(int i =0; i<4; i++) {
			if(maxArr[i]) {
				ret = chDna[i];
			}
		}
		return ret;
	}
	// int 배열에서 가장 빈번한 char값 출력 

	
	
	public static String getLeastDna() {
		
		// A C G T
		
		String ret = "";
		for(int i = 0; i< M; i++) {
			int[] numArr = new int[4];
			for(int j = 0; j <N; j++) {
				for(int k = 0; k < 4; k++) {
					if(dna[j][i] == chDna[k]) {
						numArr[k]++;
					}
				}
				// 
				
			}
			// 
			ret += getMostChar(numArr);
		}
		return ret;
	}
	
	public static int getResult(String res) {
		int ret = 0;
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < M; j++) {
				if(dna[i][j] != res.charAt(j)) {
					ret++;
				}
			}
		}
		return ret;
	}
	
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		scan.nextLine();
		dna = new char[N][M];
		
		for(int i = 0; i< N; i++) {
			String tmp = scan.nextLine();
			for(int j = 0; j <M; j++) {
				dna[i][j] = tmp.charAt(j);
			}
		}
		String ans = getLeastDna();
		System.out.println(ans);
		System.out.println(getResult(ans));
		
	}
}
