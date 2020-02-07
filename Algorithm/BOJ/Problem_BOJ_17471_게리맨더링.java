package 정올;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_BOJ_17471_게리맨더링 {

	static int N;
	static int[] population;
	static ArrayList<Integer>[] list;
	static int sum;
	static int []sumBol;
	
	
	static int diff = Integer.MAX_VALUE;
	static boolean[] comb;
	
	
	
	public static void getDiff() {
		sum = Math.abs(sumBol[0] - sumBol[1]);
		if(sum <= diff) {
			diff = sum;
		}
	}
	
	public static boolean isConnected(boolean flag) {
		// comb 배열에 0번 인덱스는 무시하고 1번 부터 N까지 true 와 false
		//  true 부터 적용
				
		boolean[] visited = new boolean[N+1];
		Queue<Integer> district = new LinkedList<>();
		for(int i = 1; i< N+1; i++) {
			if(comb[i] == flag) {
				district.add(i);
				visited[i] = true;
				break;
			}
		}
		
		while(!district.isEmpty()) {
			int now = district.poll();
			for(int i = 1; i < N+1; i++) {
				if(visited[i])
					continue;
				if(flag != comb[i])
					continue;
				if(!list[i].contains(now))
					continue;
				district.add(i);
				visited[i] = true;
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			if(comb[i] != flag) {
				continue;
			}
			if(!visited[i])
				return false;
		}
		if(flag) {
			for(int i = 1; i< N+1; i++) {
				if(comb[i] == flag) {
					sumBol[0] += population[i];
				}
			}			
		}else {
			for(int i = 1; i< N+1; i++) {
				if(comb[i] == flag) {
					sumBol[1] += population[i];
				}
			}
		}
		return true;
	}	

	public static void getComb(boolean[]visited, int start, int n, int r) {
		if(r == 0) {
			sumBol = new int[2];
			if(isConnected(true) && isConnected(false)) {
				// 모두 연결				
				getDiff();
			}
			return;
		}else {
			for(int i = start; i <= n; i++) {
				visited[i] = true;
				getComb(visited, i+1, n, r-1);
				visited[i] = false;
			}
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		population = new int[N+1];
		list = new ArrayList[N+1];
		comb = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= N; i++) {
			population[i] = scan.nextInt();
		}
		for(int i = 1; i<= N; i++) {
			int tmp = scan.nextInt();
			int []tmpArr = new int[tmp];
			for(int j = 0; j < tmp; j++) {
				tmpArr[j] = scan.nextInt();
			}
			for(int j = 0; j < tmp; j++) {
				list[i].add(tmpArr[j]);
				list[tmpArr[j]].add(i);
			}
		}
		// 입력 완료
		
		for(int i = 1; i <= N-1; i++) {
			getComb(comb,1, N,i);
		}
		if(diff == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(diff);
		}
		
		
		
	}

}
