package algo_study;

import java.util.Scanner;

public class Problem_BOJ_14500_테트로미노 {

	static int N;
	static int M;
	static int [][]arr;
	static Dot []tetromino = new Dot[4];
	static int max = Integer.MIN_VALUE;
	static boolean[][] visited;
	
	static int []dx = {0,-1,0,1};
	static int []dy = {1,0,-1,0};
	
	
	static class Dot{
		private int x;
		private int y;
		
		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		@Override
		public String toString() {
			return "Dot [x=" + x + ", y=" + y + "]";
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}		
	}
	
	public static void print() {
		for(int i = 0; i < 4; i++) {
			System.out.println(tetromino[i].toString());
		}
	}
	
	public static void initTetro() {
		for(int i = 0; i < 4; i++) {
			tetromino[i] = new Dot(0,0);
		}
	}
	
	public static void clearVisit() {
		for(int i = 0; i < N+6; i++) {
			for(int j = 0; j < M+6; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static void getFword(int x, int y) {
		int [] dr = {0,-1,0,1};
		int [] dc = {1,0,-1,0};
		
		
		
		int nx, ny;
		for(int rn = 0; rn < 4; rn++) {
			int sum = arr[x][y];			
			for(int i = 0; i < 3; i++) {
				if(i + rn >= 4) {
					nx = x + dr[i+rn-4];
					ny = y + dc[i+rn-4];
				}else {
					nx = x + dr[i+rn];
					ny = y + dc[i+rn];
				}
				if(!isIn(nx,ny)) {
					// 한개라도 범위를 벗어나버리면
					return;
				}else {
					sum += arr[nx][ny];
				}
			}
			if(sum >= max)
				max = sum;
		}	
		
	}
	
	public static void getTetro(int x, int y, int depth) {
		// 테트로미노 좌표값 구하는 함수
		if(depth == 3) {
			if(!visited[x][y]) {
				tetromino[depth].setX(x);
				tetromino[depth].setY(y);
				int tmp = getNum();
				if(tmp >= max) {
					max = tmp;
				}				
				//print();
				//System.out.println();
			}			
			return;
		}else {
			visited[x][y] = true;
			tetromino[depth].setX(x);
			tetromino[depth].setY(y);
			
			// 4방 탐색
			for(int i = 0; i < 4; i++) {
				int nx = x +dx[i];
				int ny = y +dy[i];
				
				if(isIn(nx, ny)) {
					if(!visited[nx][ny]) {
						//visited[nx][ny] = true;
						getTetro(nx, ny, depth+1);
						//visited[nx][ny] = false;
					}
				}
			}
			visited[x][y] = false;
		}
		
	}
	
	public static void clearTetro() {
		for(int i = 0; i < 4; i++) {
			tetromino[i].setX(0);
			tetromino[i].setY(0);
		}
	}
	

	
	public static void getAllNum() {
		for(int i = 0; i < N+6; i++) {
			for(int j = 0; j < M+6; j++) {
				getTetro(i,j,0);
			//	clearVisit();
				clearTetro();
				getFword(i, j);
			}
		}
	}
	
	
	public static int getNum() {
		// 해당 테트로미노 좌표값으로 이루어진 합구하는 함수
		int sum = 0;
		for(int i =0; i <4; i++) {
			sum += arr[tetromino[i].getX()][tetromino[i].getY()];
		}
		return sum;
	}
	
	public static boolean isIn(int x, int y) {
		// 범위 안에 있는지 체크하는 함수
		return (0<= x && x < N+6 && 0 <= y && y < M+6);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		arr = new int[N+6][M+6];
		visited = new boolean[N+6][M+6];
		
		for(int i = 3; i < N+3; i++) {
			for(int j = 3; j < M+3; j++) {
				arr[i][j] = scan.nextInt();
			}
		}
		initTetro();
		getAllNum();
		
		System.out.println(max);
		// 입력완료
		
		
	}

}
