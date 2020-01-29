package algo_study;

import java.util.Scanner;
/*
 * @author 215
 * @since 2020. 1. 29.
 * @see 
 * @mem 256mb
 * @time 20sec
 * @caution : Be careful with the number of Magnetic
 */

public class Problem_SWEA_1220 {
	
			
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int []ans = new int[10];
		// Declare the array of answer
		
		for(int i = 0; i< 10; i++) {
			int n = scan.nextInt();
			// 100
			int [][]arr = new int[n][n];
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					arr[j][k] = scan.nextInt();
				}
			}
			// Insert Data from the Test Case
			int ret = 0;
			boolean is = false;
			
			for(int j = 0; j < n; j++) {
				is = false;
				for(int k = 0; k < n; k++) {
					if(is == false && arr[k][j] == 1) {
						// N극
						is = true;
						continue;
					}
					if(is == true && arr[k][j] == 2) {
						// S극
						ret++;
						is = false;
						continue;
					}
				}
			}
			
			ans[i] = ret;
			// get the number						
		}
		
		for(int i = 0; i< 10; i++) {
			System.out.println("#"+(i+1)+" "+ans[i]);
		}
		// Print the answer
	}

}
