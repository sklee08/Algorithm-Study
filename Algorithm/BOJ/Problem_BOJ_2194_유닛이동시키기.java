package algo_study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Problem_BOJ_2194_유닛이동시키기 {

	static int N, M, A, B, K;
	static Dot start, end;
	static boolean[][] visit;
	static boolean[][] obs;
	
	static int[]dr = {0,0,1,-1};
	static int[]dc = {1,-1,0,0};
	
	public static class Dot{
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
	
	public static boolean isIn(int row, int col) {
		return (0 <= row && row+A-1 < N && 0 <= col && col+B-1 < M);
	}
	
	public static boolean isObstacle(int row, int col) {
		for(int i = row; i< row+A; i++) {
			for(int j = col; j < col+B; j++) {
				if(obs[i][j]) return true;
			}
		}
		
		return false;
	}
	
	public static void moveUnit() {
		Queue<Dot> q = new LinkedList<>();
		visit[start.row][start.col] = true;
		q.add(start);
		int ans = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int row = tmp.row;
			int col = tmp.col;
			int cnt = tmp.cnt;
			if(row == end.row && col == end.col) {
				ans = cnt;
				break;
			}			
			for(int i = 0; i < 4; i++) {
				int nr = row +dr[i];
				int nc = col +dc[i];
				if(isIn(nr, nc) && !visit[nr][nc] && !isObstacle(nr, nc)) {
					visit[nr][nc] = true;
					q.add(new Dot(nr,nc,cnt+1));
				}
			}
		}
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		visit = new boolean[N][M];
		A = scan.nextInt();
		B = scan.nextInt();
		K = scan.nextInt();
		obs = new boolean[N][M];
		for(int i = 0; i < K; i++) {
			obs[scan.nextInt() - 1][scan.nextInt() - 1] = true;
		}
		start = new Dot(scan.nextInt()-1, scan.nextInt()-1, 0);
		end = new Dot(scan.nextInt()-1, scan.nextInt()-1, 0);
		// 입력 완료
		
		moveUnit();
	}

}
