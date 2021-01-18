import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_BOJ_1726_·Îº¿ {
	static int M, N, ans;
	static boolean[][] map;
	static int[] dr = { 0, 0, 0, 1, -1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	static Dot start, end;

	static class Dot {
		int r;
		int c;
		int dir;
		int cnt;

		public Dot(int r, int c, int dir, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}

		public Dot() {
		};

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					map[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int dir = Integer.parseInt(st.nextToken());
		start = new Dot(r, c, dir, 0);
		st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		dir = Integer.parseInt(st.nextToken());
		end = new Dot(r, c, dir, 0);

		bfs();

		System.out.println(ans);
	}

	public static void bfs() {
		Queue<Dot> q = new LinkedList<>();
		boolean[][][] visited = new boolean[M][N][5];
		visited[start.r][start.c][start.dir] = true;
		q.add(new Dot(start.r, start.c, start.dir, 0));

		while (!q.isEmpty()) {
			Dot tmp = q.poll();
			int r = tmp.r;
			int c = tmp.c;
			int cnt = tmp.cnt;
			int dir = tmp.dir;

			if (r == end.r && c == end.c && dir == end.dir) {
				ans = cnt;
				return;
			}

			for (int i = 1; i <= 3; i++) {
				int nr = r + dr[dir] * i;
				int nc = c + dc[dir] * i;
				if (!isIn(nr, nc))
					continue;
				if (!map[nr][nc]) {
					if (!visited[nr][nc][dir]) {
						visited[nr][nc][dir] = true;
						q.add(new Dot(nr, nc, dir, cnt + 1));
					}
				} else {
					break;
				}
			}

			for (int i = 1; i <= 4; i++) {
				if (dir != i && !visited[r][c][i]) {
					int turn = 1;
					if (dir == 1) {
						if (i == 2) {
							turn++;
						}
					} else if (dir == 2) {
						if (i == 1) {
							turn++;
						}
					} else if (dir == 3) {
						if (i == 4) {
							turn++;
						}
					} else {
						if (i == 3) {
							turn++;
						}
					}

					visited[r][c][i] = true;
					q.add(new Dot(r, c, i, cnt + turn));
				}
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return (0 <= r && r < M && 0 <= c && c < N);
	}

}
