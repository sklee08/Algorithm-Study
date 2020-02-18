package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_SWEA_2817_부분수열의합 {

	static int N,K;
	static int[]arr;
	static int ans;
	
	
	public static void getComb() {
		int n = arr.length;
		for(int i = 0; i < (1 << n); i++) {
			int sum = 0;
			for(int j = 0; j < n; j++) {
				if((i & (1 << j)) != 0) {
					sum += arr[j];
				}
			}
			if(sum == K) {
				ans++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++) {
			ans = 0;
			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp, " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N];
			int[] temp = new int[K];
			tmp = br.readLine();
			st = new StringTokenizer(tmp, " ");
			for(int i = 0; i < N; i++) {
				arr[i]= Integer.parseInt(st.nextToken());
			}
			// 입력완료
			getComb();
			System.out.println("#" +t+" "+ ans);
		}
	}

}
