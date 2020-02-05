package algo_study;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_BOJ_17144_미세먼지 {
	
	static int[][]arr;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	// 위쪽 공기청정기 방향 
	static int[]rx1 = {0,-1, 0, 1};
	static int[]ry1 = {1, 0, -1, 0};
	
	// 아래쪽 공기청정기 방향
	static int[]rx2 = {0,1,0,-1};
	static int[]ry2 = {1,0,-1,0};
	
	static int R;
	static int C;
	static int T;
	static boolean[][] visited;	// 방문 체크 배열
	static int[][] howSpread;	// 퍼지는 양 체크하는 배열
	static Dot[] ap;
	
	static class Dot{
		private int x;
		private int y;
		
		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		R = scan.nextInt();
		C = scan.nextInt();
		T = scan.nextInt();
		arr = new int[R][C];
		visited = new boolean[R][C];
		howSpread = new int[R][C];
		ap = new Dot[2];
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				arr[i][j] = scan.nextInt();
			}
		}
		// 입력 완료
			
	
		getAP();
		// 공기청정기 위치 구하기
		
		for(int i = 0; i < T; i++) {
			
			// 미세먼지 퍼뜨리기
			howSpreadAdopt();
			arrAdopt();
			adoptChange();
			
			// 공기청정기 작동
			rotate();

			// 퍼짐 정도 배열 초기화
			howSpreadClear();
		}
		
		System.out.println(getDustAll());
		
	}
	
	public static void howSpreadClear() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				howSpread[i][j] = 0;
			}
		}
	}
	
	// 프린트 함수
	public static void print() {
		for(int i = 0; i < R; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
	
	public static void getAP() {
		int idx = 0;
		for(int i = 0; i < R; i ++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j] == -1) {
					ap[idx] = new Dot(i,j);
					idx++;
				}					
			}
		}
	}
	
	// 공기청정기 1초 진행
	public static void rotate() {
		// ap[0] 이 위쪽 공기청정기
		// rx1, ry1
		int dir = 0;
		int origin = 0;
		int nx = ap[0].x + rx1[dir];
		int ny = ap[0].y + ry1[dir];
		origin = swap(origin, nx, ny);
		while(!(nx == ap[0].x && ny == ap[0].y)) {
			// 자기자신으로 도착할 때 까지 반복	
			if(isIn(nx + rx1[dir],ny + ry1[dir])) {
				// 범위 안에 있음
				nx += rx1[dir];
				ny += ry1[dir];
				origin = swap(origin, nx, ny);
			}else {
				// 다음 놈이 배열범위 밖임
				dir++;
				// 방향 변경
			}
		}
		
		// ap[1] 이 아래쪽 공기청정기
		// rx2, ry2
		
		dir = 0;
		origin = 0;
		nx = ap[1].x + rx2[dir];
		ny = ap[1].y + ry2[dir];
		origin = swap(origin, nx, ny);
		while(!(nx == ap[0].x && ny == ap[0].y)) {
			// 자기자신으로 도착할 때 까지 반복	
			if(isIn(nx + rx2[dir],ny + ry2[dir])) {
				// 범위 안에 있음
				nx += rx2[dir];
				ny += ry2[dir];
				origin = swap(origin, nx, ny);
			}else {
				// 다음 놈이 배열범위 밖임
				dir++;
				// 방향 변경
			}
		}
		
		arr[ap[0].x][ap[0].y] = -1;
		arr[ap[1].x][ap[1].y] = -1;
	}
	
	//(x,y)번째 인덱스 배열에 있는 값 swap
	public static int swap(int ret, int x, int y) {
		int tmp;
		
		tmp = ret;
		ret = arr[x][y];
		arr[x][y] = tmp;
		
		return ret;
	}
	
	// 전체 미세먼지 양 구하는 함수
	public static int getDustAll() {
		int sum = 0;
		for(int i = 0; i < R; i ++) {
			for(int j =0; j <C; j++) {
				if(arr[i][j] != 0 && arr[i][j] != -1) {
					sum += arr[i][j];
				}
			}
		}
		return sum;
	}
	
	// arr 배열 변형
	public static void arrAdopt() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				// 공기청정기 지역이면 continue
				if(arr[i][j] == -1) continue;				
				// 4방 탐색 시작
				int cnt = 0;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(isIn(nx, ny) && arr[nx][ny] != -1) {
						// 안에 있음
						// 공기청정기 부분이 아님
						cnt++;
					}
				}
				int div = arr[i][j] / 5;
				arr[i][j] -= (div * cnt);
			}
		}
	}
	
	// howSpread 배열 변형
	public static void howSpreadAdopt() {
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j] == -1) continue;
				
				// 4방 탐색 시작
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(isIn(nx, ny) && arr[nx][ny] != -1) {
						// 안에 있음
						// 공기청정기 부분이 아님
						// 탐색한 부분에 적용
						howSpread[nx][ny] += (arr[i][j] / 5);
					}
				}
			}
		}
	}
	
	public static void adoptChange() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				arr[i][j] += howSpread[i][j];
			}
		}
	}
	
	public static boolean isIn(int x, int y) {
		return (0 <= x && 0<= y && x < R && y < C);
	}
}
