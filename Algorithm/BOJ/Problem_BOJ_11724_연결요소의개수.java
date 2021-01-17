import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Problem_BOJ_11724_연결요소의개수 {
	static int N, M;
	static int ans;
	static boolean[][] connected;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		connected = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connected[a][b] = true;
			connected[b][a] = true;
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
				ans++;
			}
		}
	}

	public static void dfs(int n) {
		for(int i = 1; i <= N; i++) {
			if(connected[n][i] && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
	}
}
