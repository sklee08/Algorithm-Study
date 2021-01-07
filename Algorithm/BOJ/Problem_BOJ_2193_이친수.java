import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_2193_ÀÌÄ£¼ö {
	static int N;
	static long ans;
	static long[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];
		getAns();

		System.out.println(ans);
	}

	public static void getAns() {

		if (N == 1) {
			ans = 1;
			return;
		}
		dp[1] = 1;
		dp[2] = 1;

		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		ans = dp[N];
	}
}
