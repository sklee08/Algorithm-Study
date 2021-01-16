import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_BOJ_2225_ÇÕºÐÇØ {
	static int N, K, ans;
	static int[][] dp;

	final static int DIV = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[K + 1][N + 1];

		for (int i = 0; i <= K; i++) {
			Arrays.fill(dp[i], 1);
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {

		for (int i = 2; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % DIV;
			}
		}

		ans = dp[K][N];
	}
}
