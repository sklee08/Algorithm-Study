import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_2294_µ¿Àü2 {
	static int N, K, ans;
	static int[] dp;
	static int[] coins;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N + 1];
		dp = new int[K + 1];

		Arrays.fill(dp, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
			if(K >= coins[i]) {
				dp[coins[i]] = 1;
			}			
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		Arrays.sort(coins);

		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				if (dp[i] == 1)
					break;
				if (i - coins[j] < 0)
					continue;
				if(dp[i - coins[j]] == Integer.MAX_VALUE) {
					continue;
				}
				dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
			}
		}
		
		ans = dp[K];
		//System.out.println(Arrays.toString(dp));
		if (ans == Integer.MAX_VALUE) {
			ans = -1;
		}
	}
}
