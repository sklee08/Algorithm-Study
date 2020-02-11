package algo_study;

import java.util.Scanner;

public class Problem_JA_1810_백설공주 {

	static int []arr;
	
	public static void getComb(int r, int[] temp, int current, int start) {
		if(current == r) {
			if(getSum(temp) == 100) {
				for(int i = 0; i < temp.length; i++) {
					System.out.println(temp[i]);
				}
			}
		}else {
			for(int i = start; i < arr.length; i++) {
				temp[current] = arr[i];
				getComb(r,temp,current+1, i+1);
			}
		}
	}
	
	public static int getSum(int [] numArr) {
		
		int sum = 0;
		for(int i = 0; i < numArr.length; i++) {
			sum += numArr[i];
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		arr = new int[9];
		
		for(int i = 0; i < 9; i++) {
			arr[i] = scan.nextInt();
		}
		
		getComb(7, new int[7], 0, 0);
	}

}
