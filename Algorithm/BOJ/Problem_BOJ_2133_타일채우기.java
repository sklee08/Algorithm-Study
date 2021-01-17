import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_2133_타일채우기 {
	static int N, ans;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		if (N == 1) {
			ans = 0;
			return;
		}
		dp[2] = 3;

		for (int i = 3; i <= N; i++) {
			if (i % 2 != 0) {
				continue;
			}
			int sum = 0;
			for (int j = i - 2; j > 0; j -= 2) {
				if (j == i - 2) {
					sum += (dp[j] * 3);
					continue;
				}
				sum += (dp[j] * 2);
			}
			sum += 2;
			dp[i] = sum;
		}
		ans = dp[N];
	}
}
