package algo_study;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Problem_BOJ_3109_빵집 {

	static int R,C, nr, nc;
	static boolean[][] arr;
	static int []dr = {-1,0,1};
	static int []dc = {1,1,1};
	static int ans;
	
	static class Dot{
		int row;
		int col;
		
		public Dot(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	public static boolean isIn(int x, int y) {
		return (0<= x && x < R && 0<= y && y < C);
	}	
	
	public static boolean makePipe(int row, int col) {
		for(int i =0; i < 3; i++) {
			nr = row + dr[i];
			nc = col + 1;
			if(0<= nr && nr < R && arr[nr][nc] == false) {
				if(nc == C-1) {
					//print();
					ans++;
					return true;
				}
				arr[nr][nc] = true;
				if(makePipe(nr,nc)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void print() {
		for(int i = 0; i < R; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		R = scan.nextInt();
		C = scan.nextInt();
		arr =new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String tmp = scan.next();
			for(int j = 0; j < C; j++) {
				if(tmp.charAt(j) == 'x') {
					arr[i][j] = true;
				}
			}
		}
		
		for(int i = 0; i < R; i++) {
			makePipe(i,0);
		}
		System.out.println(ans);
	}

}
