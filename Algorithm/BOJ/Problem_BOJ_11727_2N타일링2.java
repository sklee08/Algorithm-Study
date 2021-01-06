import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_11727_2N≈∏¿œ∏µ2 {
	static int N;
	static int ans;
	static int[] dp;

	final static int DIV = 10007;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {

		if (N == 1) {
			ans = 1;
			return;
		}

		dp[1] = 1;
		dp[2] = 3;

		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % DIV;
		}

		ans = dp[N];
	}
}
