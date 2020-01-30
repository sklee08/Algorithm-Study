package algo_study;

import java.util.Scanner;

public class Problem_SWEA_2005_파스칼삼각형 {

	public static void drawPascal(int n) {
		int [][]arr= new int[n][n];
		arr[0][0]=1;
		if(n >= 2) {
			arr[1][0] = 1;
			arr[1][1] = 1;
		}
		for(int i = 2; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(j == 0) {
					arr[i][j] = 1;
				}else {
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j];					
				}
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(arr[i][j] != 0) {
					System.out.printf("%d ",arr[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		for(int t = 1; t <= tc; t++) {
			int n = scan.nextInt();
			
			System.out.println("#"+t);
			drawPascal(n);
		}
	}

}
