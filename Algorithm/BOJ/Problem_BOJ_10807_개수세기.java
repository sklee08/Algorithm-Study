package algorithm_study;
import java.util.Scanner;

public class Problem_BOJ_10807 { 
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int[]arr = new int[N];
		for(int i =0; i< N; i++) {
			arr[i] = scan.nextInt();
		}
		int find = scan.nextInt();
		int ret = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == find) {
				ret++;
			}
		}
		System.out.println(ret);
	}

}
