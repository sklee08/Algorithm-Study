package algorithm_study;

import java.util.Scanner;

public class Problem_SWEA_1954 {

	public static void drawRect(int n) {
		int i,j;
		int [][] arr= new int[n][n];
		int next_r = 0, next_c = 0;
		int data = 1;
		
		if(n % 2==1) {
			// 홀수
			arr[n /2][n/2] = n*n;
			while(next_r != n / 2) {
				for(i = 0; i < n - (next_r*2)-1; i++) {
					arr[next_r][next_r+i] = data+i;
					arr[next_r+i][n-1-next_r] = data +i+(n-1-next_r*2);
					arr[n-1-next_r][n-1-next_r-i] = data +i+2*(n-1-next_r*2);
					arr[n-1-next_r-i][next_r] = data +i+3*(n-1-next_r*2);
					next_c =data +i+3*(n-1-next_r*2) +1;
				}
				data = next_c;
				
				next_r++;
			}
		}else {
			// 짝수
			while(next_r != n / 2) {
				for(i = 0; i < n - (next_r*2)-1; i++) {
					arr[next_r][next_r+i] = data+i;
					arr[next_r+i][n-1-next_r] = data +i+(n-1-next_r*2);
					arr[n-1-next_r][n-1-next_r-i] = data +i+2*(n-1-next_r*2);
					arr[n-1-next_r-i][next_r] = data +i+3*(n-1-next_r*2);
					next_c =data +i+3*(n-1-next_r*2) +1;
				}
				data = next_c;
				
				next_r++;
			}
			
		}
		for(i = 0; i< n; i++) {
			for(j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		int[] test = new int[tc];
		int i;
		for(i = 0; i < tc; i++) {
			test[i] = scan.nextInt();
		}
		for(i = 0; i < tc; i++) {
			System.out.println("#"+(i+1));
			drawRect(test[i]);
		}
		
		
	}

}
