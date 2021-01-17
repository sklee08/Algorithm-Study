import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_BOJ_10451_순열사이클 {
	static int T, N;
	static int[] cycle;
	static int ans;
	static boolean[] visited;
	static boolean[][] connected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			cycle = new int[N + 1];
			ans = 0;

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				cycle[i] = Integer.parseInt(st.nextToken());
			}
			connected = new boolean[N + 1][N + 1];
			getAns();

			System.out.println(ans);
		}
	}

	public static void getAns() {
		for (int i = 1; i <= N; i++) {
			connected[i][cycle[i]] = true;
		}
		// 연결 처리

		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
				ans++;
			}
		}
	}

	public static void dfs(int n) {

		for (int i = 1; i < visited.length; i++) {
			if (!visited[i] && connected[n][i]) {
				visited[i] = true;
				dfs(i);
			}
		}
	}

}
