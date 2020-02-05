package algo_study;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Problem_BOJ_11502 {
	
	static List<Integer> list;
	static Queue<Integer> q;
	static int N;
	static int sum = 0;
	static boolean isEnd = false;

	public static void getPrime(int n) {
		outer : for(int i = 2; i <= n; i++) {
			if(isPrime(i)) {
				// 소수면
				for(int j = i; j <= n; j++) {
					if(isPrime(j)) {
						for(int k = j; k <= n; k++) {
							if(isPrime(k)) {
								// 3개 다 소수
								
								if(i+j+k == n) {
									System.out.printf("%d %d %d\n",i,j,k);
									break outer;
									// outer break
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static boolean isPrime(int n) {
		// 소수인지 아닌지 판단하는 메소드
		if(n == 1 || n == 0) return false;
		else if(n == 2) {
			return true;
		}else {
			for(int i = 2; i*i <= n; i++) {
				if(n % i == 0) return false;
			}			
			return true;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int tc = scan.nextInt();
		int []arr = new int[tc];
		
		for(int t = 0; t < tc; t++) {
			arr[t] = scan.nextInt();
		}
		list = new ArrayList<>();
		q = new LinkedList<>();
		for(int t = 0; t < tc; t++) {
			N = arr[t];
			getPrime(arr[t]);
		}
		scan.close();
	}

}
