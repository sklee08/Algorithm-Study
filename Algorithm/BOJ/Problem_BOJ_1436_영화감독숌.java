package algo_study;

import java.util.Scanner;

public class Problem_BOJ_1436 {

	public static void getSix(int n) {
		int num = 666;
		int cnt = 0;
		
		while(true) {
			
			if(isContain(Integer.toString(num))) {
				cnt++;
			}
			if(cnt == n) break;
			num++;
		}
		System.out.println(num);
	}
	
	public static boolean isContain (String num) {
		return num.contains("666");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		
		getSix(N);
	}

}
