package algo_study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem_BOJ_15686_치킨배달 {
	
	static int N;
	static int M;
	static int dist;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static boolean [][] visit;
	static Queue<Dot> q;
	static Queue<Dot> selected;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static class Dot{
		int x;
		int y;
		int cnt;
		public Dot(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}
	

	public static void setChicken(int depth, int start) {
		if(depth == M) {			
			int sum = bfs();
			if(sum < min) {
				min = sum;
			}
			
			visit = new boolean[N][N];
			return;
		}
		
		for(int i = start; i < N*N; i++) {
			int x = i / N;
			int y = i % N;
			if(map[x][y] == 2) {
				map[x][y] = 3;
				visit[x][y] = true;
				setChicken(depth+1, i+1);				
				map[x][y] = 2;
			}
		}
	}
		
	public static int bfs() {
		int answer = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] == 3) {
					q.add(new Dot(i,j,0));
				}
			}
		}
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			int x = tmp.x;
			int y = tmp.y;
			int cnt = tmp.cnt;
			
			if(map[x][y] == 1) {
				visit[x][y] = true;
				answer += cnt;
			}
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isIn(nx,ny) && !visit[nx][ny]) {
					q.add(new Dot(nx,ny,cnt+1));
					visit[nx][ny] = true;
				}
			}	
			
		}
		return answer;
	}

	public static boolean isIn(int x, int y) {
		return (0<=x && x < N && 0 <= y && y < N);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		map = new int[N][N];
		visit = new boolean[N][N];
		q = new LinkedList<>();
		selected = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		// 입력 완료
		setChicken(0,0);
		System.out.println(min);
	}
}
