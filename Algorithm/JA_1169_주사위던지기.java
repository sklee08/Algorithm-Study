package algorithm_study;

import java.util.Arrays;
import java.util.Scanner;

public class JA_1169_주사위던지기 {

	static int[] dices;
	static int N;
	
	public static void print() {
		for(int d : dices) {
			System.out.print(d+ " ");
		}
		System.out.println();
	}
	
	public static void getPer1(int idx) {
		if(idx == N) {
			print();
			return;
		}else {
			for(int i = 1; i< 7; i++) {
				dices[idx] = i;
				getPer1(idx+1);
			}
		}
	}
	
	public static void getPer2(int idx, int before) {
		if(idx == N) {
			print();
			return;
		}else {
			for(int i = before; i < 7; i++) {
				dices[idx] = i;
				getPer2(idx+1, i);
			}
		}
	}
	
	public static void getPer3(int r, int curr, boolean[]visited) {
		if(curr == r) {
			print();
			return;
		}else {
			for(int i = 0; i < 6; i++) {		
				if(!visited[i]) {
					dices[curr] = i+1;
					visited[i] = true;
					getPer3(r,curr+1,visited);
					visited[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		dices = new int[N];
		boolean [] visited = new boolean[6];
		int M = scan.nextInt();
		
		if(M == 1) {
			getPer1(0);
		}else if(M==2) {
			getPer2(0,1);
		}else {
			getPer3(N, 0, visited);
		}
	}

}
