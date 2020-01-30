package algo_study;

import java.util.Scanner;

public class Problem_SWEA_2001_파리퇴치 {

	static int[]dx = {0,0,1,1};
	static int[]dy = {0,1,0,1};
	
	public static int getMax(int[][]arr) {
		int max = 0;
		for(int i =0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				if(arr[i][j] >= max) {
					max = arr[i][j];
				}
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int tc = scan.nextInt();
		
		for(int t =1; t <=tc; t++) {
			// test case
			
			int N = scan.nextInt();
			int[][]arr = new int[N][N];
			int M = scan.nextInt();
			int newIndex = N-M +1;
			int [][]newArr = new int[newIndex][newIndex]; 
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = scan.nextInt();
				}
			}
			
			for(int i = 0; i < newIndex; i++) {
				for(int j = 0; j< newIndex; j++) {
					for(int k = i; k < i+M; k++) {
						for(int l = j; l < j+M; l++) {
							newArr[i][j] += arr[k][l];
						}
					}
				}
			}
			
			System.out.println("#"+t+" "+getMax(newArr));
		}

	}

}
