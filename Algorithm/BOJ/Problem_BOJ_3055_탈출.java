package algo_study;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem_BOJ_3055_탈출 {

	static int R;
	static int C;
	static char [][]arr;
	static int[]dx = {0,0,1,-1};
	static int[]dy = {1,-1,0,0};
	static Dot posD;
	static Dot posS;
	static boolean visit[][];
	
	static class Dot{
		
		private int x;
		private int y;
		private char ch;
		private int cnt;
		
		public Dot(int x, int y, char ch, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.ch = ch;
			this.cnt = cnt;
		}
		
	}
	
	public static void findDandS() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j] == 'D') {
					posD = new Dot(i,j,'D', 0);
				}
				if(arr[i][j] == 'S') {
					posS = new Dot(i,j,'S', 0);
				}
			}
		}
		
		
	}
	
	public static boolean isIn(int x, int y) {
		return (0<= x && x < R && 0 <= y && y < C);
	}
	
	public static void spreadWater() {
		int ans = 0;
		Queue<Dot> q = new LinkedList<>();
		visit = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j] == '*') {
					q.add(new Dot(i,j, '*', 0));
					//visit[i][j] = true;
				}
			}
		}
		q.add(new Dot(posS.x, posS.y, 'S', 0));
		
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int x = tmp.x;
			int y = tmp.y;
			char ch = tmp.ch;
			int cnt = tmp.cnt;
			
			if(ch == 'S') {
				if(posD.x == x && posD.y == y) {
					ans = cnt;
					break;
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(ch == 'S') {
					if(isIn(nx,ny) && (arr[nx][ny] == '.' || arr[nx][ny] == 'D')) {
						arr[nx][ny] = ch;
						q.add(new Dot(nx,ny,ch,cnt+1));		
					}
					continue;
				}				
				if(isIn(nx,ny) && arr[nx][ny] == '.') {
					arr[nx][ny] = ch;
					q.add(new Dot(nx,ny,ch,cnt+1));		
				}
			}
		}
		if(ans == 0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(ans);
		}
	} 
	
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		R = scan.nextInt();
		C = scan.nextInt();
		arr = new char[R][C];
		visit = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String tmp = scan.next();
			for(int j = 0; j<tmp.length(); j++) {
				arr[i][j] = tmp.charAt(j);
			}
		}
		// 입력 완료
		findDandS();
		// D와 S의 위치 찾기
		spreadWater();
		
	}

}
