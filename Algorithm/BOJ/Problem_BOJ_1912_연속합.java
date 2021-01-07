import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Problem_BOJ_1912_¿¬¼ÓÇÕ {
	static int N;
	static int[] dp;
	static int ans = Integer.MIN_VALUE;
	static int[] arr;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		getAns();

		System.out.println(ans);

		//print();
	}

	public static void print() {
		for (int j = 1; j <= N; j++) {
			System.out.print(dp[j] + " ");
		}
		System.out.println();
	}

	public static void getAns() {

		if (N == 1) {
			ans = arr[1];
			return;
		}
		dp[1] = arr[1];

		for (int i = 2; i <= N; i++) {
			if (dp[i - 1] + arr[i] < arr[i]) {
				dp[i] = arr[i];
			} else {
				dp[i] = dp[i - 1] + arr[i];
			}
			
		}
		for(int i = 1; i <= N; i++) {
			ans = Math.max(ans, dp[i]);
		}
	}
}
