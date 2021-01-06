import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_BOJ_11057_오르막수 {
	static int N;
	static int ans;
	static int[][] dp;

	final static int DIV = 10007;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		dp = new int[10][N + 1];

		getAns();

		System.out.println(ans);

	}

	public static void getAns() {

		if (N == 1) {
			ans = 10;
			return;
		}

		for (int i = 0; i <= 9; i++) {
			dp[i][1] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				for(int k = 0; k <= j; k++) {
					dp[j][i] += dp[k][i-1];
					dp[j][i] %= DIV; 
				}				
			}
		}

		for (int i = 0; i <= 9; i++) {
			ans += dp[i][N];
			ans %= DIV;
		}
	}
}
