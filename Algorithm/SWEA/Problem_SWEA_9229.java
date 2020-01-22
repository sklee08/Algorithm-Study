package algorithm_study;

import java.util.Scanner;

public class Problem_SWEA_9229 {
		
	public static int getSmallest(int[]arr, int limit) {
		int size = arr.length;
		int ret = -1;
		int max = 0;
		boolean isIt = false;
		
		for(int i = 0; i< size; i++) {
			if(arr[i] == limit) {
				return limit;
			}
			if(arr[i] <= limit && max <= arr[i]) {
				max = arr[i];
				isIt = true;
			}
		}
		if(isIt == true) {
			ret = max;
		}
		
		return ret;
	}
	
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		int ans[] = new int[tc];
		int limit;
		int i;
		

		for(i = 0; i< tc; i++) {
			int numSnack = scan.nextInt();
			limit = scan.nextInt();
			int []arr = new int[numSnack];
			int []sum_arr = new int[numSnack*(numSnack-1)];
			for(int j = 0; j < numSnack; j++) {
				arr[j] = scan.nextInt();
			}
			// Input 값
			int index = 0;
			for(int j = 0; j < numSnack; j++) {
				for(int k = 0; k < numSnack; k++) {
					if(k != j) {
						sum_arr[index] = arr[k] + arr[j];
						index++;
					}
				}
			}
			ans[i] = getSmallest(sum_arr, limit);
		}
		
		for(i = 0; i< tc; i++) {
			System.out.println("#"+(i+1)+" "+ans[i]);
		}
		scan.close();
	}

}
