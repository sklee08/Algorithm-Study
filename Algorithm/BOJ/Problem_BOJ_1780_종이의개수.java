package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_1780_종이의개수 {
	
	static int N;
	static int one;
	static int zero;
	static int minus;
	static int[][] origin;
	
	public static boolean isAllSame(int[][]arr, int n) {
		int data = arr[0][0];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j< n; j++) {
				if(arr[i][j] != data) {
					return false;
				}
			}
		}	
		if(data == 0) {
			zero++;
		}else if(data == -1) {
			minus++;
		}else {
			one++;
		}
		return true;
	}
	
	public static void print(int[][]arr, int n) {
		for(int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
	}
	
	public static void divide(int[][] arr, int n) {
		if(n == 1) {
			int data = arr[0][0];
			if(data == 0) {
				zero++;
			}else if(data == -1) {
				minus++;
			}else {
				one++;
			}
			return;
		}else {
			if(!isAllSame(arr, n)) {
				int div = n / 3;
				for(int i = 0; i < n; i+=div) {
					for(int j = 0; j < n; j+=div) {
						int [][]newArr = new int[div][div];
						for(int k = 0; k < div; k++) {
							for(int l = 0; l < div; l++) {
								newArr[k][l] = arr[i+k][j+l];
							}
						}
						print(newArr, div);
						divide(newArr, div);
					}
				}
			}
		}		
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		origin = new int[N][N];
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			for(int j = 0; j < N; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(origin, N);
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);
	}
}
