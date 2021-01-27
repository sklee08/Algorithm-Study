import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_2157_¿©Çà {
	static int N, M, K;
	static int ans;
	static int[][] dp;
	static int[][] cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][M + 1];
		cost = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cost[a][b] = Math.max(cost[a][b], c);
		}

		getAns();

		System.out.println(ans);
		
		print();
	}

	public static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void getAns() {

		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		dp[1][1] = 0;
		for (int i = 1; i < N; i++) {
			int res = Math.min(i, M - 1);
			for (int j = 1; j <= res; j++) {
				if(dp[i][j] != -1) {
					for (int k = i + 1; k <= N; k++) {
						if (cost[i][k] != 0) {
							dp[k][j + 1] = Math.max(dp[k][j + 1], dp[i][j] + cost[i][k]);
						}
					}
				}				
			}
		}
		
		for (int i = 1; i <= M; i++) {
			ans = Math.max(ans, dp[N][i]);
		}
		
	}
}
