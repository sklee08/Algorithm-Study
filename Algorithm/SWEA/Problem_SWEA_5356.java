package algorithm_study;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_SWEA_5356 {
	
	public static int getMax(int []arr) {
		int size = arr.length;
		int i;
		int max =0;
		for(i = 0; i<size; i++) {
			if(arr[i] >= max) {
				max = arr[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		scan.nextLine();
		int i,j,k;
		String []arr = new String[5];
		int []len = new int[5];
		String []ans = new String[tc];
		
		
		for(i = 0; i< tc; i++) {
			String answer = "";
			for(j = 0; j < 5; j++) {
				arr[j] = scan.nextLine();
				len[j] = arr[j].length();
			}
			int max = getMax(len);
			char [][] tmp = new char[5][max];
			for(char[] row : tmp) Arrays.fill(row, ' ');
			for(j = 0; j < 5; j++) {
				for(k = 0; k < len[j]; k++) {
					tmp[j][k] = arr[j].charAt(k);
				}
			}
			for(j = 0 ; j < max; j++) {
				for(k = 0; k < 5; k++) {
					answer += tmp[k][j];
				}
			}
			
			// answer 에 있는 공백 지우기 필요
			String replace = answer.replaceAll("\\s","");
			ans[i] = replace;
		}
		
		for(i = 0; i < tc; i++) {
			System.out.println("#"+(i+1)+" "+ans[i]);
		}
	}

}
