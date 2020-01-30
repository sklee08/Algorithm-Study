package algo_study;

import java.util.Scanner;

public class Problem_SWEA_5603_건초더미 {

	public static int getAns(int []arr) {
		int ret = 0;
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		int avg = sum / arr.length;
		
		
		for(int i = 0; i < arr.length; i++) {
			ret += Math.abs(arr[i] - avg);
		}
		return ret/2;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc= scan.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			int N = scan.nextInt();
			int ret= 0;
			int[]arr = new int[N];
			
			for(int i = 0; i < N; i++) {
				arr[i] = scan.nextInt();
			}
			ret = getAns(arr);
			
			System.out.println("#" +t+" " +ret);
		}
	}

}
