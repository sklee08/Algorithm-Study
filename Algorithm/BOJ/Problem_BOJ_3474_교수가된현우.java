package algorithm_study;

import java.util.Scanner;


public class Problem_BOJ_3474 {

	public static int get_zero(int n) {
		int ret = 0;		
		if(n <=4) return ret;
		
		while(n >= 5) {
			ret += (n / 5);
			n = n / 5;
		}
		
		return ret;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int tc = scan.nextInt();
		int i;
		int []arr = new int[tc];
		int []ans = new int[tc];
		
		for(i =0; i< tc; i++) {
			arr[i] = scan.nextInt();
			ans[i] = get_zero(arr[i]);
		}
		for(i = 0; i< tc; i++) {
			System.out.println(ans[i]);
		}
		scan.close();
	}

}
