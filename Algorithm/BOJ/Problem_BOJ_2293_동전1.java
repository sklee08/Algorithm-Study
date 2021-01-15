import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_2293_µ¿Àü1 {
	
	static int N, K;
	static int ans;
	static int[] coin;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		coin = new int[N + 1];
		dp = new int[K + 1];

		for (int i = 1; i <= N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		for (int i = 1; i <= N; i++) {
			if (coin[i] > K)
				continue;
			dp[coin[i]] += 1;
			for(int j = coin[i] + 1; j < K+1; j++) {
				dp[j] += dp[j - coin[i]];
			}
		}
		
		ans = dp[K];
	}
}
