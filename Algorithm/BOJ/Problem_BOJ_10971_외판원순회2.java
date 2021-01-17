import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_10971_외판원순회2 {
	static int N, ans;
	static int[][] cost;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		cost = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		ans = Integer.MAX_VALUE;
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			dfs(i, i, 0, 0);
		}
	}

	public static void dfs(int start, int i, int cnt, int sum) {
		if (cnt == N && start == i) {
			ans = Math.min(ans, sum);
			return;
		}

		for (int j = 1; j <= N; j++) {
			if (cost[i][j] == 0)
				continue;

			if (!visited[i] && cost[i][j] > 0) {
				visited[i] = true;
				sum += cost[i][j];
				dfs(start, j, cnt+1, sum);
				visited[i] = false;
				sum -= cost[i][j];
			}
		}
	}
}
