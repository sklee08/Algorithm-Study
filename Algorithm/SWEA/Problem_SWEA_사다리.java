package algorithm_study;

import java.util.Scanner;

public class Problem_SWEA_ladder {

	static int []dr = {0, 1, -1};
	static int []dc = {-1, 0, 0};
	
	public static int getPath(int[][] arr) {
		int i, j;
		int next_r, next_c;	
		int index=0;
		int onMove=0; // -1 : left, 0 : center, 1 : right
		
		for(i = 0; i < 100; i++) {
			if(arr[99][i] == 2) {
				// 종료지점 index 
				index=i;
			}
		}
		
		next_r = 99;
		next_c = index;
		while(next_r != 0) {
			if(onMove<=0&&hasLeft(arr,next_r,next_c)) {
				next_c--;
				onMove=-1;
			}
			else if(onMove>=0&&hasRight(arr,next_r,next_c)) {
				next_c++;
				onMove=1;
			}
			else {
				next_r--;
				onMove=0;
			}
		}
		return next_c;
	}
	
	public static boolean hasRight(int[][] arr,int r,int c) {
		if(c>=arr.length-1) {
			return false;
		}
		if(arr[r][c+1]==0) {
			return false;
		}
		return true;
	}
	
	public static boolean hasLeft(int[][] arr,int r,int c) {
		if(c<=0) {
			return false;
		}
		if(arr[r][c-1]==0) {
			return false;
		}
		return true;
	}
		
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int []tc = new int[10];
		int []ans = new int[10];
		int [][]data= new int[100][100];
		int i, j, k;
		
		// data 배열에 저장		
		for(i = 0; i < 10; i++) {
			tc[i] = scan.nextInt();
			for(j = 0; j < 100; j++) {
				for(k =0; k<100; k++) {
					data[j][k] = scan.nextInt();					
				}
			}
			ans[i] = getPath(data);
		}
		for(i=0; i< 10; i++) {
			System.out.println("#"+(i+1)+" "+ans[i]);
		}
	}

}
