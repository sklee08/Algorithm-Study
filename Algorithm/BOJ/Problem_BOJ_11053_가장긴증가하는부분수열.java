import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_11053_가장긴증가하는부분수열 {
	static int N;
	static int[] arr;
	static int ans;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		if (N == 1) {
			ans = 1;
			return;
		}
		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			int tmp = arr[i];
			int max = 0;
			for (int j = i - 1; j >= 1; j--) {
				if (tmp > arr[j]) {
					max = Math.max(dp[j], max);
				}
			}
			dp[i] = max + 1;

			ans = Math.max(ans, dp[i]);
		}
	}
}
