package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_12865_평범한배낭 {

	static int N, K;
	static Product[] pr;
	static int ans;
	static int[][] dp;

	static class Product {
		int w;
		int v;

		public Product(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}

	public static void getAns() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				int tmpV = pr[i - 1].v;
				int tmpW = pr[i - 1].w;

				dp[i][j] = dp[i - 1][j];
				
				if(j >= tmpW){
					dp[i][j] = Math.max(dp[i-1][j - tmpW] + tmpV, dp[i][j]);
				}
			}
		}
		
		ans = dp[N][K];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][K + 1];
		pr = new Product[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			pr[i] = new Product(w, v);
		}

		// 입력완료

		getAns();

		System.out.println(ans);
	}
}
