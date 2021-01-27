import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_2410_¸è¼öÀÇÇÕ {
	static int N, ans;
	final static int DIV = 1000000000;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		dp[1] = 1;
		if(N == 1) {
			ans = 1;
			return;
		}
		dp[2] = 2;

		for (int i = 3; i <= N; i++) {
			if (i % 2 == 0) {
				dp[i] = (dp[i - 2] + dp[i / 2]) % DIV;
			} else {
				dp[i] = dp[i - 1];
			}
		}
		ans = dp[N] % DIV;
	}
}
