package algo_study;

import java.util.Scanner;

public class Problem_BOJ_15683_감시 {

	static int N;
	static int M;
	static int[][] map;
	static int[][] copyMap;
	static int rotateNum;
	static Camera[] rotate;
	static int[] fourNum;
	
	static int []dx = {0,-1,0,1};
	static int []dy = {1,0,-1,0};

	static class Camera{
		private int camera;
		private int num;
		
		public Camera(int camera, int num) {
			super();
			this.camera = camera;
			this.num = num;
		}

		public int getCamera() {
			return camera;
		}

		public void setCamera(int camera) {
			this.camera = camera;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
		public void found() {
			this.num++;
		}
	}
	
	public static int getZeroSpace() {
		// 0인 지점 개수 리턴
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j ++) {
				if(copyMap[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
	
	public static boolean isIn(int x, int y) {
		// 범위 안에 있는지 체크하는 함수
		return (0<= x && x < N && 0 <= y && y < M);
	}
	
	public static void getRotateNum() {
		// 카메라 개수 세기 및 4진수 숫자 만들기
		int cnt = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && map[i][j] != 6) {
					// 0, 6이 아닌 경우
					cnt++;
					
				}
			}
		}
		rotate = new Camera[cnt];
		
		int idx = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && map[i][j] != 6) {
					// 0, 6이 아닌 경우
					rotate[idx] = new Camera(map[i][j], 0);
					idx++;
				}
			}
		}
		
		// 4진수 배열 생성
		rotateNum = (int)Math.pow(4, cnt);
		// 회전수 구하기
	}
	
	public static void getFourNum(int n) {
		// n을 4진수로 변경하여주는 함수
		int idx = rotate.length - 1;
		while(n > 0) {
			rotate[idx].setNum(n%4);
			idx--;
			n /=4;
		}
	}
	
	public static void securityCamera() {
		// 감시 부분 -1로 바꾸는 함수
		int min = Integer.MAX_VALUE;
		getRotateNum();
		
		for(int k = 0; k < rotateNum; k++) {
			//카메라 갯수 승 만큼 회전
			getFourNum(k);
			int idx = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0 && map[i][j] != 6) {
						// 카메라 발견						
						spread(i,j,rotate[idx].getNum(), map[i][j]);
						idx++;
						// 감시 시작
					}
					
				}
			}
			int zero = getZeroSpace();
			if(zero <= min) {
				min = zero;
			}
			
			//print();			
			copy();
		}
		System.out.println(min);
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(copyMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void spread(int x, int y, int rn, int cn) {		
		// 감시 가능한 부분을 -1로 바꾸는 함수
		int nx, ny;
		switch(cn) {
		case 1:
			nx = x + dx[0 + rn];
			ny = y + dy[0 + rn];
			while(isIn(nx, ny) && copyMap[nx][ny] != 6) {
				if(copyMap[nx][ny] == 0) {
					copyMap[nx][ny] = -1;
				}				
				nx +=dx[0 + rn];
				ny +=dy[0 + rn];
			}
			break;
		case 2:
			for(int i =0; i < 2; i ++) {
				if(i*2 + rn >= 4) {
					nx = x + dx[i*2 + rn - 4];
					ny = y + dy[i*2 + rn - 4];
				}else {
					nx = x + dx[i*2 + rn];
					ny = y + dy[i*2 + rn];
				}
				
				while(isIn(nx, ny) && copyMap[nx][ny] != 6) {
					if(copyMap[nx][ny] == 0) {
						copyMap[nx][ny] = -1;
					}
					if(i*2 + rn >= 4) {
						nx +=dx[i*2 + rn - 4];
						ny +=dy[i*2 + rn - 4];
					}else {
						nx +=dx[i*2 + rn];
						ny +=dy[i*2 + rn];
					}
				}
			}
			break;
		case 3:
			for(int i =0; i < 2; i ++) {
				if(i + rn == 4) {
					nx = x + dx[0];
					ny = y + dy[0];
				}else {
					nx = x + dx[i + rn];
					ny = y + dy[i + rn];
				}				
				while(isIn(nx, ny) && copyMap[nx][ny] != 6) {
					if(copyMap[nx][ny] == 0) {
						copyMap[nx][ny] = -1;
					}
					if(i + rn == 4) {
						nx +=dx[0];
						ny +=dy[0];
					}else {
						nx+=dx[i + rn];
						ny+=dy[i + rn];
					}
				}
			}
			break;
		case 4:
			for(int i =0; i < 3; i ++) {
				if(i + rn >= 4) {
					nx = x + dx[i+rn-4];
					ny = y + dy[i+rn-4];
				}else {
					nx = x + dx[i + rn];
					ny = y + dy[i + rn];
				}	
				
				while(isIn(nx, ny) && copyMap[nx][ny] != 6) {
					if(copyMap[nx][ny] == 0) {
						copyMap[nx][ny] = -1;
					}
					if(i + rn >= 4) {
						nx+=dx[i+rn-4];
						ny+=dy[i+rn-4];
					}else {
						nx+=dx[i + rn];
						ny+=dy[i + rn];
					}
				}
			}
			break;
		case 5:
			for(int i = 0; i< 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				while(isIn(nx, ny) && copyMap[nx][ny] != 6) {
					if(copyMap[nx][ny] == 0) {
						copyMap[nx][ny] = -1;
					}
					nx+=dx[i];
					ny+=dy[i];
				}
			}
			break;
		}		
	}
	
	public static void copy() {
		// copyMap으로 원본 데이터를 카피하는 함수
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		
		// 입력 완료
		copy();
		securityCamera();
		
	}

}
