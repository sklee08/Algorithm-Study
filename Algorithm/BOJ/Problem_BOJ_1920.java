package algorithm_study;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


public class Problem_BOJ_1920 {

	public static boolean binarySearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		int mid = 0;
		
		while(low <= high) {
			mid = (low + high) /2;
			if(a[mid] == key) {
				return true;
			}
			
			if(a[mid] < key) {
				low = mid+1;
			}else {
				high = mid-1;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);

		int N= scan.nextInt();
		int []origin = new int[N]; 
		
		
		for(int i = 0; i < N; i++) {
			origin[i] = scan.nextInt();
		}
		
		int M = scan.nextInt();
		int []nextArr = new int[M];
		
		for(int i = 0; i< M; i++) {
			nextArr[i] = scan.nextInt();
		}
		Arrays.sort(origin);
		
		for(int i = 0; i < M; i++) {
			if(binarySearch(origin, nextArr[i])) {
				// 찾음
				System.out.println("1");
			}else {
				System.out.println("0");
			}
		}
		
		
	}

}
