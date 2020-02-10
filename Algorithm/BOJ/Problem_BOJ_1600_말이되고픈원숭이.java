package algo_study;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem_BOJ_1600_말이되고픈원숭이 {

	static int K;
	static int W;
	static int H;
	static int [][]path;
	static int []dx = {0,0,1,-1,1,2,2,1,-2,-1,-2,-1};
	static int []dy = {1,-1,0,0,2,1,-1,-2,-1,-2,1,2};
	
	static boolean map[][];
	static boolean visit[][][];
	
	static int min = Integer.MAX_VALUE;
	
	static class Dot{
		
		private int x;
		private int y;
		private int cnt;
		private int horse;
		
		public Dot(int x, int y, int cnt, int horse) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.horse = horse;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getCnt() {
			return cnt;
		}
		public void setCnt(int cnt) {
			this.cnt = cnt;
		}
		public int getHorse() {
			return horse;
		}
		public void setHorse(int horse) {
			this.horse = horse;
		}
		
	}
	
	public static void BFS() {
		Dot next = new Dot(0,0,0,0);
		Queue<Dot> queue = new LinkedList<>();
		visit[0][0][0] = true;
		
		queue.add(next);
		while(!queue.isEmpty()) {
			next = queue.poll();
			int x = next.getX();
			int y = next.getY();
			int horse = next.getHorse();
			if(x == H-1 && y == W-1) {
				System.out.println(next.getCnt());
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				if(isIn(x+dx[i], y+dy[i]) && !map[x+dx[i]][y+dy[i]] && !visit[x+dx[i]][y+dy[i]][horse]) {
					queue.add(new Dot(x+dx[i], y+dy[i], next.getCnt()+1, horse));
					visit[x+dx[i]][y+dy[i]][horse] = true;
				}
			}
			if(horse == K) continue;
			
			for(int i = 4; i < dx.length; i++) {
				if(isIn(x+dx[i], y+dy[i]) && !map[x+dx[i]][y+dy[i]] && !visit[x+dx[i]][y+dy[i]][horse+1]) {
					queue.add(new Dot(x+dx[i], y+dy[i], next.getCnt()+1, horse+1));
					visit[x+dx[i]][y+dy[i]][horse+1] = true;
				}
			}
		}
			
		
		System.out.println(-1);
	}
	
	public static boolean isIn(int x, int y) {
		return 0<=x && x < H && 0 <= y && y <W;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		K = scan.nextInt();
		W = scan.nextInt();
		H = scan.nextInt();
		
		visit = new boolean[H][W][K+1];
		map = new boolean[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(scan.nextInt() != 0) {
					map[i][j] = true;
					// true이면 방문함 혹은 장애물
					// false 이면 방문안함
				}
			}
		}
		
	
		
		// 입력 완료
		BFS();
		
		
	}

}
