import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_1463_1로만들기 {
	static int N;
	static int ans;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];

		getAns();

		System.out.println(ans);

		//print();
	}

	public static void print() {
		for (int i = 1; i <= N; i++) {
			System.out.print(dp[i] + "\t");
		}
		System.out.println();
	}

	public static void getAns() {
		dp[1] = 0;

		for (int i = 2; i <= N; i++) {

			if (i % 6 == 0) {
				int min = Math.min(dp[i / 3], dp[i / 2]);
				dp[i] = Math.min(dp[i - 1] + 1, min) + 1;
				continue;
			}

			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i - 1], dp[i / 3]) + 1;
				continue;
			}

			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
				continue;
			}

			dp[i] = dp[i - 1] + 1;
		}

		ans = dp[N];
	}
}
