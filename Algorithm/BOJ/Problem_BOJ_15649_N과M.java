package algorithm_study;

import java.util.Scanner;
import java.util.LinkedList;

public class Problem_BOJ_15649 {

	public static void getArr(int N, int M, LinkedList<Integer> perArr, int[] perCheck) {
		if(perArr.size() == M) {
			for(int i : perArr) {
			System.out.print((i+1)+ " ");
			}
			System.out.println();
			return;
		}
		for(int i = 0; i< N; i++) {
			if(perCheck[i] == 0) {
				perArr.add(i);
				perCheck[i] = 1;
				getArr(N,M,perArr, perCheck);
				perCheck[i] = 0;
				perArr.removeLast();
			}
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		int []perCheck = new int[N];
		
		LinkedList<Integer> perArr = new LinkedList<Integer>();
		
		getArr(N,M, perArr,perCheck);
		scan.close();		
	}

}
