package algo_study;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Problem_BOJ_7576_토마토 {

	static int N,M;
	static boolean[][] tomato;
	static ArrayList<Dot> list;
	static int ans;
	static int []dr = {0,0,1,-1};
	static int []dc = {1,-1,0,0};
	
	static class Dot{
		int row;
		int col;
		int cnt;
		public Dot(int row, int col, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		M = scan.nextInt();
		N = scan.nextInt();
		tomato = new boolean[N][M];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int tmp = scan.nextInt();
				if(tmp != 0) {
					tomato[i][j] = true;
					if(tmp == 1) list.add(new Dot(i,j,0));					
				}
			}
		}
		
		bfs();
		
		outer : for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!tomato[i][j]) {
					ans = -1;
					break outer;
				}
			}
		}
		
		System.out.println(ans);
		// 입력완료
		
	}
	
	public static void bfs() {
		Queue<Dot> q = new LinkedList<>();
		boolean [][] visit = new boolean[N][M];
		for(int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
			visit[list.get(i).row][list.get(i).col] = true;
		}
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int r = tmp.row;
			int c = tmp.col;
			int cnt = tmp.cnt;
			ans = cnt;
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(isIn(nr,nc) && !tomato[nr][nc]) {
					tomato[nr][nc] = true;
					q.add(new Dot(nr,nc,cnt+1));
					visit[nr][nc] = true;
				}
			}
		}
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(tomato[i]));
		}
		System.out.println();
	}
	
	public static boolean isIn(int r, int c) {
		return (0<= r && r < N && 0<= c && c < M);
	}

}
