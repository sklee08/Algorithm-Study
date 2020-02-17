package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_10972_다음순열 {
	
	static int N;
	static int[]arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		String tmp = br.readLine();
		StringTokenizer st= new StringTokenizer(tmp, " ");
		int i = 0;
		while(st.hasMoreTokens()) {
			arr[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		
		if(!nextPermutation()) {
			System.out.println(-1);
		}else {
			for(i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
		}
	}
	
	public static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static boolean nextPermutation() {
		int i;
		for(i = arr.length -2; i >= 0; i--) {
			if(arr[i] < arr[i+1]) {
				break;
			}
		}
		if(i < 0 ) return false;
		
		int j;
		for(j = arr.length - 1; j > i; j--) {
			if(arr[i] < arr[j]) break;
		}
		
		swap(i,j);
		
		for(int a = i+1, b = arr.length -1; a < b; a++, b--) {
			swap(a,b);
		}
		return true;
	}

}
