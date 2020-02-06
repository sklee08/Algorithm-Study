package algo_study;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_BOJ_14503_로봇청소기 {

	static int N,M,r,c,d;
	static int[][] map;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int answer;
	
	public static void clean(int x, int y, int dir) {
		// 청소 시작
		int robotDir = dir;
		// 로봇이 보는 방향
		int left;
		// 로봇의 왼쪽 방향
		if(dir - 1 < 0) {
			left = 3;
		}else {
			left = dir - 1;
		}
		map[x][y] = -1;
		answer++;
		// 청소완료
		int nx = x;
		int ny = y;
		// 왼쪽 방향 설정
		while(true) {
			//print();
			if(isNoMoreClean(nx, ny)) {
				// 더이상 청소할 곳이없을 때
				if(map[nx+dx[getBack(robotDir)]][ny+dy[getBack(robotDir)]] == 1) {
					//System.out.printf("%d %d %d\n", nx, ny, robotDir);
					break;
				}else {
					// 후진
					nx +=dx[getBack(robotDir)];
					ny +=dy[getBack(robotDir)];
					continue;
				}
			}			
			
			if(map[nx+dx[left]][ny+dy[left]] == 0) {
				// 왼쪽이 청소되지 않은 경우
				nx += dx[left];
				ny += dy[left];
				map[nx][ny] = -1;
				answer++;
				robotDir = left;
				if(left == 0) {
					left = 3;
				}else {
					left -= 1;
				}
			}else {
				// 청소된 경우
				robotDir = left;
				if(left == 0) {
					left = 3;
				}else {
					left -= 1;
				}
				
			}
			// 방향 왼쪽으로			
		}
		System.out.println(answer);
	}
	
	public static int getBack(int n) {
		if(n < 2)
			return n+2;
		else {
			return n - 2;
		}
	}
	
	public static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
	public static boolean isNoMoreClean(int x, int y) {
		// 해당 지점이 더이상 청소할 공간이 있는지 없는지 판단하는 함수
		
		boolean isStop = true;
		for(int i = 0; i < 4; i++) {
			// 4방향 탐색
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(map[nx][ny] == 1 || map[nx][ny] == -1) {
				//벽이거나 청소완료
				continue;
			}else {
				isStop = false;
				break;
			}
		}		
		return isStop;
	}
	
	public static boolean isIn(int x, int y) {
		return (0 <= x && x <N-1 && 0 <= y && y < M-1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M= scan.nextInt();
		map = new int[N][M];
		
		r = scan.nextInt();
		c = scan.nextInt();
		d = scan.nextInt();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		
		// 입력 완료
		clean(r,c,d);
		
	}

}
