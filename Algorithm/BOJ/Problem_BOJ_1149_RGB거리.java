import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_1149_RGB°Å¸® {
	static int N, ans;
	static int[][] dp;
	static int[][] cost;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N + 1][3];
		dp = new int[3][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}

		getAns();

		System.out.println(ans);
		
		//print();
	}

	public static void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void getAns() {
		if (N == 1) {
			ans = getMin(cost[1][0], cost[1][1], cost[1][2]);
			return;
		}

		for (int i = 0; i < 3; i++) {
			dp[i][1] = cost[1][i];
		}

		for (int i = 2; i <= N; i++) {
			dp[0][i] = cost[i][0] + Math.min(dp[1][i - 1], dp[2][i - 1]);
			dp[1][i] = cost[i][1] + Math.min(dp[0][i - 1], dp[2][i - 1]);
			dp[2][i] = cost[i][2] + Math.min(dp[1][i - 1], dp[0][i - 1]);
		}

		ans = getMin(dp[0][N], dp[1][N], dp[2][N]);
	}

	public static int getMin(int a, int b, int c) {
		return (Math.min(c, Math.min(a, b)));
	}
}
