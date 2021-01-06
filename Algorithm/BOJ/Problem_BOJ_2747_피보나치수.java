import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_2747_피보나치수 {
	static int N;
	static int ans;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		ans = dp[N];
	}
}
