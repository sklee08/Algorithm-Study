import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Problem_BOJ_11052_카드구매하기 {
	static int N;
	static int ans;
	static int[] dp;
	static int[] cards;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cards = new int[N + 1];
		dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		if (N == 1) {
			ans = cards[1];
			return;
		}

		dp[1] = cards[1];
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + cards[j]);
			}
		}
		ans = dp[N];
		for (int i = 1; i <= N; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
	}
}
