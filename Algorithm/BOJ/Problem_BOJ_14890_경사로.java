package algo_study;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_BOJ_14890_경사로 {

	static int N;
	static int L;
	static int[][] path;
	static int answer;
	static boolean [] visit;
	
	public static void getCol() {
		for(int i = 0; i < N; i++) {
			boolean isPossible = true;
			int before = 1;
			int after = 1;
			visit = new boolean[N];
			for(int j = 0; j < N; j++) {
				if(isIn(j+1 , i)) {
					if(path[j][i] == path[j+1][i]) {
						before++;
						continue;
					}
					if(Math.abs(path[j][i] - path[j+1][i]) == 1) {
						// 차이가 1일 때
						// 앞뒤로 경사로 놓는 것이 가능한지 체크
						
						if(path[j][i] > path[j+1][i]) {
							//after
							
							if(j+L >= N) {
								isPossible = false;
								break;
							}
							visit[j+1] = true;
							for(int k = j+1; k < j+L; k++) {
								if(path[k][i] == path[k+1][i]) after++;
								//System.out.printf("%d행 %d열 %d\n",i,j, after);
								visit[k] = true;
								visit[k+1] = true;
							}
							if(after < L) {								
								isPossible = false;
								break;
							}
							after =1;
							before =1;
						}else {
							// before
							if(before < L) {
								
								isPossible = false;
								break;
							}
							boolean is = false;
							for(int k = j; k >= j-L+1; k--) {
								if(visit[k]) {
									is = true;
									break;								
								}
							}
							if(is) {
								isPossible = false;
								break;
							}
							after =1;
							before =1;
							
						}
						
					}else {
						// 차이가 1이상 일 때
						
						isPossible = false;
						break;
					}
					
				}
			}
			if(isPossible) {
				answer++;
		//		System.out.println("Answer Col is " + i);
			}
		}
	}
	
	public static void getRow() {	
		
		for(int i = 0; i < N; i++) {
			boolean isPossible = true;
			int before = 1;
			int after = 1;
			visit = new boolean[N];
			for(int j = 0; j < N; j++) {
				if(isIn(i , j+1)) {
					if(path[i][j] == path[i][j+1]) {
						before++;
						continue;
					}
					if(Math.abs(path[i][j] - path[i][j+1]) == 1) {
						// 차이가 1일 때
						// 앞뒤로 경사로 놓는 것이 가능한지 체크
						
						if(path[i][j] > path[i][j+1]) {
							//after
							
							if(j+L >= N) {
								isPossible = false;
								break;
							}
							visit[j+1] = true;
							for(int k = j+1; k < j+L; k++) {
								if(path[i][k] == path[i][k+1]) after++;
								//System.out.printf("%d행 %d열 %d\n",i,j, after);
								visit[k] = true;
								visit[k+1] = true;
							}
							if(after < L) {								
								isPossible = false;
								break;
							}
							after =1;
							before =1;
						}else {
							// before
							if(before < L) {
								
								isPossible = false;
								break;
							}
							boolean is = false;
							for(int k = j; k >= j-L+1; k--) {
								if(visit[k]) {
									is = true;
									break;								
								}
							}
							if(is) {
								isPossible = false;
								break;
							}
							after =1;
							before =1;
							
						}
						
					}else {
						// 차이가 1이상 일 때
						
						isPossible = false;
						break;
					}
					
				}
			}
			if(isPossible) {
				answer++;
			//	System.out.println("Answer Row is " + i);
			}
		}
		System.out.println(answer);
	}
	
	public static boolean isIn(int x, int y) {
		// 배열 인덱스 확인
		return (0 <= x  && x<N && 0 <= y && y < N);
	}	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		L = scan.nextInt();
		path = new int[N][N];
		visit= new boolean[N];
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				path[i][j] = scan.nextInt();
			}
		}
		
		// 입력 완료
		getCol();
		getRow();
		
	}

}
