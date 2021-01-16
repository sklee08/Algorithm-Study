import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_1309_µ¿¹°¿ø {
	static int N, ans;
	static int[][] dp;

	final static int DIV = 9901;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[3][N + 1];
		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		if (N == 1) {
			ans = 3;
			return;
		}

		dp[0][1] = 1;
		dp[1][1] = 1;
		dp[2][1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[0][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1]) % DIV;
			dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % DIV;
			dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % DIV;
		}
		
		ans = (dp[0][N] + dp[1][N] + dp[2][N]) % DIV;
	}
}
