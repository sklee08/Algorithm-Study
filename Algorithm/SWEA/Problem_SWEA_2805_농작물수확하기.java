package algo_study;

import java.util.Scanner;

public class Problem_SWEA_2805_농작물수확하기 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		for(int t = 1; t <= tc; t++) {

			int N = scan.nextInt();
			int[][]farm = new int [N][N];
			int ret = 0;
			
			for(int i = 0; i< N; i++) {
				String s = scan.next();
				for(int j = 0; j < N; j++) {
					farm[i][j] = s.charAt(j) - '0';					
				}
			}
			
			// 입력완료
			
			int mid = N /2;
			for(int i = 0; i < N; i++) {
				if(i <= mid) {
					for(int j = mid-i; j <= mid+i; j++) {
						ret += farm[i][j];
					}
				}else {
					// mid 보다 큼
					for(int j = i-mid; j <= 3*mid - i; j++) {
						ret += farm[i][j];
					}
				}
			}
			System.out.println("#" + t + " " + ret);
		}
		scan.close();
	}

}
