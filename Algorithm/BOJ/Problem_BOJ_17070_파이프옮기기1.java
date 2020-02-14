package algo_study;


import java.util.Arrays;
import java.util.Scanner;

public class Problem_BOJ_17070_파이프옮기기1 {

	static int N;
	static boolean[][] visit;
	
	static int[] dr = {0,1,1};
	static int[] dc = {1,1,0};
	static int ans;
	
	static class Dot{
		int row;
		int col;
		int dir;
		public Dot(int row, int col, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}	
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(visit[i]));
		}
		System.out.println();
	}
	
	public static boolean isIn(int row, int col) {
		return (0<= row && row < N && 0<= col && col < N);
	}
	
	public static void movePipe(Dot start) {
		if(start.row == N-1 && start.col == N-1) {
			ans++;
			return;
		}
		int dir = start.dir;
		int r = start.row;
		int c = start.col;
		// 기존 방향 dir
		switch(dir) {
		case 0:
			//가로방향
			for(int i = 0; i < 2; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(i != 1 && isIn(nr,nc) && !visit[nr][nc]) {
					movePipe(new Dot(nr,nc,i));
				}
				if(i == 1) {
					// 다음 방향 대각선
					if(isIn(nr, nc)) {
						if(!visit[nr-1][nc] && !visit[nr][nc-1] && !visit[nr][nc]) {
							movePipe(new Dot(nr,nc,i));
						}
					}
				}
			}
			break;
		case 1:
			// 대각선 방향
			for(int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(i != 1 && isIn(nr,nc) && !visit[nr][nc]) {
					movePipe(new Dot(nr,nc,i));
				}
				if(i == 1) {
					// 다음 방향 대각선
					if(isIn(nr, nc)) {
						if(!visit[nr-1][nc] && !visit[nr][nc-1] && !visit[nr][nc]) {
							movePipe(new Dot(nr,nc,i));
						}
					}
				}
			}
			break;
		case 2:
			// 세로방향
			for(int i = 1; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(i != 1 && isIn(nr,nc) && !visit[nr][nc]) {
					movePipe(new Dot(nr,nc,i));
				}
				if(i == 1) {
					// 다음 방향 대각선
					if(isIn(nr, nc)) {
						if(!visit[nr-1][nc] && !visit[nr][nc-1] && !visit[nr][nc]) {
							movePipe(new Dot(nr,nc,i));
						}
					}
				}
			}
			break;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		visit = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(scan.nextInt() == 0) {
					visit[i][j] = false;
				}else {
					// 장애물
					visit[i][j] = true;
				}
			}
		}
		
		// 입력완료
		movePipe(new Dot(0,1,0));
		
		System.out.println(ans);
		scan.close();
	}

}
