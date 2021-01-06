import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_10844_쉬운계단수 {
	static int N;
	static int ans;
	static int[][] dp;

	final static int DIV = 1000000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[10][N + 1];

		getAns();

		System.out.println(ans);

	}

	public static void getAns() {
		dp[0][1] = 0;

		for (int i = 1; i <= 9; i++) {
			dp[i][1] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					dp[j][i] = dp[1][i - 1];
					continue;
				}
				if (j == 9) {
					dp[j][i] = dp[8][i - 1];
					continue;
				}

				dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
				dp[j][i] %= DIV;
			}
		}

		for (int i = 0; i < 10; i++) {
			ans += dp[i][N];
			ans %= DIV;
		}
	}
}
