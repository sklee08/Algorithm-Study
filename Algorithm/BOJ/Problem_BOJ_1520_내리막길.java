import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_1520_내리막길 {
	static int N, M, ans;
	static int[][] map;
	static int[][] dp;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		getAns();

		System.out.println(ans);

		//print();
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void getAns() {

		ans = dfs(0, 0);
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M);
	}

	public static int dfs(int nx, int ny) {
		if (nx == N - 1 && ny == M - 1) {
			return 1;
		}
		if (dp[nx][ny] != -1) {
			return dp[nx][ny];
		}

		dp[nx][ny] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = nx + dr[d];
			int nc = ny + dc[d];
			if (isIn(nr, nc) && map[nx][ny] > map[nr][nc]) {
				dp[nx][ny] += dfs(nr, nc);
			}
		}

		return dp[nx][ny];
	}

}
