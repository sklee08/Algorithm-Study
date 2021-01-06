import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_1890_มกวม {
	static int N;
	static int[][] arr;
	static long[][] dp;
	static long ans;
	static int[] dr = { 0, 1 };
	static int[] dc = { 1, 0 };

	final static int RIGHT = 0;
	final static int DOWN = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		dp = new long[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getAns();

		System.out.println(ans);

		//print();
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void getAns() {
		dp[0][0] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == N - 1 && j == N - 1)
					break;
				if (dp[i][j] != 0) {
					int dist = arr[i][j];
					if (isIn(i + dist, j)) {
						dp[i + dist][j]+= dp[i][j];
					}
					if (isIn(i, j + dist)) {
						dp[i][j + dist]+= dp[i][j];
					}
				}
			}
		}

		ans = dp[N - 1][N - 1];
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}

}
