package algo_study;

import java.util.Scanner;

public class Problem_SWEA_4615 {

	static int N;
	static int[][] arr;
	static int[] dx = {1,-1, 0, 0,1,1,-1,-1};
	static int[] dy = {0, 0, 1, -1,1,-1,1,-1};
	
	public static int change(int n) {
		if(n == 2) return 1;
		else if(n ==1) return 2;
		else return -1;
	}
	
	public static boolean isIn(int x, int y) {
		return (x >= 0 && y >= 0) && (x < N && y < N); 
	}
	
	public static void adoptChange(int row, int col, int color) {
		
		for(int i = 0; i< 8; i++) {
			
			int newRow = row+dx[i];
			int newCol = col+dy[i];
			int newColor = change(color);
			boolean isColor = false;
			while(isIn(newRow, newCol) && arr[newRow][newCol] == newColor) {
				
				if(isIn(newRow+dx[i], newCol+dy[i])) {
					if(arr[newRow+dx[i]][newCol+dy[i]] == color) {
						// 다음 방향의 돌이 원래 색깔인 경우
						isColor = true;
						break;
					}	
				}
				// 다음 방향으로 좌표 이동
				newRow += dx[i];
				newCol += dy[i];
				// while 문 끝
			}
			newRow = row;
			newCol = col;
			while(isColor) {
				// 다음 방향으로 좌표 이동
				newRow += dx[i];
				newCol += dy[i];
				
				if(arr[newRow][newCol] == color) {
					break;
				}
				// 색깔 변경	
				arr[newRow][newCol] = color;						
			}			
		}
		arr[row][col] = color; // 배치
	}
	
	public static int getWhite() {
		int ret= 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 2) ret++;
			}
		}
		return ret;
	}
	
	public static int getBlack(){
		int ret= 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 1) ret++;
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		for(int i = 0; i < tc; i++) {
			N = scan.nextInt();
			arr = new int[N][N];
			arr[N/2-1][N/2-1] = 2;
			arr[N/2][N/2] = 2;
			arr[N/2 -1][N/2] = 1;
			arr[N/2][N/2-1] = 1;	
			
			int M = scan.nextInt();
			
			for(int j = 0; j<M; j++) {
			
				int a = scan.nextInt();
				int b = scan.nextInt();
				int c = scan.nextInt();
				adoptChange(b-1, a-1, c);
			}
			System.out.println("#"+(i+1)+" "+getBlack()+" "+getWhite());
		}
	}

}
