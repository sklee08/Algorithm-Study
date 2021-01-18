import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_2206_벽부수고이동하기 {
	static int N, M;
	static int ans;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static boolean[][] map;
	static boolean[][][] visited;

	static class Dot {
		int r;
		int c;
		boolean flag;
		int cnt;

		public Dot(int r, int c, boolean flag, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.flag = flag;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) - '0' == 1) {
					map[i][j] = true;
				}
			}
		}

		getAns();

		System.out.println(ans);
	}

	public static void getAns() {
		ans = bfs();
	}

	public static int bfs() {
		Queue<Dot> q = new LinkedList<>();
		visited = new boolean[N][M][2];
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		q.add(new Dot(0, 0, false, 0));

		while (!q.isEmpty()) {
			Dot tmp = q.poll();
			int r = tmp.r;
			int c = tmp.c;
			int cnt = tmp.cnt;
			boolean flag = tmp.flag;
			int visit = 0;
			if (flag)
				visit = 1;

			if (r == N - 1 && c == M - 1) {
				return cnt + 1;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (isIn(nr, nc)) {
					// 벽일 때
					if (map[nr][nc]) {
						if (visit == 0 && !visited[nr][nc][1]) {
							// 벽을 부시지 않았고, 다음(부신) 상태가 비어있는 경우 
							visited[nr][nc][1] = true;
							q.add(new Dot(nr, nc, true, cnt + 1));
						}
						
					}else {
						// 벽이 아닐 때
						if(!visited[nr][nc][visit]) {
							// 둘다 가능하게끔하기.
							q.add(new Dot(nr, nc, flag, cnt+1));
							visited[nr][nc][visit] = true;
						}
					}
				}
			}
		}

		return -1;
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M);
	}
}
