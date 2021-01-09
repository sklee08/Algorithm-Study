import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_BOJ_1699_Á¦°ö¼öÀÇÇÕ {

	static int N;
	static int ans;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			if (isMul(i)) {
				dp[i] = 1;
				continue;
			}
			int min = Integer.MAX_VALUE;
			for (int j = i - 1; j >= 1; j--) {
				if (dp[j] == 1) {
					min = Math.min(min, dp[i - j] + dp[j]);
				}
			}
			dp[i] = min;
		}
		
		ans = dp[N];
		
		//System.out.println(Arrays.toString(dp));
	}

	public static boolean isMul(int n) {

		for (int i = 1; i * i <= n; i++) {
			if (i * i == n) {
				return true;
			}
		}

		return false;
	}
}
