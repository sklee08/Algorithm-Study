package algo_study;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_BOJ_14502 {

	static int[][] lab;
	static int[][] copyLab;
	
	static Queue<Where> virusList = new LinkedList<Where>();
	
	static int max;
	static int N;
	static int M;
	static int[]dx = {0,0,1,-1};
	static int[]dy = {1,-1,0,0};

	
	public static int getZero(int [][]arr) {
		// 0인 지점 개수 구하기 
		int ret = 0;
		for(int i = 0; i < N; i++) {
			for(int j =0; j < M; j++) {
				if(arr[i][j] == 0) ret++;
			}
		}
		return ret;
	}
	
	public static void setWall(int start, int depth) {
		// 벽 세우기
		if(depth == 3) {
			copy();
			
			for(Where wh : virusList) {
				spreadVirus(wh.getX(), wh.getY());
			}
			
			max = Math.max(max, getZero(copyLab));
			
			return;
		}
		
		for(int i = 0; i < N*M; i++) {
			int x = i / M;
			int y = i % M;
			
			if(lab[x][y] == 0) {
				lab[x][y] = 1;
				setWall(start+1, depth+1);
				lab[x][y] = 0;
			}
		}
		
	}
	
	public static void spreadVirus(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >=0 && nx < N && ny >=0 && ny < M) {
				if(copyLab[nx][ny] == 0) {
					copyLab[nx][ny] = 2;
					spreadVirus(nx, ny);
				}
			}
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copyLab[i][j] = lab[i][j];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		lab = new int[N][M];
		copyLab = new int[N][M];
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				lab[i][j] = scan.nextInt();
				if(lab[i][j] == 2) {
					virusList.add(new Where(i,j));
				}
			}
		}
		// 입력 완료
		
			
		// 복사 완료
		max = Integer.MIN_VALUE;
		
		setWall(0,0);
		System.out.println(max);
		scan.close();
	}

}

class Where{
	
	private int x;
	private int y;
	
	public Where(int x, int y) {
		this.x = x;
		this.y = y;
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
	
	
}
