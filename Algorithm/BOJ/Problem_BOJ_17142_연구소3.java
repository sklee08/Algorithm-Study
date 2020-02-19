package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_17142_연구소3 {
	
	static int N,M;
	static int[][] lab;
	static int[][] copyLab;
	static int min = Integer.MAX_VALUE;
	static ArrayList<Virus> list;
	static int ans;
	static boolean[][] visit;
	
	static int[]dr = {0,0,1,-1};
	static int[]dc = {1,-1,0,0};
	
	static class Virus{
		int row;
		int col;
		int time;
		
		public Virus(int row, int col, int time) {
			super();
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
	
	public static void setVirus(int start, int depth) {
		if(depth == list.size() - M) {
			copy();
			spread();
			if(min > ans) {
				min = ans;
			}
			//ans = 0;
			return;
		}
		for(int i = start; i < list.size(); i++) {
			Virus tmp = list.get(i);
			int row = tmp.row;
			int col = tmp.col;
			lab[row][col] = -1;
			setVirus(i+1, depth+1);
			lab[row][col] = 2;
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				copyLab[i][j] = lab[i][j];
			}
		}
	}
	
	public static void spread() {
		Queue<Virus> q = new LinkedList<>();
		visit = new boolean[N][N];
		for(int i = 0; i < list.size(); i++) {
			int row = list.get(i).row;
			int col = list.get(i).col;
			if(copyLab[row][col] == 2) {
				visit[row][col] = true;
				q.offer(list.get(i));
			}
		}
		int time = 0;
		
		while(!q.isEmpty()) {
			Virus tmp = q.poll();
			int row = tmp.row;
			int col = tmp.col;
			time = tmp.time;
	
			for(int i = 0; i < 4; i++) {
				int nr = row+dr[i];
				int nc = col+dc[i];
				if(isIn(nr,nc) && !visit[nr][nc]) {
					if(copyLab[nr][nc] == 1) {
						continue;
					}
					if(copyLab[nr][nc] == -1 && !isZero()) {
						// 다음 살펴본 지역이 비활성화 바이러스 지역
						// 0이 하나도 없을 때
						continue;
					}					
					visit[nr][nc] = true;
					copyLab[nr][nc] = 2;
					q.add(new Virus(nr, nc, time+1));
				}
			}
		}
		if(isZero()) {
			ans = Integer.MAX_VALUE;
		}else {
			ans = time;
		}
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(copyLab[i]));
		}
		System.out.println();
	}
	
	public static boolean isZero() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(copyLab[i][j] == 0) {
					// 0이 한개라도 있으면
					return true;
				}
			}
		}
		// 2나 -1, 1로만 이루어지면
		return false;
	}
	
	public static boolean isIn(int r, int c) {
		return (0 <=r && r < N && 0<= c && c <N);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		StringTokenizer st = new StringTokenizer(tmp, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][N];
		copyLab = new int[N][N];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			tmp = br.readLine();
			st = new StringTokenizer(tmp, " ");
			for(int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j] == 2) {
					list.add(new Virus(i,j,0));
				}
			}				
		}
		setVirus(0, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}		
	}
}
