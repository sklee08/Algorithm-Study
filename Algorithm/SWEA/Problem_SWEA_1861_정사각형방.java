package algo_study;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem_SWEA_1861_정사각형방 {
	
	static int[]dx = {0,0,1,-1};
	static int[]dy = {1,-1,0,0};
	static int N;
	static int [][]arr;
	static int max;
	static Dot ansDot;
	static ArrayList<Dot> list;
	
	static class Dot{
		int r;
		int c;
		int cnt;
		public Dot(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	
	public static Dot moveRoom(Dot start) {
		Queue<Dot> q = new LinkedList<>();
		q.add(start);
		int cnt = 0;
		int r = 0;
		int c = 0;
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			r = tmp.r;
			c = tmp.c;
			cnt = tmp.cnt;
			int origin = arr[r][c];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];
				if(isIn(nr,nc) && arr[nr][nc] == origin + 1) {
					q.add(new Dot(nr,nc,cnt+1));
				}
			}
		}
		if(max < cnt) {
			max = cnt;			
		}
		return new Dot(start.r,start.c,cnt);
	}
	
	public static boolean isIn(int x, int y) {
		return (0 <= x && x < N && 0 <= y && y < N);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			// test case
			N = scan.nextInt();
			arr = new int[N][N];
			max = 0;
			list = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j]= scan.nextInt();
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					Dot tmp = moveRoom(new Dot(i,j,1));
					list.add(tmp);					
				}
			}
			int ans = Integer.MAX_VALUE;
			for(Dot d : list) {
				if(d.cnt == max) {
					if(arr[d.r][d.c] < ans) {
						ans = arr[d.r][d.c];
					}
				}
			}
			
			System.out.print("#"+t+ " " + ans + " ");
			System.out.println(max);
		}
	}
}
